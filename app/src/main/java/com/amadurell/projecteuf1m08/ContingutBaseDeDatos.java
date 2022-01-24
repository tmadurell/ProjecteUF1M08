package com.amadurell.projecteuf1m08;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {Contingut.class}, version = 1, exportSchema = false)
public abstract class ContingutBaseDeDatos extends RoomDatabase {

    public abstract ElementosDao obtenerElementosDao();

    private static volatile ContingutBaseDeDatos INSTANCIA;

    static ContingutBaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (ContingutBaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                            ContingutBaseDeDatos.class, "contingut.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);

                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

    @Dao
    interface ElementosDao {
        @Query("SELECT * FROM Contingut")
        LiveData<List<Contingut>> obtener();

        @Insert
        void insertar(Contingut champion);

        @Update
        void actualizar(Contingut champion);

        @Delete
        void eliminar(Contingut champion);

        @Query("SELECT * FROM Contingut WHERE nom LIKE '%' || :t || '%'")
        LiveData<List<Contingut>> buscar(String t);
        //Model
        @Query("SELECT * FROM Contingut ORDER BY valoracion DESC")
        LiveData<List<Contingut>> masValorados();

    }
}

