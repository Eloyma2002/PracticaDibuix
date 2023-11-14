package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DrawingDAOImpl implements DrawingDAO {

    static List<Drawing> drawings = new ArrayList<>();
    static int id = 0;

    @Override
    public void save(Drawing dr) {
        dr.setId(id);
        drawings.add(dr);
        id++;
    }

    @Override
    public List<Drawing>
    loadAllLists() {
        return drawings;
    }

    @Override
    public List<Drawing> loadMyList(User user) {

        List<Drawing> myList = new ArrayList<>();
        for (Drawing drawing : drawings) {
            if (drawing.getUser() == user) {
                myList.add(drawing);
            }
        }
        return myList;
    }

    @Override
    public void deleteDrawing(int id, User user) {
        drawings.removeIf(drawing -> Objects.equals(drawing.getUser().getUserName(), user.getUserName()) && drawing.getId() == id);
    }
}
