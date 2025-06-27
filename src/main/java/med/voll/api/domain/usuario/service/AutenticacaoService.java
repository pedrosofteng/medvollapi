package med.voll.api.domain.usuario.service;

import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    /*
    UserDetailService é uma interface do spring security, ele mesmo sozinho vai chamar ela
    quando tiver essa requisição de aunteticação de usuário

    spring sozinho acha e chama essa classe, não precisamos injetar, só configurar
     */

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
