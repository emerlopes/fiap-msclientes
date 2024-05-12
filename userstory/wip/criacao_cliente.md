## História de Usuário: Criação de Cliente

**Como** um usuário do sistema de gerenciamento de clientes  
**Eu quero** poder adicionar novos clientes ao sistema  
**Para que** eu possa manter um registro atualizado dos clientes que utilizam nossos serviços.

### Critérios de Aceitação:
1. O sistema deve permitir a entrada de nome, endereço, telefone e email do cliente.
2. A entrada de dados deve ser validada antes de ser salva no banco de dados.
3. Um cliente não pode ser adicionado se já existir um cliente com o mesmo email.

### Tarefas
- [x] Configurar o projeto Spring Boot - **Estimativa: 2 horas**
- [ ] Criar a classe de entidade `Cliente` com atributos como nome, endereço, telefone e email - **Estimativa: 1 hora**
- [ ] Implementar o repositório para CRUD utilizando Spring Data JPA - **Estimativa: 2 horas**
- [ ] Desenvolver o serviço para adicionar um novo cliente, incluindo validações - **Estimativa: 3 horas**
- [ ] Criar e configurar a camada de controle para receber e responder a solicitações HTTP - **Estimativa: 2 horas**
- [ ] Escrever testes unitários para a lógica de criação de clientes - **Estimativa: 2 horas**

**Estimativa Total da História: 12 horas**