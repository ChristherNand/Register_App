package com.csji.userregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.csji.userregister.adapters.UsuarioAdapter;
import com.csji.userregister.modelos.UsuarioModel;

public class RegistroActivity extends AppCompatActivity {


    private EditText et_registro_cedula,et_registro_nombre,et_registro_apellido,et_registro_contrasena;
    private Switch sw_registro_terminos;
    private Button btn_registro_guardar;
    private UsuarioAdapter usuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuarioAdapter = new UsuarioAdapter(getApplicationContext());

        et_registro_cedula = findViewById(R.id.et_registro_cedula);
        et_registro_nombre = findViewById(R.id.et_registro_nombre);
        et_registro_apellido = findViewById(R.id.et_registro_apellido);
        et_registro_contrasena = findViewById(R.id.et_registro_contrasena);
        sw_registro_terminos = findViewById(R.id.sw_registro_terminos);
        btn_registro_guardar = findViewById(R.id.btn_registro_guardar);

        btn_registro_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cedula = et_registro_cedula.getText().toString();
                String nombre = et_registro_nombre.getText().toString();
                String apellido = et_registro_apellido.getText().toString();
                String contrasena = et_registro_contrasena.getText().toString();
                boolean terminos = sw_registro_terminos.isChecked();
                if(cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contrasena.isEmpty()){
                    Toast.makeText(RegistroActivity.this, "Por favor ingresar todos los campos", Toast.LENGTH_SHORT).show();
                }else if(!terminos){
                    Toast.makeText(RegistroActivity.this, "Por favor aceptar terminos y condiciones", Toast.LENGTH_SHORT).show();
                }else{
                    UsuarioModel usuarioModel = new UsuarioModel(cedula, nombre, apellido, contrasena);
                    usuarioAdapter.openW();
                    boolean insert = usuarioAdapter.insert(usuarioModel);
                    usuarioAdapter.close();
                    if(insert){
                        Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    }else{
                        Toast.makeText(RegistroActivity.this, "Registro no exitoso", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    private  void limpiarCampos(){
        et_registro_cedula.setText("");
        et_registro_nombre.setText("");
        et_registro_apellido.setText("");
        et_registro_contrasena.setText("");
        sw_registro_terminos.setChecked(false);
        et_registro_cedula.requestFocus();
    }
}
