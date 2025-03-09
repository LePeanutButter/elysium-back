package edu.eci.cvds.elysium.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Represents a reservation model for the "reservas" collection.
 */
@Document(collection = "reservas")
public class ReservaModel {

    @Id
    private String idReserva;
    private LocalDate fechaReserva;
    private DiaSemanaModel diaSemana;
    private String proposito;
    private String idSalon;
    private EstadoReservaModel estado;
    private boolean duracionBloque;

    /**
     * Default constructor for a ReservaModel instance.
     */
    public ReservaModel() {
    }

    /**
     * Constructor to create a new ReservaModel instance.
     *
     * @param idReserva      the reservation ID
     * @param fechaReserva   the date of the reservation
     * @param diaSemana      the day of the week of the reservation
     * @param proposito      the purpose of the reservation
     * @param idSalon          the salon associated with the reservation
     * @param duracionBloque the duration block of the reservation
     */
    public ReservaModel(String idReserva,LocalDate fechaReserva, DiaSemanaModel diaSemana, String proposito, String idSalon,boolean duracionBloque) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.diaSemana = diaSemana;
        this.proposito = proposito;
        this.idSalon = idSalon;
        this.estado = EstadoReservaModel.ACTIVA;
        this.duracionBloque = duracionBloque;
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
     * Sets the reservation ID.
     *
     * @param idReserva the reservation ID
     */
    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
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
     * Gets the day of the week of the reservation.
     *
     * @return the day of the week of the reservation
     */
    public DiaSemanaModel getDiaSemana() {
        return diaSemana;
    }

    /**
     * Sets the day of the week of the reservation.
     *
     * @param diaSemana the day of the week of the reservation
     */
    public void setDiaSemana(DiaSemanaModel diaSemana) {
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
    public EstadoReservaModel getEstado() {
        return estado;
    }

    /**
     * Sets the state of the reservation.
     *
     * @param estado the state of the reservation
     */
    public void setEstado(EstadoReservaModel estado) {
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
     * Creates a new reservation.
     *
     * @param idReserva    the reservation ID
     * @param fechaReserva the date of the reservation
     * @param diaSemana    the day of the week of the reservation
     * @param proposito    the purpose of the reservation
     * @param idSalon        the salon associated with the reservation
     * @param estado       the state of the reservation
     * @param duracionBloque the duration block of the reservation
     * @return the new reservation
     */
    public ReservaModel crearReserva(String idReserva, LocalDate fechaReserva, DiaSemanaModel diaSemana, String proposito, String idSalon,boolean duracionBloque) {
        this.estado = EstadoReservaModel.ACTIVA;
        return new ReservaModel(idReserva, fechaReserva, diaSemana, proposito, idSalon, duracionBloque);
    }
    /**
     * Updates the reservation with the new data.
     * @param idInstitucional the reservation ID
     * @param tipoCampo the field type to update
     * @param value1 the new value
     * @param value2 the new value
     * @param value3 the new value
     * @param value4 the new value
     */
    public void actualizar(String idReserva, char tipoCampo, LocalDate value1,DiaSemanaModel value2,String value3,boolean value4) {
        if(this.idReserva.equals(idReserva)) {
            switch(tipoCampo) {
                case 'f': //fecha
                    this.fechaReserva = value1;
                    break;
                case 'd': //día
                    this.diaSemana = value2;
                    break;
                case 's': //salón
                    this.idSalon = value3;
                    break;
                case 'b': //bloque
                    this.duracionBloque = value4;
                    break;
                default:
                    break;
            }
        }
        this.estado = EstadoReservaModel.ACTIVA;
    }

    /**
     * Deletes the reservation.
     * @param idReserva the reservation ID  
     */
    public void deleteReserva(String idReserva) {
        if(this.idReserva.equals(idReserva)) {
            this.estado = EstadoReservaModel.ELIMINADA;
        }
    }

    /**
     * Cancels the reservation.
     * @param idReserva the reservation ID
     */
    public void cancelReserva(String idReserva) {
        if(this.idReserva.equals(idReserva)) {
            this.estado = EstadoReservaModel.CANCELADA;
        }
    }

    /**
     * Accepts the reservation.
     * @param idReserva the reservation ID
     */
    public void rechazarReserva(String idReserva) {
        if(this.idReserva.equals(idReserva)) {
            this.estado = EstadoReservaModel.RECHAZADA;
        }
    }
}

