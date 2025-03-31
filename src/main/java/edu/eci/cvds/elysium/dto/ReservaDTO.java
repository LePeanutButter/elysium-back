package edu.eci.cvds.elysium.dto;

import java.time.LocalDate;

import edu.eci.cvds.elysium.model.DiaSemana;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;


public class ReservaDTO {
    private String idReserva;    
    private LocalDate fechaReserva;
    @Negative(message = "La hora no puede ser negativa")
    private Double hora;
    // Se representa el día de la semana como String (por ejemplo, "LUNES")
    private DiaSemana diaSemana;
    @NotNull(message = "El propósito no puede ser nulo")
    private String proposito;

    private String materia;

    @NotNull(message = "El id del salón no puede ser nulo")
    // Se utiliza el objeto Salón para identificar el salón (1..1)
    private String idSalon;

    // true: reserva de bloque; false: reserva corta
    private Boolean duracionBloque;

    private Integer prioridad;
    private Integer idUsuario;

    

    /**
     * Default constructor
     */
    public ReservaDTO() {
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
    public Double getHora() {return hora;}

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
     * Get the reservation subject
     * @return reservation subject
     */
    public String getMateria() {return materia;}

    /**
     * Get the reservation salon ID
     * @return reservation salon ID
     */
    public String getIdSalon() {return idSalon;}

    /**
     * Get the reservation duration
     * @return reservation duration
     */
    public Boolean isDuracionBloque() {return duracionBloque;}

    /**
     * Get the reservation priority
     * @return reservation priority
     */
    public Integer getPrioridad() {return prioridad;}


    /**
     * Get the reservation user ID
     * @return reservation user ID
     */
    public Integer getIdUsuario() {return idUsuario;}

}
