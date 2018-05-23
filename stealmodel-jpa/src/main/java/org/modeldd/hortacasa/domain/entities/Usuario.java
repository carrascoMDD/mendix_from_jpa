/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.domain.entities;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;

/**
 * @author Antonio Carrasco Valero
 */
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 32)
    @Basic(optional = false)
    private String login;

    @Column(length = 32)
    @Basic(optional = false)
    private String nombre;

    @Column(length = 64)
    @Basic(optional = false)
    private String apellidos;

    @Column(length = 96)
    @Basic(optional = false)
    private String email;

    @Column(length = 64)
    @Basic
    private String salt;

    @Column(length = 256)
    @Basic
    private String password;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Calendar fechaCreacion;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaUltimoLogin;

    @OneToMany( /* targetEntity = Autorizacion.class, */mappedBy = "usuario", cascade = { CascadeType.DETACH, CascadeType.REMOVE})
    private List<Autorizacion> autorizaciones;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<Calendar> getFechaCreacion() {
        return Optional.ofNullable(this.fechaCreacion);
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Calendar getFechaUltimoLogin() {
        return this.fechaUltimoLogin;
    }

    public void setFechaUltimoLogin(Calendar fechaUltimoLogin) {
        this.fechaUltimoLogin = fechaUltimoLogin;
    }

    public List<Autorizacion> getAutorizaciones() {
        return this.autorizaciones;
    }

    public void setAutorizaciones(List<Autorizacion> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

}