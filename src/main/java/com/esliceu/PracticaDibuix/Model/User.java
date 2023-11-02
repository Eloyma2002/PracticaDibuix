package com.esliceu.PracticaDibuix.Model;

public class User {

    private String nomUsuari;
    private String nomICognoms;
    private String password;

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getNomICognoms() {
        return nomICognoms;
    }

    public void setNomICognoms(String nomICognoms) {
        this.nomICognoms = nomICognoms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
