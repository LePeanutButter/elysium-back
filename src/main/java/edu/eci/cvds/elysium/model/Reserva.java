package edu.eci.cvds.elysium.model;

import java.time.LocalTime;
import edu.eci.cvds.elysium.model.usuario.Usuario;

public class Reserva implements Comparable<Reserva> {
    private LocalTime fechaInicio;
    private String proposito;
    private String mnemonico;
    private Usuario usuario;
    private int prioridad;

    public Reserva(LocalTime fechaInicio, String proposito, String mnemonico, Usuario usuario, int prioridad) {
        this.fechaInicio = fechaInicio;
        this.proposito = proposito;
        this.mnemonico = mnemonico;
        this.usuario = usuario;
        setPrioridad(prioridad);
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
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public int getPrioridad() { return prioridad; }

    public void setPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        }
        this.prioridad = prioridad;
    }

    // Método para comparación en estructuras de datos como PriorityQueue
    @Override
    public int compareTo(Reserva otraReserva) {
        return Integer.compare(otraReserva.prioridad, this.prioridad);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "fechaInicio=" + fechaInicio +
                ", proposito='" + proposito + '\'' +
                ", mnemonico='" + mnemonico + '\'' +
                ", usuario=" + usuario +
                ", prioridad=" + prioridad +
                '}';
    }
}
