package com.aprendiz.ragp.horariosctpi.models;

import java.util.List;

public class AmbienteHorarioFicha {
    private String nombre;
    private Horario horario;

    public AmbienteHorarioFicha() {
    }

    public AmbienteHorarioFicha(String nombre, Horario horario) {
        this.nombre = nombre;
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
