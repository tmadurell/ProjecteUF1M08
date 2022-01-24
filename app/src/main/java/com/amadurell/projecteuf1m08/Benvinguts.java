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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amadurell.projecteuf1m08.databinding.BenvingutsBinding;
import com.bumptech.glide.Glide;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Benvinguts extends Fragment {

    NavController navController;
    com.amadurell.projecteuf1m08.Arranque arranque;
    private BenvingutsBinding binding;
    Executor executor = Executors.newSingleThreadExecutor();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = BenvingutsBinding.inflate(inflater, container, false)).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arranque = new ViewModelProvider(requireActivity()).get(com.amadurell.projecteuf1m08.Arranque.class);
        navController = Navigation.findNavController(view);

        Glide.with(Benvinguts.this).load(R.drawable.loading).into(binding.carregant);

        arranque.finishedLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                navController.navigate(R.id.action_benvingut_to_matrix);
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // simular la carga de recursos

                    Thread.sleep(3000);
                    arranque.finishedLoading.postValue(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}