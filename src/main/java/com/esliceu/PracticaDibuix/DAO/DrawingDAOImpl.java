package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Model.Drawing;

import java.util.ArrayList;
import java.util.List;

public class DrawingDAOImpl implements DrawingDAO {

    static List<Drawing> drawings = new ArrayList<>();
    static int id = 1;

    @Override
    public void save(Drawing dr) {
        dr.setId(id);
        drawings.add(dr);
        id++;
    }
}
