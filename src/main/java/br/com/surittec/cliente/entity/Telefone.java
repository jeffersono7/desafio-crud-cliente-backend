package br.com.surittec.cliente.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "telefone", schema = "CLIENTE")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 8, max = 11)
    @Column(name = "numero")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "tipo_telefone")
    private TipoTelefone tipoTelefone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;
}
