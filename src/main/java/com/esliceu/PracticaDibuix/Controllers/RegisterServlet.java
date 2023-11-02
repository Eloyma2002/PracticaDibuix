package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.DAO.UserDAOImpl;
import com.esliceu.PracticaDibuix.Model.User;
import com.esliceu.PracticaDibuix.Services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nomICognoms = req.getParameter("nomICognoms");
        String nomUsuari = req.getParameter("nomUsuari");
        String password = req.getParameter("password");
        UserServices userServices = new UserServices();
        User usuari = new User();
        usuari.setNomUsuari(nomUsuari);
        usuari.setPassword(password);
        usuari.setNomICognoms(nomICognoms);
        userServices.register(usuari);

        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        disp.forward(req, resp);
    }
}
