package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.Exceprions.UserDoesntExist;
import com.esliceu.PracticaDibuix.Model.User;
import com.esliceu.PracticaDibuix.Services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        disp.forward(req, resp);    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        UserServices userServices = new UserServices();
        User user;
        try {
            user = userServices.login(userName, password);
            resp.sendRedirect("/geoform");
        } catch (UserDoesntExist e) {
            req.setAttribute("error", "User not found");
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
            disp.forward(req, resp);
        }
    }
}
