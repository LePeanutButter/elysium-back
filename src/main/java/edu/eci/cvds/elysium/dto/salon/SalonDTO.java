package edu.eci.cvds.elysium.dto.salon;

public class SalonDTO {
    // Campo obligatorio para identificar el salón.
    private String mnemonico;
    // Los siguientes campos son opcionales; se actualizan si no son null.
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private Integer capacidad;
    
    private Boolean activo;
    private Boolean disponible;

    /**
     * Default constructor
     */
    public SalonDTO() {}

    /**
     * Constructor with parameters
     * @param mnemonico the mnemonic of the salon
     * @param nombre the name of the salon
     * @param descripcion the description of the salon
     * @param ubicacion the location of the salon
     * @param capacidad the capacity of the salon
     */
    public SalonDTO(String nombre, String descripcion, String ubicacion, int capacidad){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }


    // GETTERS AND SETTERS

    /**
     * Get the mnemonic of the salon
     * @return the mnemonic of the salon
     */
    public String getMnemonic(){return mnemonico;}

    /**
     * Set the mnemonic of the salon
     * @param mnemonico the mnemonic of the salon
     */
    public void setMnemonic(String mnemonico){this.mnemonico = mnemonico;}

    /**
     * Get the name of the salon
     * @return the name of the salon
     */
    public String getName(){return nombre;}

    /**
     * Set the name of the salon
     * @param nombre the name of the salon
     */
    public void setName(String nombre){this.nombre = nombre;}

    /**
     * Get the location of the salon
     * @return the location of the salon
     */
    public String getLocation(){return ubicacion;}

    /**
     * Set the location of the salon
     * @param ubicacion the location of the salon
     */
    public void setLocation(String ubicacion){this.ubicacion = ubicacion;}

    /**
     * Get the capacity of the salon
     * @return the capacity of the salon
     */
    public Integer getCapacity(){return capacidad;}

    /**
     * Set the capacity of the salon
     * @param capacidad the capacity of the salon
     */
    public void setCapacity(int capacidad){this.capacidad = capacidad;}

    /**
     * Get the description of the salon
     * @return the description of the salon
     */
    public String getDescription(){return descripcion;}

    /**
     * Set the description of the salon
     * @param descripcion the description of the salon
     */
    public void setDescription(String descripcion){this.descripcion = descripcion;}

    /**
     * Get the active status of the salon
     * @return the active status of the salon
     */
    public Boolean getActivo() {return activo;}

    /**
     * Set the active status of the salon
     * @param activo the active status of the salon
     */
    public void setActivo(boolean activo){this.activo = activo;}

    /**
     * Get the available status of the salon
     * @return the available status of the salon
     */
    public Boolean getAvailable(){return disponible;}

    /**
     * Set the available status of the salon
     * @param disponible the available status of the salon
     */
    public void setAvailable(boolean disponible){this.disponible = disponible;}
    
}