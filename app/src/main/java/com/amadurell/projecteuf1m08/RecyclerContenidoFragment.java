package com.amadurell.projecteuf1m08;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;


import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.amadurell.projecteuf1m08.databinding.ViewholderContingutBinding;
import com.amadurell.projecteuf1m08.databinding.FragmentRecyclerContenidoBinding;

import java.util.List;


public class RecyclerContenidoFragment extends Fragment {

    private FragmentRecyclerContenidoBinding binding;
    ContingutViewModel contingutViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerContenidoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contingutViewModel = new ViewModelProvider(requireActivity()).get(ContingutViewModel.class);
        navController = Navigation.findNavController(view);

        binding.iraNouContingut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nouContenidoFragment);
            }
        });

        ElementosAdapter elementosAdapter;
        elementosAdapter = new ElementosAdapter();

        binding.recyclerView.setAdapter(elementosAdapter);

        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Contingut contingut = elementosAdapter.obtenerElemento(posicion);
                contingutViewModel.eliminar(contingut);

            }
        }).attachToRecyclerView(binding.recyclerView);

        obtenerElementos().observe(getViewLifecycleOwner(), new Observer<List<Contingut>>() {
            @Override
            public void onChanged(List<Contingut> Contingut) {
                elementosAdapter.establecerLista(Contingut);
            }
        });
    }

    LiveData<List<Contingut>> obtenerElementos(){
        return contingutViewModel.obtener();
    }

    class ElementosAdapter extends RecyclerView.Adapter<ContingutViewHolder> {

        List<Contingut> Contingut;

        @NonNull
        @Override
        public ContingutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ContingutViewHolder(ViewholderContingutBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ContingutViewHolder holder, int position) {

            Contingut contingut = Contingut.get(position);

            holder.binding.nom.setText(contingut.nom);
            holder.binding.valoracion.setRating(contingut.valoracion);

            Glide.with(RecyclerContenidoFragment.this).load(contingut.imagen).into(holder.binding.imagen);

            holder.binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser) {
                        contingutViewModel.actualizar(contingut, rating);
                    }
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contingutViewModel.seleccionar(contingut);
                    navController.navigate(R.id.action_mostrarContenidoFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return Contingut != null ? Contingut.size() : 0;
        }

        public void establecerLista(List<Contingut> Contingut){
            this.Contingut = Contingut;
            notifyDataSetChanged();
        }

        public Contingut obtenerElemento(int posicion){
            return Contingut.get(posicion);
        }
    }

    static class ContingutViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderContingutBinding binding;

        public ContingutViewHolder(ViewholderContingutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}