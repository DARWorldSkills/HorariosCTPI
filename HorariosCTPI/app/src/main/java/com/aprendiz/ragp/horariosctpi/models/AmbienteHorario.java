package com.aprendiz.ragp.horariosctpi.models;

import java.util.List;

public class AmbienteHorario {
    private String nombre;
    private List<Horario> horario;

    public AmbienteHorario() {
    }

    public AmbienteHorario(String nombre, List<Horario> horario) {
        this.nombre = nombre;
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }
}
