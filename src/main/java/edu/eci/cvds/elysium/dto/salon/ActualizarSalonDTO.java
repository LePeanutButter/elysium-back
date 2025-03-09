package edu.eci.cvds.elysium.dto.salon;

public class ActualizarSalonDTO {
    // Campo obligatorio para identificar el salón.
    private String mnemonico;
    // Los siguientes campos son opcionales; se actualizan si no son null.
    private String nombre;
    private String ubicacion;
    private Integer capacidad;

    public ActualizarSalonDTO() {}

    // Getters y setters
    public String getMnemonico() {
        return mnemonico;
    }

    public void setMnemonico(String mnemonico) {
        this.mnemonico = mnemonico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}