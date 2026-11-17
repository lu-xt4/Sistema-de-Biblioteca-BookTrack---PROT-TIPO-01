# Sistema-de-Biblioteca-BookTrack---PROT-TIPO-01
O trabalho publicado é referente a uma atividade avaliativa do curso de Análise e Desenvolvimento de Sistemas da Universidade Potiguar (UNP).

# BookTrack — Sistema de Gerenciamento de Biblioteca

# Equipe/Grupo:

**1. Luan Calixto Barbosa | 12826124000@ulife.com.br (E-mail institucional)**

**2. Felipe Gabriel Costa de Lima | 12826120702@ulife.com.br (E-mail institucional)**

**3. Gabriel Felinto Rodrigues | 12826127117@ulife.com.br (E-mail institucional)**

**4. Mateus Batista de Siqueira Souza Dantas | 12826116593@ulife.com.br (E-mail institucional)**

**5. João Vitor Pegado Autonamo | 12826133893@ulife.com.br (E-mail institucional)**

## Sobre o projeto

O BookTrack é um sistema de gerenciamento de biblioteca desenvolvido em Java, com o objetivo de facilitar e organizar o controle de livros, usuários, empréstimos e devoluções.

Desenvolvido como uma aplicação de console, o sistema simula o funcionamento de uma biblioteca por meio de uma interface interativa, na qual diferentes tipos de usuários podem acessar funcionalidades específicas de acordo com suas permissões.

A aplicação possui três perfis de acesso: **Bibliotecário, Cliente e Gerente**, cada um com responsabilidades e níveis de acesso distintos.

O projeto foi desenvolvido aplicando conceitos de Programação Orientada a Objetos (POO), padrões de projeto (Design Patterns), estruturas de dados e lógica de programação.

## Funcionalidades

### 1. Gerenciamento de livros

O sistema permite que os bibliotecários realizem o cadastro e o gerenciamento do catálogo da biblioteca.

- Cadastro de novos livros.
- Registro de código, título, autor e quantidade de exemplares.
- Consulta ao catálogo de livros.
- Verificação da disponibilidade de exemplares.
- Controle da quantidade de livros disponíveis para empréstimo.
- Identificação de livros indisponíveis.

O sistema também possui seis livros previamente cadastrados para facilitar a utilização inicial.

### 2. Gerenciamento de usuários

O BookTrack possui controle de acesso baseado em três perfis:

**Bibliotecário**
- Realiza o cadastro de novos clientes.
- Cria as credenciais de acesso dos clientes.
- Gerencia o catálogo de livros.
- Consulta a disponibilidade dos exemplares.
- Registra empréstimos.
- Registra devoluções.
- Consulta os empréstimos registrados.

**Cliente**
- Acessa o sistema utilizando suas credenciais.
- Consulta o catálogo de livros.
- Verifica a disponibilidade dos exemplares.
- Consulta seus empréstimos em andamento.
- Consulta seu histórico de empréstimos e devoluções.

**Gerente**
- Consulta o catálogo de livros.
- Consulta todos os empréstimos registrados.
- Consulta as devoluções realizadas.
- Visualiza os usuários cadastrados, incluindo funcionários e clientes.

O gerente possui acesso direcionado ao acompanhamento e à consulta das informações administrativas, sem permissões para modificar os registros.

### 3. Controle de empréstimos

O BookTrack possui um sistema de empréstimos com regras específicas para garantir a organização e o controle dos exemplares.

- Cada cliente pode manter até **2 livros emprestados simultaneamente**.
- O prazo padrão de empréstimo é de **7 dias**.
- Livros sem exemplares disponíveis não podem ser emprestados.
- Cada empréstimo possui um identificador próprio.
- Cada empréstimo é associado ao cliente e ao livro correspondente.
- As datas de empréstimo e devolução prevista são geradas automaticamente.
- O sistema permite consultar os empréstimos em andamento e os registros anteriores.

### 4. Controle de devoluções

