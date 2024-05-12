package br.com.fiapmsclientess.infrastructure.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "tb01_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interno")
    private Long idInterno;

    @Column(name = "id_externo")
    @NotBlank(message = "O id externo do cliente e obrigatorio")
    private UUID idExterno;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "telefone", unique = true)
    private String telefone;

    @Column(name = "email")
    private String email;

}
