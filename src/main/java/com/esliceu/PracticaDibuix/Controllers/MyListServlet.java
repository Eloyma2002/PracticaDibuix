package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;
import com.esliceu.PracticaDibuix.Services.DrawingServices;
import com.esliceu.PracticaDibuix.Services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/myList")
public class MyListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Servicios de usuario para verificar la sesión del usuario
        UserServices userServices = new UserServices();
        userServices.securitySession(req, resp);

        // Obtener la sesión actual
        HttpSession session = req.getSession();

        // Servicios para manejar los dibujos
        DrawingServices drawingServices = new DrawingServices();

        // Obtener el usuario actual desde la sesión
        User user = (User) session.getAttribute("user");

        // Cargar la lista de dibujos específicos del usuario
        List<Drawing> drawings = drawingServices.loadMyList(user);

        // Configurar el atributo en la solicitud con la lista de dibujos del usuario
        req.setAttribute("drawings", drawings);

        // Redirigir a la página JSP que mostrará la lista de dibujos del usurio
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/myList.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Servicios de usuario para verificar la sesión del usuario
        UserServices userServices = new UserServices();
        userServices.securitySession(req, resp);

        // Obtener la sesión actual
        HttpSession session = req.getSession();

        // Obtener el ID del dibujo a eliminar desde el formulario
        int id = Integer.parseInt(req.getParameter("drawingId"));

        // Obtener el usuario actual desde la sesión
        User user = (User) session.getAttribute("user");

        // Servicios para manejar los dibujos y eliminar el dibujo específico del usuario
        DrawingServices drawingServices = new DrawingServices();
        drawingServices.delete(id, user);

        // Redirigir a la página de lista de dibujos del usuario después de eliminar
        resp.sendRedirect(req.getContextPath() + "/myList");
    }
}
