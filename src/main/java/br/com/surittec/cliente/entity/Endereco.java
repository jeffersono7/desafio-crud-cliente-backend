package br.com.surittec.cliente.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "endereco", schema = "cliente")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "cep")
    private String cep;

    @NotNull
    @Column(name = "logradouro")
    private String logradouro;

    @NotNull
    @Column(name = "bairro")
    private String bairro;

    @NotNull
    @Column(name = "cidade")
    private String cidade;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "uf")
    private String uf;

    @Column(name = "complemento")
    private String complemento;
}
