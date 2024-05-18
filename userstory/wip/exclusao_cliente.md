## História de Usuário: Exclusão de Cliente

**Como** um administrador do sistema  
**Eu quero** poder excluir clientes do sistema  
**Para que** eu possa remover registros que não são mais necessários ou desejados.

### Critérios de Aceitação:
1. O sistema deve permitir a exclusão de um cliente por ID.
2. Caso o cliente não seja encontrado, o sistema deve retornar uma mensagem de erro adequada.
3. A exclusão deve ser segura para evitar a remoção acidental de dados.

### Tarefas
- [x] Desenvolver o método no serviço que permite a exclusão de um cliente - **Estimativa: 2 horas**
- [x] Implementar as rotas na camada de controle para excluir um cliente por ID - **Estimativa: 1 hora**
- [X] Escrever testes unitários para a funcionalidade de exclusão de clientes - **Estimativa: 2 horas**

**Estimativa Total da História: 5 horas**