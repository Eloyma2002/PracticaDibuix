package com.esliceu.PracticaDibuix.Controllers;

import com.esliceu.PracticaDibuix.Exceptions.PasswordNotValid;
import com.esliceu.PracticaDibuix.Exceptions.UserExist;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Mapeig del servlet per a la pàgina de registre
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // Gestionar les sol·licituds GET per a la pàgina de registre
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirigir la sol·licitud a la pàgina JSP de registre
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        dispatcher.forward(req, resp);
    }

    // Gestionar les sol·licituds POST en enviar el formulari de registre
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtindre les dades de l'usuari des del formulari de registre
        String nameAndLastName = req.getParameter("nameAndLastName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        // Inicialitzar UserServices per interactuar amb funcionalitats relacionades amb l'usuari
        UserServices userServices = new UserServices();

        // Crear un objecte User amb la informació proporcionada
        User user = new User(userName, nameAndLastName, password);

        // Validar la contrasenya utilitzant una expressió regular
        String regex = "^(?!.*\\s).{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        try {
            // Verificar si la contrasenya és vàlida; si no ho és, llançar una excepció
            if (!matcher.matches()) {
                throw new PasswordNotValid();
            }
        } catch (PasswordNotValid pnv) {
            // Gestionar l'excepció configurant un atribut d'error i redirigint a la pàgina de registre
            req.setAttribute("error", "Invalid password, min 5 digits and no spaces");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
            dispatcher.forward(req, resp);
            // Aturar el processament per evitar el registre de l'usuari
            return;
        }

        try {
            // Intentar registrar l'usuari; si té èxit, redirigir a la pàgina d'inici de sessió
            userServices.register(user);
            resp.sendRedirect("/login");
        } catch (UserExist userExist) {
            // Gestionar l'excepció per a un usuari existent configurant un atribut d'error i redirigint a la pàgina de registre
            req.setAttribute("error", "User already exist");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
