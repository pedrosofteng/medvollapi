package med.voll.api.domain.usuario.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    /* SpringSecurity precisa conhecer a classe, campo login que é login, senha é senha
    então ele vai implementar a interface UserDetails
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        /*
        tenho perfil moderador, administrador, leitor -> você precisa criar uma classe que representa esses perfis

        SimpleGrantedAuthority é uma classe do Spring para destinar perfis
        ROLE + PERFIL
        como só queremos usuários, usamos ROLE_USER
         */
    }

    @Override
    public String getPassword() {
        return senha;
        // MUDAR SEMPRE PARA SENHA
    }

    @Override
    public String getUsername() {
        return login;
        // MUDAR SEMPRE PARA LOGIN
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    // A CONTA DO USUÁRIO TEM UMA DATA DE EXPIRAÇÃO? RETURN --

    @Override
    public boolean isAccountNonLocked() { return true; }

    // O USUÁRIO PODE SER BLOQUEADO? RETURN --

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    // O USUÁRIO PODE TER AS CREDENCIAIS BLOQUEADAS? RETURN --

    // por padrão deixe true se não for USAR!

    @Override
    public boolean isEnabled() { return true; }
}
