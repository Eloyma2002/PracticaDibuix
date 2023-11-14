package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Model.Drawing;

import java.util.List;

public interface ViewDAO {

    String getDrawing(int id, List<Drawing> allDrawings);
}
