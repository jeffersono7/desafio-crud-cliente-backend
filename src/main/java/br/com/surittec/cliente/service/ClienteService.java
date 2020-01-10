package br.com.surittec.cliente.service;

import br.com.surittec.cliente.entity.Cliente;
import br.com.surittec.cliente.repository.ClienteRepository;
import br.com.surittec.cliente.repository.EmailRepository;
import br.com.surittec.cliente.repository.TelefoneRepository;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;
    private final EmailRepository emailRepository;

    public ObjectNode criar(ObjectNode objectNode) {
        return null;
    }

    public ObjectNode atualizar(ObjectNode objectNode) {
        return null;
    }

    public List<Cliente> obterTodos() {
        return clienteRepository.findAll();
    }

    public ObjectNode obter(Long id) {
        return null;
    }

    public void deletar(Long id) {
    }
}
