package com.amadurell.projecteuf1m08;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.navigation.*;

import com.amadurell.projecteuf1m08.databinding.FragmentUsuariNouBinding;


public class UsuariNou extends Fragment {

    NavController navController;
    private FragmentUsuariNouBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentUsuariNouBinding.inflate(inflater, container, false)).getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.creacompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_usuariNou_to_felicitats);
            }
        });

        binding.atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_usuariNou_to_matrix);
            }
        });
    }
}
