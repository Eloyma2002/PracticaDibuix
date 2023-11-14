package com.esliceu.PracticaDibuix.Services;

import com.esliceu.PracticaDibuix.DAO.DrawingDAO;
import com.esliceu.PracticaDibuix.DAO.DrawingDAOImpl;
import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.util.List;

public class DrawingServices {

    public void save(Drawing dr) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        String numOfFigures = dr.getFigures();
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(numOfFigures);
            dr.setNumFigures(jsonArray.size());
        } catch (Exception e) {
            dr.setNumFigures(0);
        }
        drawingDAO.save(dr);
    }

    public List<Drawing> loadAll() {
        DrawingDAO drawingDAO = new DrawingDAOImpl();

        return drawingDAO.loadAllLists();
    }

    public List<Drawing> loadMyList(User user) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();

        return drawingDAO.loadMyList(user);
    }

    public void delete(int id, User user) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        drawingDAO.deleteDrawing(id, user);
    }
}
