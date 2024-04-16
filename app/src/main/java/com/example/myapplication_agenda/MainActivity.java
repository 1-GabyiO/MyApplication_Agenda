package com.example.myapplication_agenda;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instanciar objetos View
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);
    }

    public void btnGuardar_onClick(View v) {
        String nombre = txtNombre.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String email = txtEmail.getText().toString();

        // Comprobamos que se haya ingresado un nombre antes de guardar
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un nombre para guardar el contacto.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(nombre + "_telefono", telefono);
        editor.putString(nombre + "_email", email);
        editor.commit();

        Toast.makeText(this, "Datos guardados correctamente!", Toast.LENGTH_SHORT).show();

        // Limpiar los campos después de guardar
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }

    public void btnBuscar_onClick(View v) {
        String nombreBusqueda = txtNombre.getText().toString();

        // Comprobamos que se haya ingresado un nombre antes de buscar
        if (nombreBusqueda.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un nombre para buscar el contacto.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);

        String telefono = preferences.getString(nombreBusqueda + "_telefono", null);
        String email = preferences.getString(nombreBusqueda + "_email", null);

        if (telefono != null && email != null) {
            txtTelefono.setText(telefono);
            txtEmail.setText(email);
            Toast.makeText(this, "Datos encontrados correctamente!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Contacto no encontrado.", Toast.LENGTH_LONG).show();
        }
    }
}