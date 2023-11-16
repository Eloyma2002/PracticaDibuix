package com.esliceu.PracticaDibuix.Filters;

import com.esliceu.PracticaDibuix.Model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = {"/geoform", "/myList", "/allLists", "/viewDrawing", "/modifyDrawing"})
public class SessionFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // Obtener la sesión actual
        HttpSession session = req.getSession();
        // Obtener el usuario de la sesión
        User user = (User) session.getAttribute("user");

        // Verificar si el usuario está autenticado (si no hay un usuario en la sesión)
        if (user == null) {
            // Redirigir a la página de inicio de sesión si el usuario no está autenticado
            res.sendRedirect("/login");
            return;
        }

        super.doFilter(req, res, chain);
    }
}
