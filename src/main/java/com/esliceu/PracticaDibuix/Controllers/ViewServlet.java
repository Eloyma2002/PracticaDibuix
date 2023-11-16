package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Services.DrawingServices;
import com.esliceu.PracticaDibuix.Services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value = "/viewDrawing")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Servicios de usuario para verificar la sesión del usuario
        UserServices userServices = new UserServices();
        userServices.securitySession(req, resp);

        // Redirigir a la página JSP para visualizar el dibujo
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/viewDrawing.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Servicios de usuario para verificar la sesión del usuario
        UserServices userServices = new UserServices();
        userServices.securitySession(req, resp);

        // Obtener el ID del dibujo a visualizar desde el formulario
        int id = Integer.parseInt(req.getParameter("drawingId"));

        // Obtener el dibujo desde el servicio de dibujos
        DrawingServices drawingServices = new DrawingServices();
        Drawing drawing = drawingServices.getDrawing(id);

        // Configurar el atributo en la solicitud con el JSON del dibujo
        req.setAttribute("JSON", drawing.getFigures());

        // Redirigir a la página JSP para visualizar el dibujo
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/viewDrawing.jsp");
        dispatcher.forward(req, resp);
    }
}
