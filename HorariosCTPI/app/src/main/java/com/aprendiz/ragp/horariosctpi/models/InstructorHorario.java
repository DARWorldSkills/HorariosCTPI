package com.aprendiz.ragp.horariosctpi.models;

public class InstructorHorario {
    private String nombre;
    private String dia;
    private String hora;
    private String ficha;
    private String ambiente;
    private String abreviacion;

    public InstructorHorario() {
    }

    public InstructorHorario(String nombre, String dia, String hora, String ficha, String ambiente) {
        this.nombre = nombre;
        this.dia = dia;
        this.hora = hora;
        this.ficha = ficha;
        this.ambiente = ambiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }


    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }
}
