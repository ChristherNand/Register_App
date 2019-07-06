package com.csji.userregister.adapters;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csji.userregister.R;
import com.csji.userregister.database.DataBaseHelper;
import com.csji.userregister.modelos.UsuarioModel;

import java.util.ArrayList;

public class UsuarioAdapter extends BaseAdapter {
    private static final String BASE_DE_DATOS ="uswdb.db";
    private  static final  int VERSION =1;
    public static final String SCRIPT_CREAR_TABLA = "create table usuario(id integer primary key autoincrement, cedula text, nombre text, apellido text, contrasena text);";
    public static final String SCRIPT_BORRAR_TABLA = "drop table if exists usuario";
    private static final String NOMBRE_TABLA = "usuario";
    private static SQLiteDatabase database;
    private static DataBaseHelper dataBaseHelper;

    private final Context context;

    private UsuarioModel usuarioModel;
    private ArrayList<UsuarioModel>usuarioModels;

    public UsuarioAdapter(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context,BASE_DE_DATOS,null,VERSION);
    }

    public UsuarioAdapter(Context context, ArrayList<UsuarioModel> usuarioModels) {
        this.context = context;
        this.usuarioModels = usuarioModels;
    }

    public void openW(){
        database = dataBaseHelper.getWritableDatabase();
    }
    public void openR(){
        database = dataBaseHelper.getReadableDatabase();
    }
    public void close(){
        database.close();
    }
    public boolean insert(UsuarioModel usuarioModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cedula",usuarioModel.get_cedula());
        contentValues.put("nombre",usuarioModel.get_nombre());
        contentValues.put("apellido",usuarioModel.get_apellido());
        contentValues.put("contrasena",usuarioModel.get_contrasena());

        long result = database.insert(NOMBRE_TABLA,null,contentValues);
        if(result > 0){
            return true;
        }else {
            return false;
        }

    }

    public int delete(int id){
        String where = "id=?";
        String parameter = String.valueOf(id);
        return database.delete(NOMBRE_TABLA, where, new String[]{parameter});

    }
    public UsuarioModel selectOne(String cedula){
        usuarioModel = new UsuarioModel();
        String where = "cedula=?";
        Cursor cursor = database.query(NOMBRE_TABLA,null,where, new String[]{cedula},null, null, null);
        if(cursor.getCount() < 1){
            return usuarioModel;
        }
        cursor.moveToFirst();
        usuarioModel.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
        usuarioModel.set_cedula(cursor.getString(cursor.getColumnIndex("cedula")));
        usuarioModel.set_nombre(cursor.getString(cursor.getColumnIndex("nombre")));
        usuarioModel.set_apellido(cursor.getString(cursor.getColumnIndex("apellido")));
        usuarioModel.set_contrasena(cursor.getString(cursor.getColumnIndex("contrasena")));
        return usuarioModel;
    }
    public ArrayList<UsuarioModel> selectAll(){
        usuarioModels = new ArrayList<>();

        Cursor cursor = database.query(NOMBRE_TABLA,null,null, null,null, null, null);
        if(cursor.getCount() < 1){
            return usuarioModels;
        }
        cursor.moveToFirst();
        do{
            usuarioModel = new UsuarioModel();
            usuarioModel.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            usuarioModel.set_cedula(cursor.getString(cursor.getColumnIndex("cedula")));
            usuarioModel.set_nombre(cursor.getString(cursor.getColumnIndex("nombre")));
            usuarioModel.set_apellido(cursor.getString(cursor.getColumnIndex("apellido")));
            usuarioModel.set_contrasena(cursor.getString(cursor.getColumnIndex("contrasena")));
            usuarioModels.add(usuarioModel);
        }while (cursor.moveToNext());

        return usuarioModels;
    }
    public void update(UsuarioModel usuarioModel){
        String where = "cedula=?";
        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre",usuarioModel.get_nombre());
        contentValues.put("apellido",usuarioModel.get_apellido());
        contentValues.put("contrasena",usuarioModel.get_contrasena());
        database.update(NOMBRE_TABLA,contentValues,where, new String[]{usuarioModel.get_cedula()});

    }

    @Override
    public int getCount() {
        return usuarioModels.size();
    }

    @Override
    public Object getItem(int i) {
        return usuarioModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem = view;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewItem = layoutInflater.inflate(R.layout.item_usuario_lv, viewGroup, false);
        }
        TextView tv_item_usuario_lv_nombre = viewItem.findViewById(R.id.tv_item_usuario_lv_nombre);
        TextView tv_item_usuario_lv_cedula = viewItem.findViewById(R.id.tv_item_usuario_lv_cedula);

        usuarioModel = (UsuarioModel) getItem(i);
        tv_item_usuario_lv_nombre.setText("Usuario: " + usuarioModel.get_id() + ". " + usuarioModel.get_nombre() + " " + usuarioModel.get_apellido());
        tv_item_usuario_lv_cedula.setText("Cédula: " + usuarioModel.get_cedula());

        return  viewItem;
    }
}
