package com.esliceu.PracticaDibuix.Services;

import com.esliceu.PracticaDibuix.DAO.DrawingDAO;
import com.esliceu.PracticaDibuix.DAO.DrawingDAOImpl;
import com.esliceu.PracticaDibuix.Model.Drawing;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class DrawingServices {

    public void save (Drawing dr) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        String numOfFigures = dr.getFigures();
        JSONParser parser = new JSONParser();
        JSONArray array;

        try {
            System.out.println("BON DIE");
            array = (JSONArray) parser.parse(numOfFigures);
            dr.setNumFigures(array.size());
        } catch (Exception e) {
            System.out.println("MAL DIE");
            return;
        }

        for (int i = 0; i < dr.getNumFigures(); i++) {
            System.out.println("DEBUG: " + array.get(i));
        }

        drawingDAO.save(dr);
    }
}
