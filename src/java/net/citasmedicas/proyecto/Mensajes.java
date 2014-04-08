package net.citasmedicas.proyecto;

import java.util.Set;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.transaction.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class Mensajes {
  public static void información(final String texto) {
    mensaje(FacesMessage.SEVERITY_INFO, texto, null);
  }
  public static void error(final String texto) {
    mensaje(FacesMessage.SEVERITY_ERROR, texto, null);
  }
  public static void error(final Throwable ex) {
    try {
      throw ex;
    } catch (final ConstraintViolationException ex2) {
      procesa(ex2.getConstraintViolations());
    } catch (final RollbackException | EJBException ex2) {
      final Throwable causa = ex2.getCause();
      if (causa == null) {
        error("Error al realizar operación.");
      } else if (causa == ex2) {
        error(ex.getLocalizedMessage());
      } else {
        error(causa);
      }
    } catch (final Throwable ex2) {
      error(ex2.getLocalizedMessage());
    }
  }
  private static void mensaje(FacesMessage.Severity mensaje, String resumen,
      String detalle) {
    FacesContext.getCurrentInstance().
        addMessage(null, new FacesMessage(mensaje, resumen, detalle));
  }
  private static void procesa(final Set<ConstraintViolation<?>> violaciones) {
    for (final ConstraintViolation<?> violacion : violaciones) {
      final String propiedad = violacion.getPropertyPath().toString();
      final String mensaje = violacion.getMessage();
      if (propiedad == null || propiedad.isEmpty()) {
        error(mensaje);
      } else {
        error(propiedad + ": " + mensaje);
      }
    }
  }
}