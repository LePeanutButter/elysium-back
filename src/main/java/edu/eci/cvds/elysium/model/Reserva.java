package edu.eci.cvds.elysium.model;

import java.time.LocalTime;

import edu.eci.cvds.elysium.model.usuario.Usuario;

public class Reserva {
    private LocalTime fechaInicio;
    private String proposito;
    private String mnemonico;
    private Usuario usuario;

    public Reserva(LocalTime fechaInicio, String proposito, String mnemonico, Usuario usuario) {
        this.fechaInicio = fechaInicio;
        this.proposito = proposito;
        this.mnemonico = mnemonico;
        this.usuario = usuario;
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
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
