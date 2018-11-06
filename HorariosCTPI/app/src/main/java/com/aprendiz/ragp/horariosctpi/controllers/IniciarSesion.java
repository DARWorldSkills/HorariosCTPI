package com.aprendiz.ragp.horariosctpi.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.Ambiente;
import com.aprendiz.ragp.horariosctpi.models.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IniciarSesion extends AppCompatActivity {

    EditText txtNombre,txtClave;
    Button btnIniciarSesion;
    Button btnBack;
    List<Usuario> usuarioList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        inizialite();
        consultar();
        salir();
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificar();
            }
        });
    }

    private void salir() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inizialite() {
        txtNombre = findViewById(R.id.txtNombreIS);
        txtClave = findViewById(R.id.txtClaveIS);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnBack = findViewById(R.id.btnBack);
    }

    private void consultar() {

        FirebaseApp.initializeApp(this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ambiente = reference.child("Usuarios");
        ambiente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Usuario>> t = new GenericTypeIndicator<ArrayList<Usuario>>(){};
                usuarioList = dataSnapshot.getValue(t);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void verificar() {
        String nombre = txtNombre.getText().toString();
        String clave = txtClave.getText().toString();
        boolean bandera =false;
        if (nombre.length()>0 && clave.length()>0) {
            if (usuarioList != null) {
                for (int i = 0; i < usuarioList.size(); i++) {
                    if (usuarioList.get(i).getNombre().equals(nombre)){
                        bandera = true;
                        if (usuarioList.get(i).getClave().equals(clave)){
                            i=usuarioList.size();
                            Intent intent = new Intent(IniciarSesion.this,ControlarHorario.class);
                            startActivity(intent);
                            finish();


                        }else{
                            txtClave.setError("Por favor ingrese la clave correspondiente");
                            Toast.makeText(this, "Clave incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if (bandera==false){
                    txtNombre.setError("El usuario no está registrado en la base de datos");
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        }else {
            if (nombre.length()<1){
                txtNombre.setError("Por favor ingresa nombre de usuario");
            }

            if (nombre.length()<1){
                txtClave.setError("Por favor ingresa la clave");
            }
            Toast.makeText(this, "Falta uno o los dos campos por ingresar", Toast.LENGTH_SHORT).show();
        }

    }


}
