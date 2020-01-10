package br.com.surittec.cliente.entity;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "cliente", schema = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @CPF
    @Column(name = "cpf")
    private String cpf;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "endereco")
    private Endereco endereco;
}