O sistema permite que os bibliotecários registrem a devolução dos livros emprestados.

Durante esse processo:

- O sistema registra a data efetiva da devolução.
- O status do empréstimo é atualizado.
- A disponibilidade do exemplar é restaurada no catálogo.
- O sistema identifica se a devolução ocorreu dentro ou fora do prazo.

Os empréstimos podem apresentar três situações:

- **EM ANDAMENTO:** livro ainda não devolvido.
- **DEVOLVIDO NO PRAZO:** livro devolvido até a data prevista.
- **DEVOLVIDO COM ATRASO:** livro devolvido após a data prevista.

Não são aplicadas multas ou penalidades financeiras por atraso, e o sistema não possui funcionalidade de renovação de empréstimos.

## Regras de negócio

| Regra | Descrição |
|---|---|
| Limite de empréstimos | Máximo de 2 livros por cliente |
| Prazo de empréstimo | 7 dias |
| Disponibilidade | Não permite empréstimos de livros indisponíveis |
| Devolução | Restaura a disponibilidade do exemplar |
| Identificação | Cada empréstimo possui um identificador |
| Atrasos | Identifica devoluções realizadas após o prazo |
| Multas | Não são aplicadas |
| Renovação | Não disponível |

## Arquitetura e organização do sistema

O BookTrack foi estruturado utilizando classes com responsabilidades específicas, buscando separar a representação dos dados, as regras de negócio, o armazenamento das informações e a interação com os usuários.

A organização principal do sistema é composta por:

| Classe | Responsabilidade |
|---|---|
| Livro | Representa os livros cadastrados na biblioteca |
| Cliente | Representa os clientes e suas credenciais |
| Emprestimo | Armazena os dados e a situação dos empréstimos |
| EmprestimoFactory | Responsável pela criação dos objetos de empréstimo |
| CatalogoLivros | Gerencia o armazenamento e a busca de livros |
| CadastroClientes | Gerencia o cadastro, a busca e a autenticação de clientes |
| RegistroEmprestimos | Gerencia os registros e as consultas de empréstimos |
| SistemaBiblioteca | Coordena os subsistemas e centraliza as regras de negócio |
| Menus | Controla a interação com os usuários e apresenta as funcionalidades |
| Main | Inicializa a aplicação |

## Padrões de projeto (Design Patterns)

Um dos aspectos importantes do BookTrack é a aplicação de três padrões de projeto, utilizados para organizar a arquitetura e facilitar a manutenção do código.

### 1. Singleton — Padrão Criacional

Implementado na classe `SistemaBiblioteca`.

O padrão Singleton garante que exista apenas uma instância da classe responsável pelo gerenciamento central do sistema.

Sua implementação utiliza:

- Atributo estático para armazenar a instância.
- Construtor privado, impedindo a criação de objetos externos.
- Método `getInstancia()` para fornecer acesso à instância única.

Dessa maneira, diferentes partes da aplicação utilizam o mesmo objeto de gerenciamento, mantendo o acesso centralizado aos dados e às regras de negócio.

### 2. Facade (Fachada) — Padrão Estrutural

Implementado também na classe `SistemaBiblioteca`.

O padrão Facade fornece uma interface simplificada para acessar funcionalidades de diferentes componentes internos.

No BookTrack, a classe `SistemaBiblioteca` funciona como uma fachada que coordena os seguintes subsistemas:

- `CatalogoLivros`
- `CadastroClientes`
- `RegistroEmprestimos`
- `EmprestimoFactory`

Assim, a classe `Menus` não precisa acessar diretamente cada subsistema. Ela utiliza os métodos disponibilizados pela fachada, que centraliza a comunicação e a execução das regras de negócio.

### 3. Factory (Fábrica) — Padrão Criacional

Implementado na classe `EmprestimoFactory`.

O padrão Factory tem como objetivo centralizar a criação de objetos, evitando que a lógica de instanciação fique espalhada pelo sistema.

