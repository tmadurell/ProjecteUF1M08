package com.amadurell.projecteuf1m08;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ContingutRepositori {


    ContingutBaseDeDatos.ElementosDao elementosDao;
    Executor executor = Executors.newSingleThreadExecutor();

    ContingutRepositori(Application application){
        elementosDao = ContingutBaseDeDatos.obtenerInstancia(application).obtenerElementosDao();
    }

    public void actualizar(Contingut contingut, float valoracion) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contingut.valoracion = valoracion;
                elementosDao.actualizar(contingut);
            }
        });
    }
    //Model
    LiveData<List<Contingut>> masValorados() {
        return elementosDao.masValorados();
    }

    LiveData<List<Contingut>> obtener(){
        return elementosDao.obtener();
    }

    LiveData<List<Contingut>> buscar(String t) {
        return elementosDao.buscar(t);
    }


    void insertar(Contingut contingut){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDao.insertar(contingut);
            }
        });
    }

    void eliminar(Contingut contingut) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDao.eliminar(contingut);
            }
        });
    }
}
