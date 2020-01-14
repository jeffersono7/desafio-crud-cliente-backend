package br.com.surittec.cliente.service;

import br.com.surittec.cliente.DTO.ClienteDTO;
import br.com.surittec.cliente.entity.Cliente;
import br.com.surittec.cliente.entity.Email;
import br.com.surittec.cliente.entity.Endereco;
import br.com.surittec.cliente.entity.Telefone;
import br.com.surittec.cliente.entity.TipoTelefone;
import br.com.surittec.cliente.repository.ClienteRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private final ObjectMapper objectMapper;

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

    public Cliente atualizar(ObjectNode objectNode) {

        Cliente cliente = getClienteByNode(objectNode);
        Endereco endereco = getEnderecoByNode(objectNode);

        Endereco enderecoSalvo = enderecoService.save(endereco);
        cliente.setEndereco(enderecoSalvo);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        List<Email> emails = getEmailByNode(objectNode);
        emails.forEach(email -> email.setCliente(clienteSalvo));
        emailService.salvar(emails);

        List<Telefone> telefones = getTelefoneByNode(objectNode);
        telefones.forEach(telefone -> telefone.setCliente(cliente));
        telefoneService.salvar(telefones);

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

    private Endereco getEnderecoByNode(ObjectNode node) {
        JsonParser enderecoNode = node.get("endereco").traverse();

        try {
            return objectMapper.readValue(enderecoNode, Endereco.class);
        } catch (IOException e) {
            return null;
        }
    }

    private Cliente getClienteByNode(ObjectNode node) {

        JsonParser clienteNode = node.deepCopy().traverse();

        try {
            return objectMapper.readValue(clienteNode, Cliente.class);
        } catch (IOException e) {
            return null;
        }
    }

    private List<Email> getEmailByNode(ObjectNode node) {
        JsonParser emailNode = node.get("email").traverse();

        try {
            return objectMapper.readValue(emailNode, List.class);
        } catch (IOException e) {
            return null;
        }
    }

    private List<Telefone> getTelefoneByNode(ObjectNode node) {
        JsonParser telefoneNode = node.get("telefone").traverse();

        try {
            return objectMapper.readValue(telefoneNode, List.class);
        } catch (IOException e) {
            return null;
        }
    }
}
