package edu.eci.cvds.elysium.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.repository.RecursoRepository;

@Service
public class RecursoServiceImpl implements RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    @Override
    public List<RecursoModel> consultarRecursos() {
        return recursoRepository.findAll();
    }

    @Override
    public List<RecursoModel> consultarNombre(String nombre) {
        return recursoRepository.findByNombre(nombre);
    }

    @Override
    public List<RecursoModel> consultarCantidad(int cantidad) {
        return recursoRepository.findByCantidad(cantidad);
    }

    @Override
    public List<RecursoModel> consultarEspecificaciones(List<String> especificaciones) {
        return recursoRepository.findByEspecificaciones(especificaciones);
    }

    @Override
    public RecursoModel consultarRecurso(ObjectId id) {
        return recursoRepository.findByObjectID(id);
    }

    @Override
    public void agregarRecurso(String nombre, int cantidad, List<String> especificaciones) {
        RecursoModel recurso = new RecursoModel(nombre, cantidad, especificaciones);
        recurso.crearRecurso(nombre, cantidad, especificaciones);
        boolean activo = true;
        recurso.setActivo(activo);
        recursoRepository.save(recurso);
    }

    @Override
    public void actualizarRecurso(ObjectId id,char tipoCampo,String nuevoNombre, int nuevaCantidad, List<String> nuevasEspecificaciones) {
        RecursoModel recurso = recursoRepository.findByObjectID(id);
        if(recurso != null ){
            recurso.actualizar(id, tipoCampo, nuevoNombre, nuevaCantidad, nuevasEspecificaciones);
            switch (tipoCampo){
                case 'n':
                    recurso.setNombre(nuevoNombre);
                    break;
                case 'c':
                    recurso.setCantidad(nuevaCantidad);
                    break;
                case 'e':
                    recurso.setEspecificaciones(nuevasEspecificaciones);
                    break;
                default:
                    break;
            }
            boolean activo = true;
            recurso.setActivo(activo);
            recursoRepository.save(recurso);
        }
    }

    @Override
    public void eliminarRecurso(ObjectId id) {
        RecursoModel recurso = recursoRepository.findByObjectID(id);
        if(recurso != null){
            recursoRepository.delete(recurso);
            boolean activo = false;
            recurso.setActivo(activo);
            recursoRepository.save(recurso);
        }
    }
    
}
