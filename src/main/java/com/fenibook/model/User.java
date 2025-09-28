package com.fenibook.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter 
@Setter

@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Role role;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int score;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist 
    protected void onCreate() { 
    	createdAt = LocalDateTime.now(); }
    
    public enum Role { USER, ADMIN }
    
    
    
    // --- Implementazione Metodi UserDetails ---

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    // getPassword() e getUsername() sono già generati da Lombok (@Getter)

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
    
    
    public void setUsername(String username2) {
		// TODO Auto-generated method stub
		
	}

	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		
	}

	public void setPassword(String encode) {
		// TODO Auto-generated method stub
		
	}

	public void setRole(Role user) {
		// TODO Auto-generated method stub
		
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
    
    
    
    

	
    
    

}