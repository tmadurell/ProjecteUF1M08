package com.amadurell.projecteuf1m08;

public class CalculoExperiencia {

    public static class Calculo {
        public double experiencia;
        public int niveles;

        public Calculo(double duracion, int niveles) {
            this.experiencia = experiencia;
            this.niveles = niveles;
        }
    }

    interface Callback {
        void cuandoEsteCalculadaEltiempo(double tiempo);
        void cuandoHayaErrorDeDuracionInferiorAlMinimo(double duracionMinima);
        void cuandoHayaErrorDeCapituloInferiorAlMinimo(int capituloMinimo);
        void cuandoEmpieceElCalculo();
        void cuandoFinaliceElCalculo();
    }

    public void calcular(Calculo calculo, Callback callback) {
        int nivelesMinimo = 0;
        double experienciaMinima = 0;
        

        callback.cuandoEmpieceElCalculo();


        try {
            // simular operacion de larga duracion (3s)
            Thread.sleep(3000);
            //El total de episodios que vas a ver en total
            nivelesMinimo = 1;
            //El tiempo que dura un capitulo promedio no incluye especiales, en minutos
            experienciaMinima = 1;

        } catch (InterruptedException e) {}
        boolean error = false;
        if (calculo.experiencia < experienciaMinima) {
            callback.cuandoHayaErrorDeDuracionInferiorAlMinimo(experienciaMinima);
            error = true;
        }

        if (calculo.niveles < nivelesMinimo) {
            callback.cuandoHayaErrorDeCapituloInferiorAlMinimo(nivelesMinimo);
            error = true;
        }

        if (calculo.niveles < nivelesMinimo) {
            callback.cuandoHayaErrorDeCapituloInferiorAlMinimo(nivelesMinimo);
            error = true;
        }

        if(!error) {
            //Formula matematica IMPORTANTE
            callback.cuandoEsteCalculadaEltiempo( (calculo.experiencia * calculo.niveles) );
        }

        callback.cuandoFinaliceElCalculo();
    }
}
