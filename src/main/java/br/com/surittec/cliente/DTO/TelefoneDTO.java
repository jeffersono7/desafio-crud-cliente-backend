package br.com.surittec.cliente.DTO;

import lombok.Data;

@Data
public class TelefoneDTO {

    private String numero;
    private TipoTelefoneDTO tipoTelefone;
}
