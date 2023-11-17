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
        // Redirigeix a la pàgina JSP de geoform
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/geoform.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtenir la sessió actual
        HttpSession session = req.getSession();

        // Serveis per gestionar els dibuixos
        DrawingServices drawingServices = new DrawingServices();

        // Obtenir les dades JSON i el nom del dibuix a través del formulari
        String json = req.getParameter("JSON");
        String drawingName = req.getParameter("name");

        // Obtenir l'usuari de la sessió actual
        User user = (User) session.getAttribute("user");

        // Crear un objecte Drawing amb les dades proporcionades
        Drawing drawing = new Drawing(drawingName, user, json, 0);

        // Guardar el dibuix a la base de dades
        if (drawingServices.save(drawing)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/confirmation.jsp");
            req.setAttribute("confirmation", "Your drawing is saved");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            req.setAttribute("error", "You cannot save a drawing without content");
            dispatcher.forward(req, resp);
        }
    }
}
