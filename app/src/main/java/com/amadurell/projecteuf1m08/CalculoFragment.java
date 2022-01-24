package com.amadurell.projecteuf1m08;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.amadurell.projecteuf1m08.databinding.FragmentRecyclerCalculoBinding;

public class CalculoFragment extends Fragment {
    private FragmentRecyclerCalculoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerCalculoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final CalculoViewModel calculoViewModel = new ViewModelProvider(this).get(CalculoViewModel.class);

        binding.calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;

                double duracion = 0;
                int capitulos = 0;

                try {
                    duracion = Double.parseDouble(binding.experiencia.getText().toString());
                } catch (Exception e){
                    binding.experiencia.setError("Error: Introduzca un valor. Por favor");
                    error = true;
                }

                try {
                    capitulos = Integer.parseInt(binding.niveles.getText().toString());
                } catch (Exception e){
                    binding.niveles.setError("Error: Introduzca un valor. Por favor");
                    error = true;
                }

                if (!error) {
                    calculoViewModel.calcular(duracion, capitulos);
                }
            }
        });

        calculoViewModel.tiempo.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double experiencia) {
                binding.tiempo.setText(String.format("%.2f",experiencia)+ " experiencia en total");
            }
        });

        calculoViewModel.errorExperiencia.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer duracionMinima) {
                if (duracionMinima != null) {
                    binding.experiencia.setError("Error");
                } else {
                    binding.niveles.setError(null);
                }
            }
        });

        calculoViewModel.errorNiveles.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer capitulosMinimo) {
                if (capitulosMinimo != null) {
                    binding.niveles.setError("Error");
                } else {
                    binding.experiencia.setError(null);
                }
            }
        });

        calculoViewModel.calculando.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean calculando) {
                if (calculando) {
                    binding.calculando.setVisibility(View.VISIBLE);
                    binding.tiempo.setVisibility(View.GONE);
                } else {
                    binding.calculando.setVisibility(View.GONE);
                    binding.tiempo.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}