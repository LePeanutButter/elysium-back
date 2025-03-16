package edu.eci.cvds.elysium.dto.salon;

public class SalonDTO {
    // Campo obligatorio para identificar el salón.
    private String mnemonico;
    // Los siguientes campos son opcionales; se actualizan si no son null.
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private String description;
    private Boolean activo;
    private Boolean disponible;

    public SalonDTO() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }

    public Boolean getDisponible (){
        return disponible;
    }

    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
}