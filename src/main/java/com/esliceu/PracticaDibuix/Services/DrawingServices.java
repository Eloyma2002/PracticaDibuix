package com.esliceu.PracticaDibuix.Services;

import com.esliceu.PracticaDibuix.DAO.DrawingDAO;
import com.esliceu.PracticaDibuix.DAO.DrawingDAOImpl;
import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.util.List;
import java.util.Objects;

public class DrawingServices {

    // Mètode per desar un dibuix
    public boolean save(Drawing drawing) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();

        // Obtindre el JSON de figures del dibuix
        String figuresJson = drawing.getFigures();
        JSONParser parser = new JSONParser();

        try {
            // Intentar parsejar el JSON i obtenir el nombre de figures
            JSONArray jsonArray = (JSONArray) parser.parse(figuresJson);
            drawing.setNumFigures(jsonArray.size());
            if (drawing.getNumFigures() == 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        // Desar el dibuix a la base de dades
        drawingDAO.save(drawing);
        return true;
    }

    // Mètode per carregar tots els dibuixos
    public List<Drawing> loadAll() {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        return drawingDAO.loadAllLists();
    }

    // Mètode per carregar la llista de dibuixos d'un usuari específic
    public List<Drawing> loadMyList(User user) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        return drawingDAO.loadMyList(user);
    }

    // Mètode per eliminar un dibuix
    public boolean delete(int id, User user) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        return drawingDAO.deleteDrawing(id, user);
    }

    // Mètode per obtenir un dibuix pel seu ID
    public Drawing getDrawing(int id) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        return drawingDAO.getDrawing(id);
    }

    // Mètode per modificar un dibuix ja creat anteriorment
    public boolean modify(int id, String figures, String newName, User user, Drawing drawing) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        JSONParser parser = new JSONParser();

        try {
            // Intentar parsejar el JSON i obtenir el nombre de figures
            JSONArray jsonArray = (JSONArray) parser.parse(figures);
            if (Objects.equals(user.getUsername(), drawing.getUser().getUsername())) {
                drawingDAO.modifyFigures(id, figures, newName, jsonArray.size(), user);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
