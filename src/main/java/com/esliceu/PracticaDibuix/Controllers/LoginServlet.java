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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UserDoesntExist, ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        UserServices userServices = new UserServices();
        userServices.adminAdd();
        User user = userServices.login(userName, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect("/geoform");
        } else {
            req.setAttribute("error", "User not found");
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            disp.forward(req, resp);
        }

    }
}
