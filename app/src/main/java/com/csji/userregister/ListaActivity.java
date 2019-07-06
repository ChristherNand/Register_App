package com.csji.userregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.csji.userregister.adapters.UsuarioAdapter;
import com.csji.userregister.modelos.UsuarioModel;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private TextView tv_listar_contenido;
    private UsuarioAdapter usuarioAdapter;
    private ArrayList<UsuarioModel>usuarioModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        usuarioAdapter = new UsuarioAdapter(getApplicationContext());
        tv_listar_contenido = findViewById(R.id.tv_listar_contenido);

        usuarioAdapter.openR();
        usuarioModels = usuarioAdapter.selectAll();
        usuarioAdapter.close();
        String lista_recorrida = "";
        for(UsuarioModel usuarioModel: usuarioModels){
            lista_recorrida +=
                    "ID:" + usuarioModel.get_id() +
                    ".\nCédula: " + usuarioModel.get_cedula() +
                            ".\nNombre: " + usuarioModel.get_nombre() +
                            ".\nApellido: " + usuarioModel.get_apellido() +
                            ".\n-----------------------------\n";
        }
        tv_listar_contenido.setText(lista_recorrida);
    }
}
