package com.cdlc.p2andersonm;

public class DefDB {
    public static final String nameDb = "Persona";
    public static final String tabla_persona = "persona";
    public static final String col_codigo = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_estrato = "estrato";
    public static final String col_salario = "salario";
    public static final String col_educacion = "nivelEducativo";



    public static final String create_tabla_persona = "CREATE TABLE IF NOT EXISTS persona(" +
            "  codigo text primary key," +
            "  nombre text," +
            "  salario real," +
            "  estrato integer," +
            "  nivelEducativo text" +
            ");";
}
