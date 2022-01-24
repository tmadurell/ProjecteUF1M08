package com.amadurell.projecteuf1m08;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class CalculoViewModel extends AndroidViewModel {

    Executor executor;

    CalculoExperiencia simulador;

    MutableLiveData<Double> tiempo = new MutableLiveData<>();
    MutableLiveData<Integer> errorExperiencia = new MutableLiveData<>();
    MutableLiveData<Integer> errorNiveles = new MutableLiveData<>();
    MutableLiveData<Boolean> calculando = new MutableLiveData<>();

    public CalculoViewModel(@NonNull Application application) {
        super(application);

        executor = Executors.newSingleThreadExecutor();
        simulador = new CalculoExperiencia();
    }

    public void calcular(double capital, int plazo) {

        final CalculoExperiencia.Calculo calculo = new CalculoExperiencia.Calculo(capital, plazo);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                simulador.calcular(calculo, new CalculoExperiencia.Callback() {

                    @Override
                    public void cuandoEsteCalculadaEltiempo(double tiempo) {
                        errorExperiencia.postValue(null);
                        errorNiveles.postValue(null);
                        CalculoViewModel.this.tiempo.postValue(tiempo);
                    }

                    @Override
                    public void cuandoHayaErrorDeDuracionInferiorAlMinimo(double duracionMinima) {
                        errorNiveles.postValue((int) duracionMinima);
                    }

                    @Override
                    public void cuandoHayaErrorDeCapituloInferiorAlMinimo(int capituloMinimo) {
                        errorExperiencia.postValue(capituloMinimo);
                    }

                    @Override
                    public void cuandoEmpieceElCalculo() {
                        calculando.postValue(true);
                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        calculando.postValue(false);
                    }
                });
            }
        });
    }
}
