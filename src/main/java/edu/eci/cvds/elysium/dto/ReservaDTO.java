package edu.eci.cvds.elysium.dto;

import java.time.LocalDate;
import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;

public class ReservaDTO {
    
    private String idReserva;
    private LocalDate fechaReserva;
    private char tipoCampo;
    // Se representa el día de la semana como String (por ejemplo, "LUNES")
    private DiaSemanaModel diaSemana;
    private String proposito;

    // Se utiliza el objeto Salón para identificar el salón (1..1)
    private String idSalon;

    
    // true: reserva de bloque; false: reserva corta
    private boolean duracionBloque;

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
     */
    public ReservaDTO(String idReserva, LocalDate fechaReserva, DiaSemanaModel diaSemana, String proposito, String idSalon, boolean duracionBloque) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.diaSemana = diaSemana;
        this.proposito = proposito;
        this.idSalon = idSalon;
        this.duracionBloque = duracionBloque;
    }

    public ReservaDTO(String idReserva,char tipoCampo, LocalDate fechaReserva, DiaSemanaModel diaSemana, String idSalon, boolean duracionBloque) {
        this.idReserva = idReserva;
        this.tipoCampo = tipoCampo;
        this.fechaReserva = fechaReserva;
        this.diaSemana = diaSemana;
        this.idSalon = idSalon;
        this.duracionBloque = duracionBloque;
    }

    //Getters
    public String getIdReserva() {return idReserva;}
    public LocalDate getFechaReserva() {return fechaReserva;}
    public char getTipoCampo() {return tipoCampo;}
    public DiaSemanaModel getDiaSemana() {return diaSemana;}
    public String getProposito() {return proposito;}
    public String getIdSalon() {return idSalon;}
    public boolean isDuracionBloque() {return duracionBloque;}

}
