package com.mandrake.model;

/**
 * Created by EDSON on 28/06/2015.
 */
public class AtencionModel {

    private int Id;
    private String Medico; // Nombre + Apellido Paterno
    private String FechaAtencion;
    private String Diagnostico, Receta;

    //region Getters & Setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMedico() {
        return Medico;
    }

    public void setMedico(String medico) {
        Medico = medico;
    }

    public String getFechaAtencion() {
        return FechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        FechaAtencion = fechaAtencion;
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        Diagnostico = diagnostico;
    }

    public String getReceta() {
        return Receta;
    }

    public void setReceta(String receta) {
        Receta = receta;
    }
    //endregion

}
