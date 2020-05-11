package com.cdlc.p2andersonm;

public class Persona {
    String nombre;
    String educacion;
    String cedula;
    int estrato;
    double salario;

    public Persona(String nombre, String educacion, String cedula, int estrato, double salario) {
        this.nombre = nombre;
        this.educacion = educacion;
        this.cedula = cedula;
        this.estrato = estrato;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", educacion='" + educacion + '\'' +
                ", cedula='" + cedula + '\'' +
                ", estrato=" + estrato +
                ", salario=" + salario +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
