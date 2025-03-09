package edu.eci.cvds.elysium.repository;

import edu.eci.cvds.elysium.model.RecursoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.bson.types.ObjectId;

@Repository
public interface RecursoRepository extends MongoRepository<RecursoModel, ObjectId> {

    RecursoModel findByObjectID(ObjectId id);
    List<RecursoModel> findAll();
    List<RecursoModel> findByNombre(String nombre);
    List<RecursoModel> findByCantidad(int cantidad);
    List<RecursoModel> findByEspecificaciones(List<String> especificaciones);
}
