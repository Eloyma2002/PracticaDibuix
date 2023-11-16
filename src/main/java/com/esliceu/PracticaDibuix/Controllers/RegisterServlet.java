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

// Mapeo del servlet para la página de registro
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // Manejar las solicitudes GET para la página de registro
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirigir la solicitud a la página JSP de registro
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        dispatcher.forward(req, resp);
    }

    // Manejar las solicitudes POST al enviar el formulario de registro
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los datos del usuario desde el formulario de registro
        String nameAndLastName = req.getParameter("nameAndLastName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        // Inicializar UserServices para interactuar con funcionalidades relacionadas con el usuario
        UserServices userServices = new UserServices();

        // Crear un objeto User con la información proporcionada
        User user = new User(userName, nameAndLastName, password);

        // Validar la contraseña utilizando una expresión regular
        String regex = "^(?!.*\\s).{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        try {
            // Verificar si la contraseña es válida; si no lo es, lanzar una excepción
            if (!matcher.matches()) {
                throw new PasswordNotValid();
            }
        } catch (PasswordNotValid pnv) {
            // Manejar la excepción configurando un atributo de error y redirigiendo a la página de registro
            req.setAttribute("error", "Contraseña no válida, mínimo 5 dígitos y sin espacios");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
            dispatcher.forward(req, resp);
            // Detener el procesamiento para evitar el registro del usuario
            return;
        }

        try {
            // Intentar registrar al usuario; si tiene éxito, redirigir a la página de inicio de sesión
            userServices.register(user);
            resp.sendRedirect("/login");
        } catch (UserExist userExist) {
            // Manejar la excepción para un usuario existente configurando un atributo de error y redirigiendo a la página de registro
            req.setAttribute("error", "El usuario ya existe");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
