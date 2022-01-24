package com.amadurell.projecteuf1m08;

import androidx.room.*;

@Entity
public class Contingut {
    @PrimaryKey(autoGenerate = true)
    int id;

    int imagen;
    String nom;
    String descripcio;
    float valoracion;

    public Contingut(int imagen, String nom, String descripcio) {
        this.nom = nom;
        this.descripcio = descripcio;
        this.imagen = imagen;

    }
}
