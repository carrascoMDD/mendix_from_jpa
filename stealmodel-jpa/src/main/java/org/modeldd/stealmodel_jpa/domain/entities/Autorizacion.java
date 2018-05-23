/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.domain.entities;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

/**
 * @author Antonio Carrasco Valero
 */
@Entity
public class Autorizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional=false, fetch= EAGER)
    @JoinColumn(nullable=false)
    private Usuario usuario;

    @ManyToOne(optional=false, fetch= EAGER)
    @JoinColumn(nullable=false)
    private Rol rol;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}