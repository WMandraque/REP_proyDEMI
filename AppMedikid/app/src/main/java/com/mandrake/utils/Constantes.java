package com.mandrake.utils;

import com.mandrake.model.ApoderadoModel;
import com.mandrake.model.PacienteModel;

import java.util.ArrayList;

/**
 * Created by Mandrake on 27/06/2015.
 */
public class Constantes
{
    public static ApoderadoModel apoderado = new ApoderadoModel();
    public static ArrayList<PacienteModel> pacientes = new ArrayList<PacienteModel>();

    public static final String URL_MEDIKID_LOGIN = "http://192.168.1.33:50/Host/ApoderadoService.svc/LoginApoderado/%s/%s";
    public static final String URL_MEDIKID_GETPACIENTES = "http://192.168.1.33:50/Host/PacienteService.svc/GetPacientesByApoderado/%s";
    public static final String URL_MEDIKID_GETATENCIONES = "http://192.168.1.33:50/Host/AtencionService.svc/GetAtencionesByPaciente/%s";

    public static final String DESC_MEDICO = "Médico";
    public static final String DESC_FECHAATENCION = "F. Atención";
    public static final String DESC_RECETA = "Receta";
    public static final String DESC_DIAGNOSTICO = "Diagnóstico";
}
