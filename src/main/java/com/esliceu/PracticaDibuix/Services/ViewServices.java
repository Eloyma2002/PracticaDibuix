package com.esliceu.PracticaDibuix.Services;

import com.esliceu.PracticaDibuix.DAO.ViewDAO;
import com.esliceu.PracticaDibuix.DAO.ViewDAOImpl;
import com.esliceu.PracticaDibuix.Model.Drawing;

import java.util.List;

public class ViewServices {

    public String getDrawing(int id, List<Drawing> allDrawings) {
        ViewDAO viewDAO = new ViewDAOImpl();
        return viewDAO.getDrawing(id, allDrawings);
    }
}
