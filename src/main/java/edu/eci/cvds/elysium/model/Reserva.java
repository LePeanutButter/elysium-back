package edu.eci.cvds.elysium.model;

import java.time.LocalTime;

public class Reserva {
    private LocalTime fechaInicio;
    private String proposito;
    private String mnemonico;
    private int usuarioId;

    public Reserva(LocalTime fechaInicio, String proposito, String mnemonico, int usuarioId) {
        this.fechaInicio = fechaInicio;
        this.proposito = proposito;
        this.mnemonico = mnemonico;
        this.usuarioId = usuarioId;
    }

    // Getters y setters
    public LocalTime getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getProposito() {
        return proposito;
    }
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }
    public String getMnemonico() {
        return mnemonico;
    }
    public void setMnemonico(String mnemonico) {
        this.mnemonico = mnemonico;
    }
    public int getUsuario() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
