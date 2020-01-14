package br.com.surittec.cliente.service;

import br.com.surittec.cliente.DTO.ClienteDTO;
import br.com.surittec.cliente.entity.Cliente;
import br.com.surittec.cliente.entity.Email;
import br.com.surittec.cliente.entity.Endereco;
import br.com.surittec.cliente.entity.Telefone;
import br.com.surittec.cliente.entity.TipoTelefone;
import br.com.surittec.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;
    private final EmailService emailService;

    public Cliente criar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);

        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(dto, endereco);

        Endereco enderecoSalvo = enderecoService.save(endereco);
        cliente.setEndereco(enderecoSalvo);

        cliente.setId(null);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        List<Email> emails = getEmailByClienteDTO(dto);
        emails.forEach(email -> email.setCliente(clienteSalvo));
        emailService.salvar(emails);

        List<Telefone> telefones = getTelefoneByDTO(dto);
        telefones.forEach(telefone -> telefone.setCliente(cliente));
        telefoneService.salvar(telefones);

        return clienteSalvo;
    }

    public Cliente atualizar(ClienteDTO dto) {

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);

        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(dto, endereco);

        Endereco enderecoSalvo = enderecoService.update(endereco);
        cliente.setEndereco(enderecoSalvo);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        List<Email> emails = getEmailByClienteDTO(dto);
        emails.forEach(email -> email.setCliente(clienteSalvo));
        emailService.salvar(emails);

        List<Telefone> telefones = getTelefoneByDTO(dto);
        telefones.forEach(telefone -> telefone.setCliente(cliente));
        telefoneService.atualizar(telefones);

        return clienteSalvo;
    }

    public List<Cliente> obterTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obter(Long id) {
        return clienteRepository.findById(id);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private List<Telefone> getTelefoneByDTO(ClienteDTO dto) {
        return dto.getTelefone()
                .stream()
                .map(telefoneDTO -> {
                    Long idTipoTelefone = telefoneDTO.getTipoTelefone().getId();

                    Telefone telefone = new Telefone();

                    TipoTelefone tipoTelefone = new TipoTelefone();
                    tipoTelefone.setId(idTipoTelefone);

                    telefone.setNumero(telefoneDTO.getNumero());
                    telefone.setTipoTelefone(tipoTelefone);

                    return telefone;
                })
                .collect(Collectors.toList());
    }

    private List<Email> getEmailByClienteDTO(ClienteDTO dto) {

        return dto.getEmail()
                .stream()
                .map(emailDTO -> {
                    Email email = new Email();
                    email.setNome(emailDTO.getNome());

                    return email;
                })
                .collect(Collectors.toList());
    }
}
