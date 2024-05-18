package br.com.fiapmsclientess.infrastructure.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb01_cliente")
@Accessors(chain = true)
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interno")
    @Setter(AccessLevel.NONE)
    private Long idInterno;

    @Column(name = "id_externo")
    private UUID idExterno;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    private String telefone;

    //    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
