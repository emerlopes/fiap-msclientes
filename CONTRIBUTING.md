# Contribuindo para fiap-msclientes

Agradecemos seu interesse em contribuir para o `fiap-msclientes`! As contribuições são bem-vindas de todos os membros da comunidade, independentemente do nível de experiência. Este documento guia como participar do projeto, sugerindo mudanças e melhorando o código.

## Código de Conduta
O projeto adere a um código de conduta que todos os contribuintes devem seguir. Leia [Código de Conduta](CODE_OF_CONDUCT.md) para garantir que a comunidade permaneça acolhedora e respeitosa.

## Princípios de Design e Qualidade de Código

### Arquitetura Limpa
Para assegurar a manutenção e escalabilidade do nosso microserviço, adotamos os princípios da Arquitetura Limpa. Isso significa que o design do sistema deve ser:
- **Independente de Frameworks**: O sistema não deve ser um reflexo da arquitetura de frameworks usados, mas sim utilizar frameworks como ferramentas, sem que eles ditem a arquitetura.
- **Testável**: A lógica de negócios pode ser testada sem a UI, o banco de dados, ou qualquer elemento externo.
- **Independente de UI**: A UI pode mudar facilmente, sem alterar o restante do sistema. A lógica de negócios não está atada à UI.
- **Independente de Banco de Dados**: Você pode trocar o Oracle por SQL Server, por exemplo, sem alterar a lógica de negócios.
- **Independente de qualquer agente externo**: As regras de negócio não devem saber nada nem sobre a web, nem sobre o banco de dados, etc.

### Princípios SOLID
Para garantir a qualidade do código, seguimos os princípios SOLID:
- **S - Single Responsibility Principle**: Cada módulo ou classe deve ter responsabilidade sobre uma única parte da funcionalidade.
- **O - Open/Closed Principle**: As entidades de software devem ser abertas para extensão, mas fechadas para modificação.
- **L - Liskov Substitution Principle**: Objetos em um programa devem ser substituíveis por instâncias de suas subtipos sem alterar a corretude do programa.
- **I - Interface Segregation Principle**: Muitas interfaces específicas são melhores que uma interface única.
- **D - Dependency Inversion Principle**: Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações.

## Como Contribuir
Para contribuir com o `fiap-msclientes`, siga estes passos:

1. **Fork o Repositório**
    - Faça um "fork" do repositório para criar uma cópia em sua conta do GitHub.

2. **Clone o Repositório**
    - Clone o repositório forkado para sua máquina local para fazer alterações.

3. **Crie uma Branch**
    - Crie uma nova branch para suas mudanças. Isso ajuda a manter o desenvolvimento organizado e separado.
    - Exemplo: `git checkout -b feature/nova-funcionalidade`

4. **Faça suas Alterações**
    - Adicione ou altere o código conforme necessário para sua contribuição.
    - Escreva e execute testes que cobrem as novas funcionalidades ou correções.

5. **Commit suas Mudanças**
    - Commits Atômicos: Crie um commit para cada modificação significativa, mantendo uma linha do tempo clara e facilitando a revisão de mudanças específicas.
    - **Padronização dos Commits**:
        - Utilize o formato do Conventional Commits para mensagens claras e padronizadas:
            - `chore`: Atualização de tarefas que não impactam o código de produção.
            - `feat`: Adições de novas funcionalidades.
            - `fix`: Correções de bugs.
            - `refactor`: Mudanças que não alteram a funcionalidade final.
            - `docs`: Inclusão ou alteração em arquivos de documentação.

6. **Push para o GitHub**
    - Envie suas mudanças para o GitHub.
    - Exemplo: `git push origin feature/nova-funcionalidade`

7. **Abra um Pull Request**
    - Abra um pull request do branch no seu fork para o branch `main` do repositório original.
    - Descreva as mudanças realizadas e qualquer informação adicional que possa ajudar os revisores a entender suas contribuições.

8. **Revisão**
    - Aguarde a revisão do seu pull request. Responda a qualquer feedback e faça alterações se necessário.

## Recursos
- [Documentação Oficial do Spring Boot](https://spring.io/projects/spring-boot)
- [Tutorial de Git](https://www.atlassian.com/git/tutorials)

## Ajuda
Se precisar de ajuda, você pode perguntar na seção de issues do GitHub.

Obrigado por contribuir para o `fiap-msclientes`!
