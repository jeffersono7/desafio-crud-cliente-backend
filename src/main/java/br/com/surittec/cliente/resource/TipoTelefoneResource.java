package br.com.surittec.cliente.resource;

import br.com.surittec.cliente.entity.TipoTelefone;
import br.com.surittec.cliente.service.TipoTelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "tipos-telefone")
public class TipoTelefoneResource {

    private final TipoTelefoneService tipoTelefoneService;

    @GetMapping
    public ResponseEntity<List<TipoTelefone>> obterTodos() {
        List<TipoTelefone> telefones = tipoTelefoneService.obterTodos();

        return ResponseEntity.ok(telefones);
    }
}
