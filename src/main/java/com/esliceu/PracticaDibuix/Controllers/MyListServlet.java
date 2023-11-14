package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;
import com.esliceu.PracticaDibuix.Services.DrawingServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/myList")
public class MyListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DrawingServices drawingServices = new DrawingServices();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Drawing> drawings = drawingServices.loadMyList(user);
        req.setAttribute("drawings", drawings);

        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/myList.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter("drawingId"));
        User user = (User) session.getAttribute("user");
        DrawingServices drawingServices = new DrawingServices();
        drawingServices.delete(id, user);
        resp.sendRedirect(req.getContextPath() + "/myList");
    }
}
