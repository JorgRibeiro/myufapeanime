package br.edu.ufape.myufapeanime.myufapeanime.TestesDeIntegracao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;

@SpringBootTest
public class IntegracaoUsuarioTest {
    @Autowired
    private GerenciadorAnimes gerenciadorAnimes;

    @Autowired
    @Qualifier("interfaceRepositorioUsuarios")
    private InterfaceRepositorioUsuarios repositorioUsuarios;

    @Test
    public void testCriarEBuscarUsuarioPorId() throws UsuarioDuplicadoException, UsuarioInexistenteException {
        // Cria um novo usuário
        Usuario usuario = new Usuario();
        usuario.setNome("Novo Usuário");
        usuario.setEmail("novo.usuario@example.com");

        // Salva o usuário pela fachada
        Usuario usuarioSalvo = gerenciadorAnimes.saveUsuario(usuario);

        // Busca o usuário salvo pelo ID e verifica os dados
        Usuario usuarioEncontrado = gerenciadorAnimes.findByIdUsuario(usuarioSalvo.getId());
        assertEquals(usuario.getNome(), usuarioEncontrado.getNome());
        assertEquals(usuario.getEmail(), usuarioEncontrado.getEmail());
    }
}
