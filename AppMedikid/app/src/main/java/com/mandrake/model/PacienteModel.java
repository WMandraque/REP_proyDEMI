package com.mandrake.model;

/**
 * Created by EDSON on 28/06/2015.
 */
public class PacienteModel {

    private int Id;
    private int IdPaciente;
    private String NombreCompleto;  // Nombre + ApellidoPaterno

    public PacienteModel() {
    }

    public PacienteModel(int id, int idPaciente, String nombreCompleto) {
        Id = id;
        IdPaciente = idPaciente;
        NombreCompleto = nombreCompleto;
    }

    //region Getters & Setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        IdPaciente = idPaciente;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }
    //endregion


    @Override
    public String toString() {
        return this.NombreCompleto;
    }
}
