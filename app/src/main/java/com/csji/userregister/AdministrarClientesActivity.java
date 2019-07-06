package com.csji.userregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.csji.userregister.adapters.UsuarioAdapter;
import com.csji.userregister.modelos.UsuarioModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AdministrarClientesActivity extends AppCompatActivity {

    private UsuarioAdapter usuarioAdapter;
    private ArrayList<UsuarioModel> usuarioModels;
    private UsuarioModel usuarioModel;
    private ListView lv_administrar_clientes_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_clientes);

        usuarioAdapter = new UsuarioAdapter(getApplicationContext());
        lv_administrar_clientes_lista = findViewById(R.id.lv_administrar_clientes_lista);
        usuarioModels = new ArrayList<>();
        usuarioAdapter.openR();
        usuarioModels = usuarioAdapter.selectAll();
        usuarioAdapter.close();
        lv_administrar_clientes_lista.setAdapter(new UsuarioAdapter(this,usuarioModels));
        lv_administrar_clientes_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //Toast.makeText(AdministrarClientesActivity.this, "Buscando..." ,Toast.LENGTH_SHORT).show();

                usuarioModel = (UsuarioModel) usuarioAdapter.getItem(i);
                Snackbar.make(view, "Buscando...", Snackbar.LENGTH_SHORT).show();

                Snackbar snackbar =  Snackbar.make(view, "Desea editar a " + usuarioModel.get_nombre() + "?", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Si!", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent editar = new Intent(AdministrarClientesActivity.this, EditarActivity.class);
                        editar.putExtra("usuarioModel", usuarioModel);
                        startActivity(editar);
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();



            }
        });
    }
}
