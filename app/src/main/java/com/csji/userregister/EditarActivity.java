package com.csji.userregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.csji.userregister.adapters.UsuarioAdapter;
import com.csji.userregister.modelos.UsuarioModel;

public class EditarActivity extends AppCompatActivity {

    private EditText et_editar_nombre, et_editar_apellido, et_editar_contrasena;
    private Button btn_editar_guardar;
    private UsuarioModel usuarioModel;
    private UsuarioAdapter usuarioAdapter;
    private String nombre, apellido, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        et_editar_nombre=findViewById(R.id.et_editar_nombre);
        et_editar_apellido=findViewById(R.id.et_editar_apellido);
        et_editar_contrasena=findViewById(R.id.et_editar_contrasena);
        btn_editar_guardar = findViewById(R.id.btn_editar_guardar);
        usuarioAdapter = new UsuarioAdapter(getApplicationContext());
        usuarioModel = new UsuarioModel();

        usuarioModel = (UsuarioModel) getIntent().getSerializableExtra("usuarioModel");
        nombre = usuarioModel.get_nombre();
        apellido = usuarioModel.get_apellido();
        contrasena = usuarioModel.get_contrasena();

        et_editar_nombre.setText(nombre);
        et_editar_apellido.setText(apellido);
        et_editar_contrasena.setText(contrasena);
        btn_editar_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = et_editar_nombre.getText().toString();
                apellido = et_editar_apellido.getText().toString();
                contrasena = et_editar_contrasena.getText().toString();

                usuarioModel.set_nombre(nombre);
                usuarioModel.set_apellido(apellido);
                usuarioModel.set_contrasena(contrasena);
                usuarioAdapter.openW();
                usuarioAdapter.update(usuarioModel);
                usuarioAdapter.close();

                Toast.makeText(EditarActivity.this,"Actualización exitosa", Toast.LENGTH_SHORT).show();
                Intent lista = new Intent(EditarActivity.this, ListaActivity.class);
                startActivity(lista);
            }
        });
    }
}
