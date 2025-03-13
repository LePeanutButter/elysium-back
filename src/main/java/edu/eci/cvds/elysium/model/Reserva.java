package edu.eci.cvds.elysium.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a reservation model for the "reservas" collection.
 */
@Document(collection = "reservas")
public class Reserva {

    @Id
    private String idReserva;
    private LocalDate fechaReserva;
    private double hora;
    private DiaSemana diaSemana;
    private String proposito;
    private String idSalon;
    private EstadoReserva estado;
    private boolean duracionBloque;
    private int prioridad;
    private int idUsuario;

    /**
     * Default constructor for a ReservaModel instance.
     */
    public Reserva() {
    }

    /**
     * Constructor to create a new ReservaModel instance.
     *
     * @param fechaReserva   the date of the reservation
     * @param diaSemana      the day of the week of the reservation
     * @param proposito      the purpose of the reservation
     * @param idSalon        the salon associated with the reservation
     * @param duracionBloque the duration block of the reservation
     * @param prioridad      the priority of the reservation (1 to 5)
     * @param idUsuario      the user ID associated with the reservation
     */
    public Reserva(LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito, String idSalon,boolean duracionBloque, int prioridad, int idUsuario) {
        this.fechaReserva = fechaReserva;
        this.hora = hora;
        this.diaSemana = diaSemana;
        this.proposito = proposito;
        this.idSalon = idSalon;
        this.estado = EstadoReserva.ACTIVA;
        this.duracionBloque = duracionBloque;
        setPrioridad(prioridad); // Validación dentro del setter
        // each reserva knows its user
        this.idUsuario = idUsuario;
    }

    /**
     * Gets the reservation ID.
     *
     * @return the reservation ID
     */
    public String getIdReserva() {
        return idReserva;
    }

    /**
     * Gets the date of the reservation.
     *
     * @return the date of the reservation
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Sets the date of the reservation.
     *
     * @param fechaReserva the date of the reservation
     */
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * Gets the hour of the reservation.
     *
     * @return the hour of the reservation
     */
    public double getHora() {
        return hora;
    }

    /**
     * Sets the hour of the reservation.
     *
     * @param hora the hour of the reservation
     */
    public void setHora(double hora) {
        this.hora = hora;
    }

    /**
     * Gets the day of the week of the reservation.
     *
     * @return the day of the week of the reservation
     */
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    /**
     * Sets the day of the week of the reservation.
     *
     * @param diaSemana the day of the week of the reservation
     */
    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * Gets the purpose of the reservation.
     *
     * @return the purpose of the reservation
     */
    public String getProposito() {
        return proposito;
    }

    /**
     * Sets the purpose of the reservation.
     *
     * @param proposito the purpose of the reservation
     */
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    /**
     * Gets the salon ID associated with the reservation.
     *
     * @return the salon ID
     */
    public String getIdSalon() {
        return idSalon;
    }

    /**
     * Sets the salon ID associated with the reservation.
     *
     * @param idSalon the salon ID
     */
    public void setIdSalon(String idSalon) {
        this.idSalon = idSalon;
    }

    /**
     * Gets the state of the reservation.
     *
     * @return the state of the reservation
     */
    public EstadoReserva getEstado() {
        return estado;
    }

    /**
     * Sets the state of the reservation.
     *
     * @param estado the state of the reservation
     */
    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    /**
     * Checks if the reservation duration is a block.
     *
     * @return true if the reservation duration is a block, false otherwise
     */
    public boolean isDuracionBloque() {
        return duracionBloque;
    }

    /**
     * Sets the reservation duration block.
     *
     * @param duracionBloque the reservation duration block
     */
    public void setDuracionBloque(boolean duracionBloque) {
        this.duracionBloque = duracionBloque;
    }

    /**
     * Gets the priority of the reservation.
     *
     * @return the priority of the reservation
     */
    public int getPrioridad() {
        return prioridad;
    }


    /**
     * Sets the priority of the reservation.
     *
     * @param prioridad the priority of the reservation (1 to 5)
     */
    public void setPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        }
        this.prioridad = prioridad;
    }

    /**
     * Gets the user ID associated with the reservation.
     *
     * @return the user ID
     */
    public int getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the user ID associated with the reservation.
     * @param idUsuario the user ID
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
