/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GRUPO2
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByDv", query = "SELECT u FROM Usuario u WHERE u.dv = :dv"),
    @NamedQuery(name = "Usuario.findByApaterno", query = "SELECT u FROM Usuario u WHERE u.apaterno = :apaterno"),
    @NamedQuery(name = "Usuario.findByAmaterno", query = "SELECT u FROM Usuario u WHERE u.amaterno = :amaterno"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findBySexoId", query = "SELECT u FROM Usuario u WHERE u.sexoId = :sexoId"),
    @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Usuario.findByFonoa", query = "SELECT u FROM Usuario u WHERE u.fonoa = :fonoa"),
    @NamedQuery(name = "Usuario.findByFonob", query = "SELECT u FROM Usuario u WHERE u.fonob = :fonob"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.activo = :activo"),
    @NamedQuery(name = "Usuario.findByCreado", query = "SELECT u FROM Usuario u WHERE u.creado = :creado"),
    @NamedQuery(name = "Usuario.findByModificado", query = "SELECT u FROM Usuario u WHERE u.modificado = :modificado"),
    @NamedQuery(name = "Usuario.findByDesactivado", query = "SELECT u FROM Usuario u WHERE u.desactivado = :desactivado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "DV")
    private Character dv;
    @Column(name = "APATERNO")
    private String apaterno;
    @Column(name = "AMATERNO")
    private String amaterno;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "SEXO_ID")
    private BigInteger sexoId;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "FONOA")
    private BigInteger fonoa;
    @Column(name = "FONOB")
    private BigInteger fonob;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "ACTIVO")
    private Character activo;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "MODIFICADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    @Column(name = "DESACTIVADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date desactivado;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rol rolId;
    @JoinColumn(name = "RUT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sexo rut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jefe")
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "JEFE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario jefe;

    public Usuario() {
    }

    public Usuario(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Character getDv() {
        return dv;
    }

    public void setDv(Character dv) {
        this.dv = dv;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public BigInteger getSexoId() {
        return sexoId;
    }

    public void setSexoId(BigInteger sexoId) {
        this.sexoId = sexoId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigInteger getFonoa() {
        return fonoa;
    }

    public void setFonoa(BigInteger fonoa) {
        this.fonoa = fonoa;
    }

    public BigInteger getFonob() {
        return fonob;
    }

    public void setFonob(BigInteger fonob) {
        this.fonob = fonob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Character getActivo() {
        return activo;
    }

    public void setActivo(Character activo) {
        this.activo = activo;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public Date getDesactivado() {
        return desactivado;
    }

    public void setDesactivado(Date desactivado) {
        this.desactivado = desactivado;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public Sexo getRut() {
        return rut;
    }

    public void setRut(Sexo rut) {
        this.rut = rut;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Usuario getJefe() {
        return jefe;
    }

    public void setJefe(Usuario jefe) {
        this.jefe = jefe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Usuario[ id=" + id + " ]";
    }
    
}
