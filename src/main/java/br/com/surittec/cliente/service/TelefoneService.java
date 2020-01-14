package br.com.surittec.cliente.service;

import br.com.surittec.cliente.entity.Telefone;
import br.com.surittec.cliente.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;

    public List<Telefone> salvar(List<Telefone> telefones) {
        telefones.forEach(telefone -> telefone.setId(null));

        return telefoneRepository.saveAll(telefones);
    }

    public List<Telefone> atualizar(List<Telefone> telefones) {
        return telefoneRepository.saveAll(telefones);
    }
}
