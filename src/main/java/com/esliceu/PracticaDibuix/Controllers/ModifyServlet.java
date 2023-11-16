package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.Exceptions.IsNotYourDrawing;
import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;
import com.esliceu.PracticaDibuix.Services.DrawingServices;
import com.esliceu.PracticaDibuix.Services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/modifyDrawing")
public class ModifyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Servicios de usuario para verificar la sesión del usuario
        UserServices userServices = new UserServices();
        userServices.securitySession(req, resp);

        // Redirigir a la página JSP para modificar el dibujo
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/modifyDrawing.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Servicios de usuario para verificar la sesión del usuario
        UserServices userServices = new UserServices();
        userServices.securitySession(req, resp);

        // Obtener la sesión actual
        HttpSession session = req.getSession();

        // Obtener el ID del dibujo a modificar desde el formulario
        int id = Integer.parseInt(req.getParameter("drawingId"));

        // Obtener el dibujo desde el servicio de dibujos
        DrawingServices drawingServices = new DrawingServices();
        Drawing drawing = drawingServices.getDrawing(id);

        // Obtener la información necesaria del dibujo
        String figures = drawing.getFigures();
        String name = req.getParameter("name");

        // Configurar atributos en la solicitud para la página JSP
        req.setAttribute("drawingId", drawing.getId());
        req.setAttribute("JSON", figures);
        req.setAttribute("name", drawing.getName());

        // Obtener el usuario actual desde la sesión
        User user = (User) session.getAttribute("user");

        try {
            // Verificar si el dibujo pertenece al usuario actual antes de modificar
            if (user.getUsername().equals(drawing.getUser().getUsername())) {
                figures = req.getParameter("JSON");
                drawingServices.modify(id, figures, name);
            } else {
                // Lanzar una excepción si el dibujo no pertenece al usuario actual
                throw new IsNotYourDrawing();
            }
        } catch (IsNotYourDrawing isNotYourDrawing) {
            req.setAttribute("error", "You can't modify this drawing, it's not yours");
        }

        // Redirigir a la página JSP para modificar el dibujo
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/modifyDrawing.jsp");
        dispatcher.forward(req, resp);
    }
}
