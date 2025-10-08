package com.andres.gpsmapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnMapa;
    Button btnImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnMapa = findViewById(R.id.btnMapa);
        btnImagen = findViewById(R.id.btnImagen);

        //agregamos los listeners a los botones para abrir las actividades
        btnMapa.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);

        });

        btnImagen.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ImagenActivity.class);
            startActivity(intent);

        });

    }
}