No BookTrack, a fábrica é responsável por criar novos objetos `Emprestimo`, recebendo as informações necessárias e calculando automaticamente:

- A data atual do empréstimo.
- A data prevista para devolução, considerando o prazo estabelecido.

Dessa forma, a classe `SistemaBiblioteca` solicita a criação de um empréstimo à fábrica, que retorna o objeto devidamente inicializado.

### Benefícios da utilização dos padrões

A aplicação desses padrões contribui para:

- Centralização do gerenciamento do sistema.
- Simplificação da comunicação entre os componentes.
- Separação das responsabilidades.
- Organização da criação de objetos.
- Maior facilidade de manutenção e compreensão do código.

## Conceitos de Programação Orientada a Objetos

Além dos padrões de projeto, o BookTrack aplica conceitos fundamentais da Programação Orientada a Objetos.

**Encapsulamento**

Os atributos das classes são declarados como privados (`private`), controlando o acesso às informações por meio de métodos públicos, como getters e setters.

**Abstração**

As principais entidades do domínio da biblioteca são representadas por classes próprias, como `Livro`, `Cliente` e `Emprestimo`, permitindo organizar suas características e comportamentos.

**Composição e colaboração entre objetos**

As classes trabalham em conjunto para executar as funcionalidades do sistema. A classe `SistemaBiblioteca`, por exemplo, mantém referências aos subsistemas que coordena.

## Tecnologias e recursos utilizados

- **Java:** linguagem de programação utilizada no desenvolvimento.
- **Programação Orientada a Objetos (POO):** estruturação da aplicação por meio de classes e objetos.
- **Scanner:** leitura de dados e interação com o usuário pelo console.
- **LocalDate:** manipulação de datas e controle dos prazos de empréstimo.
- **Arrays:** armazenamento e gerenciamento dos registros em memória.
- **Estruturas condicionais:** validação de regras e controle das decisões.
- **Estruturas de repetição:** gerenciamento dos menus e fluxos de execução.
- **Switch/Case:** direcionamento das opções selecionadas pelos usuários.

## Características técnicas e limitações

- Aplicação executada diretamente pelo terminal.
- Interface interativa baseada em menus.
- Autenticação de usuários por nome de usuário e senha.
- Três perfis de acesso com permissões distintas.
- Organização do código em classes.
- Aplicação dos padrões Singleton, Facade e Factory.
- Armazenamento dos dados por meio de arrays tradicionais.
- Capacidade de até 100 livros, 100 clientes e 100 empréstimos.
- Cadastro de seis livros iniciais.
- Controle automático das datas de empréstimo e devolução.

**Armazenamento:** os dados são mantidos em memória durante a execução do programa. Não existe integração com banco de dados ou mecanismo de persistência externa. Portanto, os registros criados durante a execução são perdidos quando a aplicação é encerrada.

## Objetivos acadêmicos

O desenvolvimento do BookTrack tem como objetivo consolidar conhecimentos adquiridos durante a formação em Análise e Desenvolvimento de Sistemas, especialmente nas áreas de:

- Lógica de programação.
- Programação Orientada a Objetos.
- Padrões de projeto (Design Patterns).
- Organização e arquitetura de software.
- Estruturas de dados.
- Manipulação de informações.
- Aplicação de regras de negócio.
- Desenvolvimento de aplicações interativas.

## Considerações finais

O BookTrack representa uma aplicação prática dos conceitos de desenvolvimento de software, simulando um ambiente de gerenciamento de biblioteca.

Através de sua implementação, foi possível aplicar conceitos de orientação a objetos e padrões de projeto, demonstrando como diferentes componentes podem trabalhar em conjunto para construir uma solução organizada e funcional.

O projeto também proporciona experiência na modelagem de entidades, separação de responsabilidades, gerenciamento de dados e desenvolvimento de regras de negócio utilizando Java.

---

**Projeto acadêmico desenvolvido em Java — Análise e Desenvolvimento de Sistemas.**
