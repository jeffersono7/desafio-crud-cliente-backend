package br.com.surittec.cliente.resource;

import br.com.surittec.cliente.DTO.ClienteDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cliente> criar(@RequestBody ClienteDTO dto) {
        Cliente response = clienteService.criar(dto);

        String id = response.getId().toString();

        return ResponseEntity.created(URI.create(id)).body(response);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cliente> atualizar(ObjectNode objectNode) {
        Cliente response = clienteService.atualizar(objectNode);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodos() {
        List<Cliente> clientes = clienteService.obterTodos();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Cliente>> obter(Long id) {
        Optional<Cliente> response = clienteService.obter(id);

        if (response.isPresent()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity deletar(Long id) {
        clienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
