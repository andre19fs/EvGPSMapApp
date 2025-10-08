package com.andres.gpsmapapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImagenActivity extends AppCompatActivity {


    ImageView imageView;

    Button btnCargar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagen);

        imageView = findViewById(R.id.imageView);
        btnCargar = findViewById(R.id.btnCargar);
        btnVolver = findViewById(R.id.btnVolver);

        btnCargar.setOnClickListener((view) -> {
            cargarImagenEnHiloSecundario();
        });

        btnVolver.setOnClickListener((view) -> {
            finish();
        });
    }

    // Metodo para cargar una imagen desde una URL
    private Bitmap loadImageFromNetwork(String urlString) {
        try {
            java.net.URL url = new java.net.URL(urlString);
            java.io.InputStream input = url.openStream();
            return android.graphics.BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Metodo para cargar la imagen en un hilo secundario y actualizar la UI en el hilo principal
    public void cargarImagenEnHiloSecundario() {
        new Thread(() -> {
            final Bitmap bitmap = loadImageFromNetwork("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Phoenix_A_compared_to_Ton_618_and_the_Orbit_of_Neptune.jpg/250px-Phoenix_A_compared_to_Ton_618_and_the_Orbit_of_Neptune.jpg");
            imageView.post(() -> {
                imageView.setImageBitmap(bitmap);
            });
        }).start();
    }
}
