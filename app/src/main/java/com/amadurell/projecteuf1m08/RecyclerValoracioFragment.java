package com.amadurell.projecteuf1m08;


import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerValoracioFragment extends com.amadurell.projecteuf1m08.RecyclerContenidoFragment {
    @Override
    LiveData<List<Contingut>> obtenerElementos() {
        return contingutViewModel.masValorados();
    }

}