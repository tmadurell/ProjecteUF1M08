package com.amadurell.projecteuf1m08;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amadurell.projecteuf1m08.databinding.FragmentNouContingutBinding;

public class NouContingutFragment extends Fragment {
    private FragmentNouContingutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNouContingutBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ContingutViewModel contingutViewModel = new ViewModelProvider(requireActivity()).get(ContingutViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();
                String imagen = binding.imagen.getText().toString();

                switch (imagen){
                    case "Pikachu.jpg": case "pikachu.jpg": case "Pikachu.JPG": case "pikachu.JPG":
                    case "Pikachu.PNG": case "pikachu.PNG": case "Pikachu.png": case "pikachu.png":
                        contingutViewModel.insertar(new Contingut(R.drawable.upikachu,nombre, descripcion));
                        break;
                    case "Snorlax.jpg": case "snorlax.jpg": case "Snorlax.JPG": case "snorlax.JPG":
                    case "Snorlax.PNG": case "snorlax.PNG": case "Snorlax.png": case "snorlax.png":
                        contingutViewModel.insertar(new Contingut(R.drawable.usnorlax,nombre, descripcion));
                        break;
                    case "Venusaur.JPG": case "venusaur.JPG": case "Venusaur.jpg": case "venusaur.jpg":
                    case "Venusaur.PNG": case "venusaur.PNG": case "Venusaur.png": case "venusaur.png":
                        contingutViewModel.insertar(new Contingut(R.drawable.uvenusaur,nombre, descripcion));
                        break;
                    case "Mr.mime.JPG": case "mr.mime.JPG": case "Mr.Mime.JPG":case "Mr.mime.PNG": case "mr.mime.PNG": case "Mr.Mime.PNG":
                    case "Mr.mime.jpg": case "mr.mime.jng": case "Mr.Mime.jng":case "Mr.mime.png": case "mr.mime.png": case "Mr.Mime.png":
                        contingutViewModel.insertar(new Contingut(R.drawable.umrmime,nombre, descripcion));
                        break;
                    case "Lucario.JPG": case "lucario.JPG":case "Lucario.PNG": case "lucario.PNG":
                    case "Lucario.jpg": case "lucario.jpg":case "Lucario.png": case "lucario.png":
                        contingutViewModel.insertar(new Contingut(R.drawable.ulucario,nombre, descripcion));
                        break;
                    default:
                        contingutViewModel.insertar(new Contingut(R.drawable.misterio,nombre, descripcion));
                        break;
                }

                navController.popBackStack();
            }
        });
    }
}