package challlenge.forohub.infra.seguridad;

import challlenge.forohub.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticarUsuariosService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    //Metodo para cargar el usuario mediante el nombre, el nombre es extraido de la base de datos gracias al repositorio
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findBylogin(username);
    }
}


