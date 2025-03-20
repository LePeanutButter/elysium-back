package edu.eci.cvds.elysium.dto;

import java.time.LocalDate;

import edu.eci.cvds.elysium.model.DiaSemana;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;


public class ReservaDTO {
    private String idReserva;    
    private LocalDate fechaReserva;
    @Negative(message = "La hora no puede ser negativa")
    private double hora;
    // Se representa el día de la semana como String (por ejemplo, "LUNES")
    private DiaSemana diaSemana;
    @NotNull(message = "El propósito no puede ser nulo")
    private String proposito;

    @NotNull(message = "El id del salón no puede ser nulo")
    // Se utiliza el objeto Salón para identificar el salón (1..1)
    private String idSalon;

    // true: reserva de bloque; false: reserva corta
    private boolean duracionBloque;

    private int prioridad;
    @NotNull(message = "El id del usuario no puede ser nulo")
    private int idUsuario;

    

    /**
     * Default constructor
     */
    public ReservaDTO() {
    }

    

    /**
     * Constructor
     * @param idReserva represents the reservation ID
     * @param fechaReserva represents the reservation date 
     * @param hora represents the reservation time 
     * @param diaSemana represents the reservation day of the week
     * @param proposito represents the reservation purpose 
     * @param idSalon represents the reservation salon ID
     * @param duracionBloque represents the reservation duration
     * @param prioridad represents the reservation priority
     * @param idUsuario represents the reservation user ID
     */
    // Constructor for creating a reservation
    public ReservaDTO(LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito, String idSalon, boolean duracionBloque, int prioridad, int idUsuario) {
        this.fechaReserva = fechaReserva;
        this.hora = hora;
        this.diaSemana = diaSemana;
        this.proposito = proposito;
        this.idSalon = idSalon;
        this.duracionBloque = duracionBloque;
        setPrioridad(prioridad);
        this.idUsuario = idUsuario;
    }

    /**
     * Constructor
     * @param idReserva represents the reservation ID
     * @param fechaReserva represents the reservation date
     * @param hora represents the reservation time
     * @param diaSemana represents the reservation day of the week
     * @param proposito represents the reservation purpose
     * @param idSalon represents the reservation salon ID
     * @param duracionBloque represents the reservation duration
     * @param prioridad represents the reservation priority
     * @param idUsuario represents the reservation user ID
     */
    // Constructor for updating a reservation
    public ReservaDTO(String idReserva,LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito, String idSalon, boolean duracionBloque, int prioridad, int idUsuario) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.hora = hora;
        this.diaSemana = diaSemana;
        this.proposito = proposito;
        this.idSalon = idSalon;
        this.duracionBloque = duracionBloque;
        setPrioridad(prioridad);
        this.idUsuario = idUsuario;
    }

    //GETTERS

    /**
     * Get the reservation ID
     * @return reservation ID
     */
    public String getIdReserva() {return idReserva;}

    /**
     * Get the reservation date
     * @return reservation date
     */
    public LocalDate getFechaReserva() {return fechaReserva;}

    /**
     * Get the reservation time
     * @return reservation time
     */
    public double getHora() {return hora;}

    /**
     * Get the reservation day of the week
     * @return reservation day of the week
     */
    public DiaSemana getDiaSemana() {return diaSemana;}

    /**
     * Get the reservation purpose
     * @return reservation purpose
     */
    public String getProposito() {return proposito;}

    /**
     * Get the reservation salon ID
     * @return reservation salon ID
     */
    public String getIdSalon() {return idSalon;}

    /**
     * Get the reservation duration
     * @return reservation duration
     */
    public boolean isDuracionBloque() {return duracionBloque;}

    /**
     * Get the reservation priority
     * @return reservation priority
     */
    public int getPrioridad() {return prioridad;}

    /**
     * Set the reservation priority
     * @param prioridad reservation priority
     */
    public void setPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5");
        }
        this.prioridad = prioridad;
    }

    /**
     * Get the reservation user ID
     * @return reservation user ID
     */
    public int getIdUsuario() {return idUsuario;}

}
