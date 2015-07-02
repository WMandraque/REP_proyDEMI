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
    //public static HashMap<Integer, String> pacientes = new HashMap<Integer, String>();
    public static ArrayList<PacienteModel> pacientes = new ArrayList<PacienteModel>();

    public static final String URL_MEDIKID_LOGIN = "http://192.168.1.40:50/Host/ApoderadoService.svc/LoginApoderado/%s/%s";
    public static final String URL_MEDIKID_GETPACIENTES = "http://192.168.1.40:50/Host/PacienteService.svc/GetPacientesByApoderado/%s";
}
