package com.amadurell.projecteuf1m08;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerBusquedaFragment extends com.amadurell.projecteuf1m08.RecyclerContenidoFragment {
    @Override
    LiveData<List<com.amadurell.projecteuf1m08.Contingut>> obtenerElementos() {
        return contingutViewModel.buscar();
    }
}
