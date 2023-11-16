package com.esliceu.PracticaDibuix.Services;

import com.esliceu.PracticaDibuix.DAO.DrawingDAO;
import com.esliceu.PracticaDibuix.DAO.DrawingDAOImpl;
import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.util.List;

public class DrawingServices {

    // Método para guardar un dibujo
    public void save(Drawing drawing) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();

        // Obtener el JSON de figuras del dibujo
        String figuresJson = drawing.getFigures();
        JSONParser parser = new JSONParser();

        try {
            // Intentar parsear el JSON y obtener el número de figuras
            JSONArray jsonArray = (JSONArray) parser.parse(figuresJson);
            drawing.setNumFigures(jsonArray.size());
        } catch (Exception e) {
            // En caso de error, establecer el número de figuras en 0
            drawing.setNumFigures(0);
        }

        // Guardar el dibujo en la base de datos
        drawingDAO.save(drawing);
    }

    // Método para cargar todos los dibujos
    public List<Drawing> loadAll() {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        return drawingDAO.loadAllLists();
    }

    // Método para cargar la lista de dibujos de un usuario específico
    public List<Drawing> loadMyList(User user) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        return drawingDAO.loadMyList(user);
    }

    // Método para eliminar un dibujo
    public void delete(int id, User user) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        drawingDAO.deleteDrawing(id, user);
    }

    // Método para obtener un dibujo por su ID
    public Drawing getDrawing(int id) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        return drawingDAO.getDrawing(id);
    }

    // Método para modificar un dibujo ya creado anteriormente
    public void modify(int id, String figures, String newName) {
        DrawingDAO drawingDAO = new DrawingDAOImpl();
        JSONParser parser = new JSONParser();

        try {
            // Intentar parsear el JSON y obtener el número de figuras
            JSONArray jsonArray = (JSONArray) parser.parse(figures);
            drawingDAO.modifyFigures(id, figures, newName, jsonArray.size());
        } catch (Exception e) {
            return;
        }
    }
}
