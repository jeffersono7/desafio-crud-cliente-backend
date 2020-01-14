package br.com.surittec.cliente.service;

import br.com.surittec.cliente.entity.Endereco;
import br.com.surittec.cliente.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public Endereco save(Endereco endereco) {
        endereco.setId(null);

        return enderecoRepository.save(endereco);
    }

    public Endereco update(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
}
