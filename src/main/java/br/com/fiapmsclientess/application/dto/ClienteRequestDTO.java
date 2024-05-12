package br.com.fiapmsclientess.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
public class ClienteRequestDTO {

    @JsonProperty("nome")
    @NotBlank(message = "O nome do cliente e obrigatorio")
    private String nome;

    @JsonProperty("endereco")
    @NotBlank(message = "O endereco do cliente e obrigatorio")
    private String endereco;

    @JsonProperty("telefone")
    @NotBlank(message = "O telefone do cliente e obrigatorio")
    @Pattern(regexp = "^\\(?(\\d{2})\\)?[ .-]?(\\d{4,5})[ .-]?(\\d{4})$", message = "Formato de telefone invalido")
    private String telefone;

    @JsonProperty("email")
    @NotBlank(message = "O email do cliente e obrigatorio")
    @Email(message = "O email do cliente e invalido")
    private String email;
}
