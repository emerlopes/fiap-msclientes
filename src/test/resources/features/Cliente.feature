# language: pt

Funcionalidade: Cliente
  Eu como cliente
  Desejo realizar um cadastro
  Para que eu possa ter acesso ao sistema

  Cenario: Cadastrar cliente via API
    Dado que eu tenha os dados do cliente
      | nome | endereco | telefone        | email          |
      | Joao | Rua 1    | (11) 91234-5678 | joao@email.com |
    Quando eu enviar uma requisicao POST para '/clientes' com os dados do cliente
    Entao a resposta deve ter o codigo de status 201
    E o cliente cadastrado deve ter os mesmos dados informados: nome, endereço, telefone e email

  Esquema do Cenario: Tentativa cadastro de cliente com com dados invalidos
    Dado que eu tenha os dados do cliente
      | nome   | endereco   | telefone   | email   |
      | <nome> | <endereco> | <telefone> | <email> |
    Quando eu enviar uma requisicao POST para '/clientes' com os dados do cliente
    Entao a resposta deve ter o codigo de status 400
    E a resposta deve ter a mensagem de erro: "<mensagem_erro>"

    Exemplos:
      | nome | endereco | telefone        | email          | mensagem_erro                       |
      |      | Rua 1    | (11) 91234-5678 | email@mail.com | O nome do cliente e obrigatorio     |
      | Joao |          | (11) 91234-5678 | email@mail.com | O endereco do cliente e obrigatorio |
      | Joao | Rua 1    |                 | email@mail.com | O telefone do cliente e obrigatorio |
      | Joao | Rua 1    | (11) 91234-5678 |                | O email do cliente e obrigatorio    |
      | Joao | Rua 1    | 12345678        | email@mail.com | Formato de telefone invalido        |
      | Joao | Rua 1    | (11) 91234-5678 | emailmail.com  | O email do cliente e invalido       |


  Esquema do Cenario: Buscar cliente por id
    Dado que eu tenha um cliente cadastrado
      | nome   | endereco   | telefone   | email   |
      | <nome> | <endereco> | <telefone> | <email> |
    Quando eu enviar uma requisicao GET para '/clientes/{id}'
    Entao a resposta deve ter o codigo de status 200
    E a resposta deve ter os dados do cliente: nome, endereco, telefone e email

    Exemplos:
      | nome | endereco | telefone        | email          |
      | Joao | Rua 1    | (11) 91234-5678 | joao@email.com |

  Esquema do Cenario: Deletar cliente por id
    Dado que eu tenha um cliente cadastrado
      | nome   | endereco   | telefone   | email   |
      | <nome> | <endereco> | <telefone> | <email> |
    Quando eu enviar uma requisicao DELETE para '/clientes/{id}'
    Entao a resposta deve ter o codigo de status 204

    Exemplos:
      | nome | endereco | telefone        | email          |
      | Joao | Rua 1    | (11) 91234-5678 | joao@email.com |




