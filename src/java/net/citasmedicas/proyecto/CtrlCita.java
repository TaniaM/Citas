package net.citasmedicas.proyecto;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class CtrlCita implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private static final String DETALLE = "detalle";
    @Inject
    private Conversation conversacion;
    @Inject 
    private EjbCitas ejb;
    private Cita modelo;
    private boolean nuevo;

    
    //getter de modelo
    public Cita getModelo() {
        return modelo;
    }
    
    //getter de nuevo
    public boolean isNuevo() {
        return nuevo;
    }
    
    @PostConstruct
    public void iniciaConversacion(){
        if(conversacion.isTransient())
            {
                conversacion.begin();
            }
    }
    
    public void terminaConversacion(){
        if(!conversacion.isTransient())
        {
            conversacion.end();
        }
    }
    
    public String editaNuevo(){
        modelo = new Cita();
        nuevo = true;
        return DETALLE;
    }
    
    public String detalle(Cita modelo){
        if(modelo == null){
            return null;
        }
        else{
            this.modelo = modelo;
            nuevo = false;
            return DETALLE;
        }
    }
    
    public String guarda(){
         try {
            if (nuevo) {
                ejb.agrega(modelo);
            } else {
                ejb.modifica(modelo);
            }
            terminaConversacion();
            return "index?faces-redirect=true";
        } catch (Exception ex) {
            Mensajes.error(ex);
            return null;
        }
    }
    
    public String regresa(){
        terminaConversacion();
        return "index?faces-redirect=true";
    }
}
