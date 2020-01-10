package br.com.surittec.cliente.service;

import br.com.surittec.cliente.entity.Cliente;
import br.com.surittec.cliente.entity.Email;
import br.com.surittec.cliente.entity.Endereco;
import br.com.surittec.cliente.entity.Telefone;
import br.com.surittec.cliente.repository.ClienteRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;
    private final EmailService emailService;

    private final ObjectMapper objectMapper;

    public Cliente criar(ObjectNode objectNode) {
        Cliente cliente = getClienteByNode(objectNode);
        Endereco endereco = getEnderecoByNode(objectNode);

        Endereco enderecoSalvo = enderecoService.save(endereco);
        cliente.setEndereco(enderecoSalvo);

        cliente.setId(null);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        List<Email> emails = getEmailByNode(objectNode);
        emails.forEach(email -> email.setCliente(clienteSalvo));
        emailService.salvar(emails);

        List<Telefone> telefones = getTelefoneByNode(objectNode);
        telefones.forEach(telefone -> telefone.setCliente(cliente));
        telefoneService.salvar(telefones);

        return clienteSalvo;
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
