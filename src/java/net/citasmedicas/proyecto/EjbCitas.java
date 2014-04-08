package net.citasmedicas.proyecto;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@Stateless @LocalBean
public class EjbCitas {
    @Inject
    private EntityManager em;
    //metodo agregar
    public void agrega(Cita modelo) {
    em.persist(modelo);
    }
    //metodo modifica
    public void modifica(Cita modelo){
        em.merge(modelo);
    }
    //metodo elimina
    public void elimina(Long id){
        final Cita modelo = em.find(Cita.class, id);
        //si exiten citas las pueden eliminar 
        if(modelo != null)
        { em.remove(modelo); }
        
    }
   
    public List<Cita> getInstancias(){
        return em.createQuery("SELECT c FROM Cita c ORDER BY c.nombre",
                Cita.class).getResultList();
        
    }
           
}
