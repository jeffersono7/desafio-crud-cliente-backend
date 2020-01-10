package br.com.surittec.cliente.service;

import br.com.surittec.cliente.entity.Email;
import br.com.surittec.cliente.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public List<Email> salvar(@Valid List<Email> emailList) {

        return emailRepository.saveAll(emailList);
    }
}
