package edu.eci.cvds.elysium.dto;

import java.time.LocalDate;

import edu.eci.cvds.elysium.model.DiaSemana;


public class ReservaDTO {
    
    private String idReserva;
    private LocalDate fechaReserva;
    private double hora;
    private char tipoCampo;
    // Se representa el día de la semana como String (por ejemplo, "LUNES")
    private DiaSemana diaSemana;
    private String proposito;

    // Se utiliza el objeto Salón para identificar el salón (1..1)
    private String idSalon;

    // true: reserva de bloque; false: reserva corta
    private boolean duracionBloque;

    private int prioridad;
    private int idUsuario;

    /*
     * Constructor de la clase ReservaDTO
     */
    public ReservaDTO() {
    }

    /**
     * Constructor de la clase ReservaDTO
     * @param idReserva
     * @param fechaReserva
     * @param diaSemana
     * @param proposito
     * @param idSalon
     * @param duracionBloque
     * @param prioridad
     */
    
    public ReservaDTO(String idReserva, LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito, String idSalon, boolean duracionBloque, int prioridad) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.hora = hora;
        this.diaSemana = diaSemana;
        this.proposito = proposito;
        this.idSalon = idSalon;
        this.duracionBloque = duracionBloque;
        setPrioridad(prioridad);
    }

    public ReservaDTO(String idReserva,char tipoCampo, LocalDate fechaReserva,double hora, DiaSemana diaSemana, String idSalon, boolean duracionBloque, int prioridad) {
        this.idReserva = idReserva;
        this.tipoCampo = tipoCampo;
        this.fechaReserva = fechaReserva;
        this.hora = hora;
        this.diaSemana = diaSemana;
        this.idSalon = idSalon;
        this.duracionBloque = duracionBloque;
        setPrioridad(prioridad);
    }

    //Getters
    public String getIdReserva() {return idReserva;}
    public LocalDate getFechaReserva() {return fechaReserva;}
    public double getHora() {return hora;}
    public char getTipoCampo() {return tipoCampo;}
    public DiaSemana getDiaSemana() {return diaSemana;}
    public String getProposito() {return proposito;}
    public String getIdSalon() {return idSalon;}
    public boolean isDuracionBloque() {return duracionBloque;}

    public int getPrioridad() {
        return prioridad;
    }

    // Setters
    public void setPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        }
        this.prioridad = prioridad;
    }

    /**
     * Gets the user ID associated with the reservation.
     *
     * @return the user ID.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Sets the user ID associated with the reservation.
     *
     * @param idUsuario the user ID.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
