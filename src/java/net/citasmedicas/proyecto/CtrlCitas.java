package net.citasmedicas.proyecto;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
public class CtrlCitas {
    @Inject
    private EjbCitas ejb;
    private List<Cita> instancias;
    @PostConstruct
  void cargaSesion() {
    // Para corregir un bug de mojarra con listados largos.
    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
  }
    
    public List<Cita> getInstancias() {
        try {
      if (instancias == null) {
        instancias = ejb.getInstancias();
      }
      return instancias;
    } catch (Exception ex) {
      Mensajes.error(ex);
      return null;
    }
   }
  
    public String elimina(final Cita modelo){
    try {
      if (modelo != null) {
        ejb.elimina(modelo.getId());
        return "index?faces-redirect=true";
      }
    } catch (Exception ex) {
      Mensajes.error(ex);
    }
    return null;
  }
}
