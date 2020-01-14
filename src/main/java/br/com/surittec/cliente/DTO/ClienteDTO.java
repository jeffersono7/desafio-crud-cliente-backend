package br.com.surittec.cliente.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;

    private List<TelefoneDTO> telefone;
    private List<EmailDTO> email;
}
