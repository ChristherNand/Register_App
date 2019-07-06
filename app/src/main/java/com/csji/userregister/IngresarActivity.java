package com.csji.userregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.csji.userregister.adapters.UsuarioAdapter;
import com.csji.userregister.modelos.UsuarioModel;

public class IngresarActivity extends AppCompatActivity {

    private UsuarioAdapter usuarioAdapter;
    private UsuarioModel usuarioModel;
    private EditText et_ingresar_cedula;
    private Button btn_ingresar_ingresar, btn_editar, btn_eliminar;
    private TextView tv_ingresar_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        usuarioAdapter = new UsuarioAdapter(getApplicationContext());
        et_ingresar_cedula = findViewById(R.id.et_ingresar_cedula);
        tv_ingresar_usuario = findViewById(R.id.tv_ingresar_usuario);
        btn_ingresar_ingresar = findViewById(R.id.btn_ingresar_ingresar);
        btn_editar = findViewById(R.id.btn_editar);
        btn_eliminar = findViewById(R.id.btn_eliminar);
        usuarioModel = new UsuarioModel();

        btn_ingresar_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cedula = et_ingresar_cedula.getText().toString();
                usuarioAdapter.openR();
                usuarioModel = usuarioAdapter.selectOne(cedula);
                usuarioAdapter.close();
                String objetoString;
                if(usuarioModel.get_id() < 1){
                    objetoString = "No se encontró ningun usuario con ese valor";
                    btn_editar.setVisibility(View.INVISIBLE);
                    btn_eliminar.setVisibility(View.INVISIBLE);

                }else{
                    objetoString = "ID:" + usuarioModel.get_id() +
                            ".\nCédula: " + usuarioModel.get_cedula() +
                            ".\nNombre: " + usuarioModel.get_nombre() +
                            ".\nApellido: " + usuarioModel.get_apellido() +
                            ".\n-----------------------------\n";
                    btn_editar.setVisibility(View.VISIBLE);
                    btn_eliminar.setVisibility(View.VISIBLE);

                }

                tv_ingresar_usuario.setText(objetoString);

            }
        });
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar = new Intent(IngresarActivity.this,EditarActivity.class);
                editar.putExtra("usuarioModel", usuarioModel);
                startActivity(editar);
            }
        });
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = usuarioModel.get_id();
                usuarioAdapter.openW();
                int resultado = usuarioAdapter.delete(id);
                usuarioAdapter.close();

                if(resultado < 1){
                    Toast.makeText(IngresarActivity.this,"Usuario no eliminado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(IngresarActivity.this,"Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show();
                    tv_ingresar_usuario.setText("Usuario");

                }

            }
        });
    }
}
