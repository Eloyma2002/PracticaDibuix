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
        // Redirigir a la página JSP de inicio de sesión
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UserDoesntExist, ServletException, IOException {
        // Obtener el nombre de usuario y la contraseña del formulario de inicio de sesión
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Inicializar UserServices para interactuar con funcionalidades relacionadas con el usuario
        UserServices userServices = new UserServices();

        try {
            // Intentar realizar el inicio de sesión y obtener el objeto User correspondiente
            User user = userServices.login(username, password);
            System.out.println("alo");
            // Si el usuario existe, crear una sesión y redirigir a la página de geoform
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect("/geoform");
            }
        } catch (UserDoesntExist e) {
            // Manejar la excepción de usuario no encontrado configurando un error y redirigiendo a la página de inicio
            // de sesión
            req.setAttribute("error", "User not found");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
