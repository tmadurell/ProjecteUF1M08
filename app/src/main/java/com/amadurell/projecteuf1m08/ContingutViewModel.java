package com.amadurell.projecteuf1m08;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

public class ContingutViewModel extends AndroidViewModel {

    ContingutRepositori contingutRepositori;


    MutableLiveData<Contingut> elementoSeleccionado = new MutableLiveData<>();

    //ViewModel
    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

    LiveData<List<Contingut>> resultatsBusqueda = Transformations.switchMap(terminoBusqueda, new Function<String, LiveData<List<Contingut>>>() {
        @Override
        public LiveData<List<Contingut>> apply(String input) {
            return contingutRepositori.buscar(input);
        }
    });

    public ContingutViewModel(@NonNull Application application) {
        super(application);

        contingutRepositori = new ContingutRepositori(application);
    }

    //ViewModel
    LiveData<List<Contingut>> masValorados(){
        return contingutRepositori.masValorados();
    }

    LiveData<List<Contingut>> buscar(){
        return resultatsBusqueda;
    }

    LiveData<List<Contingut>> obtener(){
        return contingutRepositori.obtener();
    }



    void insertar(Contingut contingut){
        contingutRepositori.insertar(contingut);
    }

    void eliminar(Contingut contingut){
        contingutRepositori.eliminar(contingut);
    }

    void seleccionar(Contingut contingut){
        elementoSeleccionado.setValue(contingut);
    }

    void actualizar(Contingut contingut, float valoracion){
        contingutRepositori.actualizar(contingut, valoracion);
    }

    MutableLiveData<Contingut> seleccionado(){
        return elementoSeleccionado;
    }

}
