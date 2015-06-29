package com.mandrake.model;

import java.util.Date;

/**
 * Created by EDSON on 28/06/2015.
 */
public class CitaModel {

    private int Id;
    private int IdApoderado, IdPaciente;
    private Date FechaCita;
    private String Motivo;

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

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        IdPaciente = idPaciente;
    }

    public Date getFechaCita() {
        return FechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        FechaCita = fechaCita;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }
    //endregion

}
