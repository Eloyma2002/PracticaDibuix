package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Model.Drawing;
import com.esliceu.PracticaDibuix.Model.User;

import java.util.List;

public interface DrawingDAO {

    void save (Drawing dr);

    List<Drawing> loadAllLists ();

    List<Drawing> loadMyList(User user);

    void deleteDrawing(int id, User user);

    Drawing getDrawing(int id);

    void modifyFigures(int id, String figures, String newName, int size);


}
