package com.luiza.cadastro_usuario.business;

import com.luiza.cadastro_usuario.infrastructure.entitys.Usuario;
import com.luiza.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){

        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("E-mail não encontrado") //funcao rapidinha, "preguicosa"
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
        Usuario usuarioAntigo = repository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() :
                        usuarioAntigo.getEmail()) //usuarioAntigo = usuarioEntity
                .nome(usuario.getNome() != null ? usuario.getNome() :
                        usuarioAntigo.getNome())
                .id(usuarioAntigo.getId())
                .build();
                repository.saveAndFlush(usuarioAtualizado);
    }
}


