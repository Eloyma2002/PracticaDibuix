package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.DAO.ViewDAO;
import com.esliceu.PracticaDibuix.DAO.ViewDAOImpl;
import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Services.DrawingServices;
import com.esliceu.PracticaDibuix.Services.ViewServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/viewDrawing")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DrawingServices drawingServices = new DrawingServices();
        ViewServices viewServices = new ViewServices();
        List<Drawing> drawings = drawingServices.loadAll();
        int id = Integer.parseInt(req.getParameter("drawingId"));
        String figures = viewServices.getDrawing(id, drawings);
        //req.setAttribute("");


        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/viewDrawing.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/viewDrawing.jsp");
        disp.forward(req, resp);
    }
}
