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

@WebServlet("/geoform")
public class GeoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Servicios de usuario para verificar la sesión del usuario
        UserServices userServices = new UserServices();
        userServices.securitySession(req, resp);

        // Redirigir a la página JSP de geoform
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/geoform.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Servicios de usuario para verificar la sesión del usuario
        UserServices userServices = new UserServices();
        userServices.securitySession(req, resp);

        // Obtener la sesión actual
        HttpSession session = req.getSession();

        // Servicios para manejar los dibujos
        DrawingServices drawingServices = new DrawingServices();

        // Obtener los datos JSON y el nombre del dibujo atraves formulario
        String json = req.getParameter("JSON");
        String drawingName = req.getParameter("name");

        // Obtener el usuario de la sesión actual
        User user = (User) session.getAttribute("user");

        // Crear un objeto Drawing con los datos proporcionados
        Drawing drawing = new Drawing(drawingName, user, json, 0);

        // Guardar el dibujo en la base de datos
        drawingServices.save(drawing);

        // Redirigir a la página JSP de geoform después de guardar el dibujo
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/geoform.jsp");
        dispatcher.forward(req, resp);
    }
}
