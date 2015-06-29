package com.mandrake.utils;

/**
 * Created by EDSON on 28/06/2015.
 */
public class DBConstantes {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "bdmedikid.db";

    public static final String TB_APODERADO = "tb_apoderado";

    //region Columnas
    private static final String C_ID = "id";
    private static final String C_IDEMP = "idempleado";
    private static final String C_DNI = "dni";
    private static final String C_NOMBRE = "nombre";
    private static final String C_EMAIL = "email";
    private static final String C_CLAVE = "clave";
    //endregion

    //region Columnas Tabla Apoderado
    private static final String APODERADO_ID = String.format("%s INTEGER PRIMARY KEY AUTOINCREMENT", C_ID);
    private static final String APODERADO_IDEMP = String.format("%s INTEGER", C_IDEMP);
    private static final String APODERADO_DNI = String.format("%s TEXT", C_DNI);
    private static final String APODERADO_NOMBRE = String.format("%s TEXT", C_NOMBRE);
    private static final String APODERADO_EMAIL = String.format("%s TEXT", C_EMAIL);
    private static final String APODERADO_CLAVE = String.format("%s TEXT", C_CLAVE);
    //endregion

    public static final String APODERADO_COLS = String.format("%s, %s, %s, %s, %s", C_IDEMP,
                                                                C_DNI, C_NOMBRE, C_EMAIL, C_CLAVE);
    public static final String CREATE_APODERADO_COLS = String.format("%s, %s, %s, %s, %s",
                                                                        APODERADO_IDEMP,
                                                                        APODERADO_DNI, APODERADO_NOMBRE,
                                                                        APODERADO_EMAIL, APODERADO_CLAVE);

    public static final String CREATE_TBAPODERADO = String.format("CREATE TABLE %s (%s, %s)",
                                                                    TB_APODERADO, APODERADO_ID, CREATE_APODERADO_COLS);
    public static final String DROP_TBAPODERADO = String.format("DROP TABLE %s", TB_APODERADO);

    /*
    public String CREATE_TABLE(String nombreTabla, String[] campos, String[] tipos)
    {
        String mensaje = "";
        boolean valida = (campos.length == tipos.length);

        if (valida)
        {
            boolean tieneId = Arrays.asList(campos).contains("id");

            StringBuilder columnas = new StringBuilder(tieneId ? "" : C_ID);

            for(int i=0; i<campos.length; i++)
            {
                columnas.append(campos[i]);
            }
        }

        return mensaje;
    }
    */

}
