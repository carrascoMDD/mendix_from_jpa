/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.domain.entities;

import javax.persistence.*;
import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    private String nombre;

    @Basic
    private String rolinterno;

    @OneToMany( /* targetEntity = Autorizacion.class, */mappedBy = "rol", cascade = { CascadeType.DETACH, CascadeType.REMOVE})
    private List<Autorizacion> autorizaciones;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRolinterno() {
        return this.rolinterno;
    }

    public void setRolinterno(String rolinterno) {
        this.rolinterno = rolinterno;
    }

    public List<Autorizacion> getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(List<Autorizacion> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }
}