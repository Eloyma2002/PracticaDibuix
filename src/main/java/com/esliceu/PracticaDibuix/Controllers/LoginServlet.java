package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.Exceptions.UserDoesntExist;
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
        // Redirigeix a la pàgina JSP d'inici de sessió
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UserDoesntExist, ServletException, IOException {
        // Obtenir el nom d'usuari i la contrasenya del formulari d'inici de sessió
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Inicialitzar UserServices per interactuar amb funcionalitats relacionades amb l'usuari
        UserServices userServices = new UserServices();

        // Intentar realitzar l'inici de sessió i obtenir l'objecte User corresponent
        User user = userServices.login(username, password);

        // Si l'usuari existeix, crear una sessió i redirigir a la pàgina de geoform
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/geoform");
        } else {
            // Manejar la excepció d'usuari no trobat configurant un missatge d'error i redirigint a la pàgina d'inici de sessió
            req.setAttribute("error", "User not found");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
