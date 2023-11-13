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

@WebServlet("/allLists")
public class AllListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DrawingServices drawingServices = new DrawingServices();
        List<Drawing> drawings = drawingServices.loadAll();
        req.setAttribute("drawings", drawings);


        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/allLists.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/allLists.jsp");
        disp.forward(req, resp);
    }

}
