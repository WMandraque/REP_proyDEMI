package com.mandrake.model;

/**
 * Created by Mandrake on 22/06/2015.
 */
public class ApoderadoDTO
{

    private int idApoderado;
    private String email, contrasena;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdApoderado()
    {
        return idApoderado;
    }

    public void setIdApoderado(int idApoderado)
    {
        this.idApoderado = idApoderado;
    }

}
