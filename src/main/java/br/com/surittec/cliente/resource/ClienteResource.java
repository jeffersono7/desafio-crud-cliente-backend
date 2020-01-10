package br.com.surittec.cliente.resource;

import br.com.surittec.cliente.entity.Cliente;
import br.com.surittec.cliente.service.ClienteService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ObjectNode> criar(ObjectNode objectNode) {
        ObjectNode response = clienteService.criar(objectNode);

        String id = response.get("id").asText();

        return ResponseEntity.created(URI.create(id)).body(response);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ObjectNode> atualizar(ObjectNode objectNode) {
        ObjectNode response = clienteService.atualizar(objectNode);

        String id = response.get("id").asText();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodos() {
        List<Cliente> clientes = clienteService.obterTodos();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<ObjectNode> obter(Long id) {
        ObjectNode response = clienteService.obter(id);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity deletar(Long id) {
        clienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
