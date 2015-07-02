package com.mandrake.model;

/**
 * Created by Mandrake on 22/06/2015.
 */
public class ApoderadoModel
{
    private int Id;
    private int IdApoderado;
    private String DNI, NombreCompleto;
    private String Email, Contrasena;

    public ApoderadoModel(){}

    public ApoderadoModel(int id, int idApoderado, String DNI, String nombreCompleto, String email, String contrasena) {
        Id = id;
        IdApoderado = idApoderado;
        this.DNI = DNI;
        NombreCompleto = nombreCompleto;
        Email = email;
        Contrasena = contrasena;
    }

    //region Getters & Setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdApoderado() {
        return IdApoderado;
    }

    public void setIdApoderado(int idApoderado) {
        IdApoderado = idApoderado;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }
    //endregion

}
