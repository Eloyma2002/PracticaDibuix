package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Model.Drawing;

import java.util.List;

public interface DrawingDAO {

    void save (Drawing dr);

    List<Drawing> loadAllLists ();

}
