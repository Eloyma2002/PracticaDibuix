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
        // Redirigir a la pàgina JSP per visualitzar el dibuix
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/viewDrawing.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtindre l'ID del dibuix a visualitzar des del formulari
        int id = Integer.parseInt(req.getParameter("drawingId"));

        // Obtindre el dibuix des del servei de dibuixos
        DrawingServices drawingServices = new DrawingServices();
        Drawing drawing = drawingServices.getDrawing(id);

        // Configurar l'atribut en la sol·licitud amb el JSON del dibuix
        req.setAttribute("JSON", drawing.getFigures());

        // Redirigir a la pàgina JSP per visualitzar el dibuix
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/viewDrawing.jsp");
        dispatcher.forward(req, resp);
    }
}
