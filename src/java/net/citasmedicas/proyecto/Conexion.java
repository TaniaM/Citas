package net.citasmedicas.proyecto;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class Conexion {
    @Produces @PersistenceContext
     private EntityManager em;

}
