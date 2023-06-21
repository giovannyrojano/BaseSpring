package com.example.demo.users.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.security.auth.Subject;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;

import com.example.demo.tools.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol", schema = "dev")
@SQLDelete(sql = "UPDATE dev.rol SET deleted = true WHERE deleted = false and rol_id = ?")
@Where(clause = "deleted = false")
public class Rol extends BaseModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "name")
    private String name;

    @Column(name = "descripcion")
    private String descripcion;
    @JsonIgnore
    @ManyToMany(mappedBy = "userRol", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
    @JsonIgnore
     @Override
    public String getAuthority() {
        return name;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // Resto de atributos, constructores y métodos
}