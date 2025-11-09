package co.edu.elbosque.procureit.security;

import co.edu.elbosque.procureit.entity.Empleado;
import co.edu.elbosque.procureit.repository.EmpleadoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoUserDetailsService implements UserDetailsService {
    private final EmpleadoRepository repo;
    public EmpleadoUserDetailsService(EmpleadoRepository repo) { this.repo = repo; }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado e = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
        GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + e.getRolApp().name());
        // IMPORTANTE: para demo la contraseña se iguala al username (cámbiala por real en producción)
        return User.withUsername(e.getUsername()).password("{noop}" + e.getUsername()).authorities(List.of(auth)).build();
    }
}
