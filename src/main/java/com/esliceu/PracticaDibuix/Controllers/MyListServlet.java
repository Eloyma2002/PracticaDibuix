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
        // Obté la sessió actual
        HttpSession session = req.getSession();

        // Serveis per gestionar els dibuixos
        DrawingServices drawingServices = new DrawingServices();

        // Obté l'usuari actual des de la sessió
        User user = (User) session.getAttribute("user");

        // Carregar la llista de dibuixos específics de l'usuari
        List<Drawing> drawings = drawingServices.loadMyList(user);

        // Configurar l'atribut a la sol·licitud amb la llista de dibuixos de l'usuari
        req.setAttribute("drawings", drawings);

        // Redirigir a la pàgina JSP que mostrarà la llista de dibuixos de l'usuari
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/myList.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Obté la sessió actual
        HttpSession session = req.getSession();

        // Obté l'ID del dibuix a eliminar des del formulari
        int id = Integer.parseInt(req.getParameter("drawingId"));

        // Obté l'usuari actual des de la sessió
        User user = (User) session.getAttribute("user");

        DrawingServices drawingServices = new DrawingServices();
        if (drawingServices.delete(id, user)) {
            // Indiquem que l'usuari ha esborrat correctament el dibuix
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/confirmation.jsp");
            req.setAttribute("confirmation", "Your drawing is deleted");
        }

        // Gestionar l'excepció per a un dibuix no existent configurant un atribut d'error i redirigint a la pàgina de registre
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
        req.setAttribute("error", "You cant delete this drawing, is not yours");
        dispatcher.forward(req, resp);
        dispatcher.forward(req, resp);
    }
}
