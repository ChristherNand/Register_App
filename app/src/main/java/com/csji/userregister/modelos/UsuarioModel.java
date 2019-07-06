package com.csji.userregister.modelos;

import java.io.Serializable;

public class UsuarioModel implements Serializable {
    private int _id;
    private String _cedula;
    private String _nombre;
    private String _apellido;
    private String _contrasena;

    public UsuarioModel() {
    }

    public UsuarioModel(String _cedula, String _nombre, String _apellido, String _contrasena) {
        this._cedula = _cedula;
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._contrasena = _contrasena;
    }

    public UsuarioModel(int _id, String _cedula, String _nombre, String _apellido, String _contrasena) {
        this._id = _id;
        this._cedula = _cedula;
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._contrasena = _contrasena;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "_id=" + _id +
                ", _cedula='" + _cedula + '\'' +
                ", _nombre='" + _nombre + '\'' +
                ", _apellido='" + _apellido + '\'' +
                ", _contrasena='" + _contrasena + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_cedula() {
        return _cedula;
    }

    public void set_cedula(String _cedula) {
        this._cedula = _cedula;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_apellido() {
        return _apellido;
    }

    public void set_apellido(String _apellido) {
        this._apellido = _apellido;
    }

    public String get_contrasena() {
        return _contrasena;
    }

    public void set_contrasena(String _contrasena) {
        this._contrasena = _contrasena;
    }
}
