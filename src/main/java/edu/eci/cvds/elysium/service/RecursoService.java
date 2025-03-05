package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.model.RecursoModel;

import org.bson.types.ObjectId;
import java.util.List;


public interface RecursoService {
    List<RecursoModel> consultarRecursos();
    List<RecursoModel> consultarNombre(String nombre);
    List<RecursoModel> consultarCantidad(int cantidad);
    List<RecursoModel> consultarEspecificaciones(List<String> especificaciones);
    RecursoModel consultarRecurso(ObjectId id);
    void agregarRecurso(String nombre, int cantidad, List<String> especificaciones);
    void actualizarRecurso(ObjectId id, char tipoCampo,String nombre, int cantidad, List<String> especificaciones);
    void eliminarRecurso(ObjectId id);
} 


