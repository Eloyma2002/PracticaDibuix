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
        // Redirigeix a la pàgina JSP per modificar el dibuix
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/modifyDrawing.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/modifyDrawing.jsp");

        // Obtenir la sessió actual
        HttpSession session = req.getSession();

        // Obtenir l'ID del dibuix a modificar des del formulari
        int id = Integer.parseInt(req.getParameter("drawingId"));

        // Obtenir el dibuix des del servei de dibuixos
        DrawingServices drawingServices = new DrawingServices();
        Drawing drawing = drawingServices.getDrawing(id);

        // Obtenir la informació necessària del dibuix
        String figures = drawing.getFigures();
        String name = req.getParameter("name");

        // Configurar atributs en la sol·licitud per a la pàgina JSP
        req.setAttribute("drawingId", drawing.getId());
        req.setAttribute("JSON", figures);
        req.setAttribute("name", drawing.getName());

        // Obtenir l'usuari actual des de la sessió
        User user = (User) session.getAttribute("user");

        figures = req.getParameter("JSON");
        if (drawingServices.modify(id, figures, name, user, drawing)) {
            dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/confirmation.jsp");
            req.setAttribute("confirmation", "El teu dibuix s'ha modificat correctament");
        }


        // Redirigir a la pàgina JSP per modificar el dibuix
        dispatcher.forward(req, resp);
    }
}
