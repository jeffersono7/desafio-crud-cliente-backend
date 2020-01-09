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

@Data
@Entity
@Table(name = "email", schema = "cliente")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column("id")
    private Long id;

    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;
}
