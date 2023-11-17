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

@WebServlet("/allLists")
public class AllListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Serveis per gestionar els dibuixos
        DrawingServices drawingServices = new DrawingServices();
        List<Drawing> drawings = drawingServices.loadAll();

        // Configurar atribut a la sol·licitud amb la llista de tots els dibuixos
        req.setAttribute("drawings", drawings);

        // Redirigir a la pàgina JSP que mostrarà la llista de tots els dibuixos
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/allLists.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Obtenir l'ID del dibuix a eliminar des del formulari
        int id = Integer.parseInt(req.getParameter("drawingId"));

        // Obtenir l'usuari actual des de la sessió
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        DrawingServices drawingServices = new DrawingServices();
        if (drawingServices.delete(id, user)) {

            // Indiquem que l'usuari ha eliminat correctament el dibuix
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/confirmation.jsp");
            req.setAttribute("confirmation", "Your drawing is deleted");
            dispatcher.forward(req, resp);
        }

        // Gestionar l'excepció per a un usuari existent configurant un atribut d'error i redirigint a la pàgina de registre
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
        req.setAttribute("error", "You cant delete this drawing, is not yours");
        dispatcher.forward(req, resp);
    }

}
