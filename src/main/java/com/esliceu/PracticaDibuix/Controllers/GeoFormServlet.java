package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;
import com.esliceu.PracticaDibuix.Services.DrawingServices;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/geoform")
public class GeoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/geoform.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DrawingServices drawingServices = new DrawingServices();
        String json = req.getParameter("JSON");
        String drawingName = req.getParameter("name");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Drawing dr = new Drawing(drawingName, user, json, 0);
        drawingServices.save(dr);



        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/geoform.jsp");
        disp.forward(req, resp);
    }

}
