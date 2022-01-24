package com.amadurell.projecteuf1m08;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.amadurell.projecteuf1m08.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());


        //ExViewModel = new ViewModelProvider(this).get(ExViewModel.class);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                // Top-level destinations:
                R.id.homeFragment,R.id.recyclerContenidoFragment,R.id.recyclerBusquedaFragment,R.id.recyclerValoracioFragment
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {

            if(destination.getId() == R.id.benvinguts
            || destination.getId() == R.id.usuaris
            || destination.getId()  == R.id.usuariExistent
            || destination.getId()  == R.id.usuariNou
            || destination.getId()  == R.id.felicitats

            ){
                binding.bottomNavView.setVisibility(View.GONE);
                binding.navView.setVisibility(View.GONE);
                binding.toolbar.setVisibility(View.GONE);

            } else {
                binding.bottomNavView.setVisibility(View.VISIBLE);
                binding.navView.setVisibility(View.VISIBLE);
                binding.toolbar.setVisibility(View.VISIBLE);
            }
            if (destination.getId() == R.id.recyclerBusquedaFragment){
                binding.searchView.setVisibility(View.VISIBLE);
                binding.searchView.setIconified(false);
                binding.searchView.requestFocusFromTouch();
            } else {
                binding.searchView.setVisibility(View.GONE);
            }

        });



    }

}