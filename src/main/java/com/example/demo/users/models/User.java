package com.example.demo.users.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.security.config.token.Token;
import com.example.demo.tools.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity()
@Table(name = "users", schema = "dev")
@AllArgsConstructor
@NoArgsConstructor
// hibernate
@SQLDelete(sql = "UPDATE dev.users SET deleted = true WHERE deleted= false and  users_id = ?")
@Where(clause = "deleted = false")
public class User extends BaseModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="users_id")
    private Long userId;

     @NotEmpty(message = "El campo name es obligatorio")
    @Column(name ="name")
    private String username;
    
    @NotEmpty(message = "El campo contraseña es obligatorio")
    private String password;
 /*    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserRol userRoles;
        public UserRol getUserRoles() {
        return userRoles;
    }
*/

 /* 
    @JsonIgnore
    @ManyToMany(mappedBy = "UserRol")
    private Set<UserRol> userRol = new HashSet<>();
*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_rol",
        schema = "dev",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> userRol = new HashSet<>();


    public Set<Rol> getUserRol() {
        return userRol;
    }


  @OneToMany(mappedBy = "user")
  private List<Token> tokens;


    public void setUsername(String name) {
        this.username = name;
    }
      @Override
        public String getUsername() {
        return username;
    }

    
    public void setPassword(String password) { 
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(password);
        this.password =    encodedPassword;
    }



@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return getUserRol();
}
    @Override
    public String getPassword() {
         return password;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
    return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
         return true;
    }

    @Override
    public boolean isEnabled() {
      return !isDeleted();
    }
    public Long getId() {
        return userId;
    }




/*

        */

}


