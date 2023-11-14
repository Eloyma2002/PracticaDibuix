package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Model.Drawing;

import java.util.List;

public class ViewDAOImpl implements ViewDAO{

    @Override
    public String getDrawing(int id, List<Drawing> allDrawings) {

        for (Drawing drawing : allDrawings) {
            if (drawing.getId() == id) {
                return drawing.getFigures();
            }
        }
        return null;
    }
}
