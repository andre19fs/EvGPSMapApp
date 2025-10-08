package com.andres.gpsmapapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Main activity que muestra el mapa, e implementa OnMapReadyCallback para manejar el mapa cuando esté listo.
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private boolean agregarMarcadorActivo = false;
    private FloatingActionButton btnAgregarMarcador;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100; //identificamos la solicitud de permiso
    private GoogleMap mMap; //objeto GoogleMap para interactuar con el mapa
    private FusedLocationProviderClient fusedLocationClient; //cliente de google para obtener la ubicación del dispositivo


    /*
    * cargamos el activity_main con el fragment del mapa, verificamos los permisos de ubicación
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnAgregarMarcador = findViewById(R.id.btnAgregarMarcador);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this); //inicializamos el cliente de ubicación de google

        checkLocationPermission();

        //Agregamos un listener al botón para activar o desactivar el modo de agregar marcador
        btnAgregarMarcador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarMarcadorActivo = !agregarMarcadorActivo;

                if (agregarMarcadorActivo) {
                    Toast.makeText(MainActivity.this, "Toca el mapa para agregar un marcador", Toast.LENGTH_SHORT).show();
                    btnAgregarMarcador.setImageResource(android.R.drawable.ic_menu_close_clear_cancel); // Cambia el ícono para salir del modo
                } else {
                    Toast.makeText(MainActivity.this, "Modo de agregar marcador desactivado", Toast.LENGTH_SHORT).show();
                    btnAgregarMarcador.setImageResource(android.R.drawable.ic_input_add); // Regresa al ícono original
                }
            }
        });
    }

    /*
    * Verificamos si se dio permiso de ubicacion, si no lo está, lo pedimos, y si lo tiene activamos la ubicación del usuario
    */
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            enableUserLocation();
        }
    }


    /*
    * activamos el boton para reubicar la ubicación del usuario en el mapa con getCurrentLocation()
    */
    private void enableUserLocation() {
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                getCurrentLocation();
            }
        }
    }


    /*
    * obtenemos la ubicación actual del usuario y movemos la cámara del mapa a esa ubicación, con un marcador
    */
    private void getCurrentLocation() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                            mMap.addMarker(new MarkerOptions().position(currentLatLng).title("tu ubicacion"));
                        }
                    }
                });
    }


    /*
    * cuando el mapa se carga llamamos a enableUserLocation() para activar la ubicación del usuario y agregamos un listener para detectar toques en el mapa y agregar marcadores si el modo está activo
    * */
    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
       enableUserLocation();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (agregarMarcadorActivo) {
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(latLng)
                            .title("Marcador personalizado");

                    mMap.addMarker(markerOptions);
                }
            }
        });
    }


    /*
    * cuando el usuario responde la solicitud de permiso, verificamos si acepto o no, si si, activa la ubicacion y si no, muestra un mensaje
    * */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}