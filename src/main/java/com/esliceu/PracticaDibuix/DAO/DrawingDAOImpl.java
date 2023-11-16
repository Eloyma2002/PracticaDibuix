package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DrawingDAOImpl implements DrawingDAO {

    // Lista que almacena los dibujos a modo de base de datos (cada uno enlazado con su usuario correspondiente)
    static List<Drawing> drawings = new ArrayList<>();

    // Variable para asignar IDs únicas a los dibujos
    static int id = 0;

    @Override
    public void save(Drawing drawing) {
        // Asignar una ID única al dibujo y agregarlo a la lista
        drawing.setId(id);
        drawings.add(drawing);
        // Incrementar la ID para el próximo dibujo
        id++;
    }

    @Override
    public List<Drawing> loadAllLists() {
        // Devolver la lista completa de dibujos
        return drawings;
    }

    @Override
    public List<Drawing> loadMyList(User user) {
        // Filtrar y devolver la lista de dibujos pertenecientes a un usuario específico
        List<Drawing> myList = new ArrayList<>();
        for (Drawing drawing : drawings) {
            if (drawing.getUser().equals(user)) {
                myList.add(drawing);
            }
        }
        return myList;
    }

    @Override
    public void deleteDrawing(int id, User user) {
        // Eliminar un dibujo si coincide con el ID y el usuario proporcionados
        drawings.removeIf(drawing -> Objects.equals(drawing.getUser().getUsername(), user.getUsername()) && drawing.getId() == id);
    }

    @Override
    public Drawing getDrawing(int id) {
        // Buscar y devolver un dibujo por su ID
        for (Drawing drawing : drawings) {
            if (drawing.getId() == id) {
                return drawing;
            }
        }
        return null;
    }

    @Override
    public void modifyFigures(int id, String figures, String newName, int size) {
        // Modificar las figuras, nombre y tamaño de un dibujo por su ID
        for (Drawing drawing : drawings) {
            if (drawing.getId() == id) {
                drawing.setFigures(figures);
                drawing.setName(newName);
                drawing.setNumFigures(size);
            }
        }
    }
}
