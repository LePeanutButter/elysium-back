package edu.eci.cvds.elysium.model.usuario;

import java.util.ArrayList;
import java.util.List;

import edu.eci.cvds.elysium.model.Salon;

public class Administrador extends Usuario {

    private List<Salon> salones;

    public Administrador(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo) {
        super(idInstitucional, nombre, apellido, correoInstitucional, activo, true);
        this.salones = new ArrayList<>();
    }

    @Override
    public List<Salon> getSalones() {
        return salones;
    }

    // Método para actualizar información (por ejemplo, 'n' para nombre, 'a' para apellido, 'c' para correo)
    public void actualizar(int idInstitucional, char tipoCampo, String value) {
        if(this.idInstitucional == idInstitucional) {
            switch(tipoCampo) {
                case 'n':
                    this.nombre = value;
                    break;
                case 'a':
                    this.apellido = value;
                    break;
                case 'c':
                    this.correoInstitucional = value;
                    break;
                default:
                    break;
            }
        }
    }

    // // Método para añadir un salón
    // public void añadirSalon(String nombre, String ubicacion, int capacidad) {
    //     Salon salon = new Salon(nombre, ubicacion, capacidad);
    //     this.salones.add(salon);
    // }
}
