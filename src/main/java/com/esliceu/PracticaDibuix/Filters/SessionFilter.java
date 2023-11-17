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
        // Obtenir la sessió actual
        HttpSession session = req.getSession();
        // Obtenir l'usuari de la sessió
        User user = (User) session.getAttribute("user");

        // Verificar si l'usuari està autenticat (si no hi ha un usuari a la sessió)
        if (user == null) {
            // Redirigir a la pàgina d'inici de sessió si l'usuari no està autenticat
            res.sendRedirect("/login");
            return;
        }

        super.doFilter(req, res, chain);
    }
}
