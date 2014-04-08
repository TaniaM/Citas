package net.citasmedicas.proyecto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Cita implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //esto quiere decir que el atributo nombre no debe 
    //ser nulo y tiene una longitud de 1 a 255 caracteres
    @NotNull @Size(min = 1, max = 255)
    private String nombre;
    //el atributo fechaNac no debe ser nulo y es una fecha
    //en el pasado
    @NotNull @Past
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNac;
    private String direccion;
    @NotNull 
    private String consultorio;
    //la cita no debera ser nula y la fecha es futura
    //ademas de tener un formato DD/MM/AAAA HH:MM
    @NotNull @Future
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date cita;
    
        public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//establece los setter y getter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public Date getCita() {
        return cita;
    }

    public void setCita(Date cita) {
        this.cita = cita;
    }
    


/*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

*/
    @Override
    public String toString() {
        return "Cita[" + getId() + "]";
    }
    
}
