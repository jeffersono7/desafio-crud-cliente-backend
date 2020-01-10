package br.com.surittec.cliente.service;

import br.com.surittec.cliente.entity.TipoTelefone;
import br.com.surittec.cliente.repository.TipoTelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TipoTelefoneService {

    private final TipoTelefoneRepository tipoTelefoneRepository;

    public List<TipoTelefone> obterTodos() {
        return this.tipoTelefoneRepository.findAll();
    }
}
