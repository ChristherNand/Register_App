package com.csji.userregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_registro ,btn_listar, btn_ingresar, btn_main_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_registro = findViewById(R.id.btn_registro);
        btn_listar = findViewById(R.id.btn_lista);
        btn_ingresar = findViewById(R.id.btn_ingresar);
        btn_main_list_view = findViewById(R.id.btn_main_list_view);

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(registro);
            }
        });
        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lista = new Intent(MainActivity.this, ListaActivity.class);
                startActivity(lista);
            }
        });
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingresar = new Intent(MainActivity.this, IngresarActivity.class);
                startActivity(ingresar);
            }
        });
        btn_main_list_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listView = new Intent(MainActivity.this, AdministrarClientesActivity.class);
                startActivity(listView);
            }
        });
    }
}
