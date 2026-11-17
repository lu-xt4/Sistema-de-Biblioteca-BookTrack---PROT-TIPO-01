//Importamos a biblioteca LocalDate para trabalhar com datas
import java.time.LocalDate;
//Importamos a biblioteca Scanner para que o usuário possa interagir com o sistema
import java.util.Scanner;

//------------------------- CLASSE LIVRO ------------------------- 
class Livro {

    //Atributos da classe (CARACTERÍSTICAS)
    private int codigo;
    private String titulo;
    private String autor;
    private int quantidade;

    //Inicializamos o objeto Livro com seus dados iniciais (CONSTRUTOR)
    public Livro(int codigo, String titulo, String autor, int quantidade) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.quantidade = quantidade;
    }

    //Retornamos os atributos com os Getters
    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    //Atualiza os dados sobre os atributos com Setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

//------------------------- CLASSE CLIENTE -------------------------
class Cliente {

    //Atributos da classe (CARACTERÍSTICAS)
    private int id;
    private String nomeCompleto;
    private String usuario;
    private String senha;

    //Inicializamos o objeto Cliente com seus dados iniciais (CONSTRUTOR)
    public Cliente(int id, String nomeCompleto, String usuario, String senha) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.usuario = usuario;
        this.senha = senha;
    }

    //Retornamos os atributos com os Getters
    public int getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    //Atualiza os dados sobre os atributos com Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

//------------------------- CLASSE EMPRESTIMO ------------------------- 
class Emprestimo {

    //Atributos da classe (CARACTERÍSTICAS)
    private int id;
    private int idCliente;
    private int codigoLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao; //fica null enquanto o livro não for devolvido
    private String situacao; //"EM ANDAMENTO", "DEVOLVIDO NO PRAZO" ou "DEVOLVIDO COM ATRASO"

    //Todo empréstimo novo começa sem data de devolução e "EM ANDAMENTO" (CONSTRUTOR)
    public Emprestimo(int id, int idCliente, int codigoLivro, LocalDate dataEmprestimo, LocalDate dataPrevista) {
        this.id = id;
        this.idCliente = idCliente;
        this.codigoLivro = codigoLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.dataDevolucao = null;
        this.situacao = "EM ANDAMENTO";
    }

    //Retornamos os atributos com os Getters
    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getCodigoLivro() {
        return codigoLivro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public String getSituacao() {
        return situacao;
    }

    //Atualiza os dados sobre os atributos com Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setCodigoLivro(int codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}

// ------------------------- CLASSE EMPRESTIMOFACTORY ------------------------- 
// PADRÃO CRIACIONAL
// Esta classe é responsável por criar os objetos Emprestimo.
// Quem precisa de um empréstimo pede para a fábrica, que calcula
// as datas automaticamente (hoje e hoje + prazo).
// ----------------------------------------------------------------------------

class EmprestimoFactory {

    //Cria um empréstimo novo com a data de hoje e a data prevista de devolução
    public Emprestimo criar(int id, int idCliente, int codigoLivro, int prazoDias) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataPrevista = hoje.plusDays(prazoDias);
        return new Emprestimo(id, idCliente, codigoLivro, hoje, dataPrevista);
    }
}

// ------------------------- CLASSE CATALOGOLIVROS ------------------------- 
// SUBSISTEMAS: cada um cuida de um array e do seu contador.
// Eles não conhecem as regras do sistema, só guardam e buscam dados.
// -------------------------------------------------------------------------

class CatalogoLivros {

    //Array com capacidade de 100 posições e seu contador
    private Livro[] livros = new Livro[100];
    private int totalLivros = 0;

    public boolean estaCheio() {
        return totalLivros == 100;
    }

    //Guarda o livro na primeira posição livre e aumenta o contador
    public void adicionar(Livro livro) {
        livros[totalLivros] = livro;
        totalLivros++;
    }

    //Devolve o livro encontrado, ou null se não encontrar
    public Livro buscar(int codigo) {
        for (int i = 0; i < totalLivros; i++) {
            if (livros[i].getCodigo() == codigo) {
                return livros[i];
            }
        }
        return null;
    }

    public int getTotal() {
        return totalLivros;
    }

    public Livro get(int posicao) {
        return livros[posicao];
    }
}

//------------------------- CLASS CADASTRO CLIENTE -------------------------

class CadastroClientes {

    private Cliente[] clientes = new Cliente[100];
    private int totalClientes = 0;

    public boolean estaCheio() {
        return totalClientes == 100;
    }

    public void adicionar(Cliente cliente) {
        clientes[totalClientes] = cliente;
        totalClientes++;
    }

    public Cliente buscarPorId(int id) {
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getId() == id) {
                return clientes[i];
            }
        }
        return null;
    }

    //Confere se já existe um cliente com esse nome de usuário
    public boolean usuarioExiste(String usuario) {
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    //Devolve o cliente se usuário e senha estiverem certos, ou null
    public Cliente autenticar(String usuario, String senha) {
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getUsuario().equals(usuario) && clientes[i].getSenha().equals(senha)) {
                return clientes[i];
            }
        }
        return null;
    }

    public int getTotal() {
        return totalClientes;
    }

    public Cliente get(int posicao) {
        return clientes[posicao];
    }
}

// ------------------------- CLASS REGISTROEMPRESTIMO ------------------------- 

class RegistroEmprestimos {

    private Emprestimo[] emprestimos = new Emprestimo[100];
    private int totalEmprestimos = 0;

    public boolean estaCheio() {
        return totalEmprestimos == 100;
    }

    public void adicionar(Emprestimo emprestimo) {
        emprestimos[totalEmprestimos] = emprestimo;
        totalEmprestimos++;
    }

    public Emprestimo buscar(int id) {
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i].getId() == id) {
                return emprestimos[i];
            }
        }
        return null;
    }

    //Conta quantos livros o cliente tem emprestados agora (devolvidos não contam)
    public int contarEmAndamento(int idCliente) {
        int quantidade = 0;
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i].getIdCliente() == idCliente
                    && emprestimos[i].getSituacao().equals("EM ANDAMENTO")) {
                quantidade++;
            }
        }
        return quantidade;
    }

    public int getTotal() {
        return totalEmprestimos;
    }

    public Emprestimo get(int posicao) {
        return emprestimos[posicao];
    }
}

// ------------------------- SISTEMA BIBLIOTECA ------------------------- 
// PADRÃO ESTRUTURAL: FACADE 
// Esta classe é a "porta de entrada" única. 
// O Menus só conversa com ela,e ela organiza o trabalho entre os subsistemas (catálogo, clientes,
//      empréstimos) e a fábrica. O Menus não precisa conhecer nenhum deles.
// ----------------------------------------------------------------------

// PADRÃO CRIACIONAL: SINGLETON
//   Só pode existir um SistemaBiblioteca em todo o programa. O construtor
//   é private e o objeto é obtido por getInstancia().
// Os métodos de cadastro, empréstimo e devolução retornam:
//   null  -> deu certo
//   texto -> mensagem explicando o erro
// ---------------------------------------------------------------

class SistemaBiblioteca {

    //SINGLETON: guarda a única instância. "static" significa que pertence à
    //classe (e não a um objeto), então existe uma só, mesmo sem criar objetos
    private static SistemaBiblioteca instancia;

    //Os subsistemas e a fábrica que a fachada coordena
    private CatalogoLivros catalogo = new CatalogoLivros();
    private CadastroClientes cadastroClientes = new CadastroClientes();
    private RegistroEmprestimos registroEmprestimos = new RegistroEmprestimos();
    private EmprestimoFactory fabricaEmprestimos = new EmprestimoFactory();

    //Regras dos empréstimos
    private int limiteLivrosPorCliente = 2;
    private int prazoDias = 7;

    //SINGLETON: construtor private, ninguém de fora consegue fazer "new SistemaBiblioteca()"
    private SistemaBiblioteca() {
        carregarLivrosIniciais();
    }

    //SINGLETON: cria a instância na primeira vez e depois sempre devolve a mesma
    public static SistemaBiblioteca getInstancia() {
        if (instancia == null) {
            instancia = new SistemaBiblioteca();
        }
        return instancia;
    }

    //Livros que já vêm cadastrados quando o programa inicia
    private void carregarLivrosIniciais() {
        cadastrarLivro(1, "Dom Casmurro", "Machado de Assis", 3);
        cadastrarLivro(2, "O Cortiço", "Aluísio Azevedo", 2);
        cadastrarLivro(3, "Capitães da Areia", "Jorge Amado", 1);
        cadastrarLivro(4, "Vidas Secas", "Graciliano Ramos", 4);
        cadastrarLivro(5, "A Hora da Estrela", "Clarice Lispector", 2);
        cadastrarLivro(6, "Memórias Póstumas de Brás Cubas", "Machado de Assis", 5);
    }

    // ------------------------- ACESSO AOS DADOS -------------------------
    //O Menus usa estes métodos para percorrer os dados (a fachada repassa aos subsistemas)

    public int getTotalLivros() {
        return catalogo.getTotal();
    }

    public Livro getLivro(int posicao) {
        return catalogo.get(posicao);
    }

    public int getTotalClientes() {
        return cadastroClientes.getTotal();
    }

    public Cliente getCliente(int posicao) {
        return cadastroClientes.get(posicao);
    }

    public int getTotalEmprestimos() {
        return registroEmprestimos.getTotal();
    }

    public Emprestimo getEmprestimo(int posicao) {
        return registroEmprestimos.get(posicao);
    }

    // ------------------------- BUSCAS -------------------------
    //Todas devolvem o objeto encontrado, ou null se não encontrarem

    public Livro buscarLivro(int codigo) {
        return catalogo.buscar(codigo);
    }

    public Cliente buscarClientePorId(int id) {
        return cadastroClientes.buscarPorId(id);
    }

    public Emprestimo buscarEmprestimo(int id) {
        return registroEmprestimos.buscar(id);
    }

    //Confere se o nome de usuário já está em uso (por funcionário ou por cliente)
    private boolean usuarioJaExiste(String usuario) {
        if (usuario.equals("gerente01") || usuario.equals("bibliotecario01")
                || usuario.equals("bibliotecario02")) {
            return true;
        }
        return cadastroClientes.usuarioExiste(usuario);
    }

    public int contarEmprestimosEmAndamento(int idCliente) {
        return registroEmprestimos.contarEmAndamento(idCliente);
    }

    // ------------------------- AUTENTICAÇÃO (LOGIN) -------------------------

    //Devolve "GERENTE", "BIBLIOTECARIO" ou null se não for um funcionário
    public String autenticarFuncionario(String usuario, String senha) {
        if (usuario.equals("gerente01") && senha.equals("gerente123")) {
            return "GERENTE";
        }
        if (usuario.equals("bibliotecario01") && senha.equals("biblio123")) {
            return "BIBLIOTECARIO";
        }
        if (usuario.equals("bibliotecario02") && senha.equals("biblio456")) {
            return "BIBLIOTECARIO";
        }
        return null;
    }

    //Devolve o cliente se usuário e senha estiverem certos, ou null
    public Cliente autenticarCliente(String usuario, String senha) {
        return cadastroClientes.autenticar(usuario, senha);
    }

    // ------------------------- CADASTROS -------------------------

    public String cadastrarLivro(int codigo, String titulo, String autor, int quantidade) {
        if (catalogo.estaCheio()) {
            return "Limite de 100 livros atingido.";
        }
        if (codigo <= 0) {
            return "O código deve ser maior que zero.";
        }
        if (catalogo.buscar(codigo) != null) {
            return "Já existe um livro com o código " + codigo + ".";
        }
        if (quantidade <= 0) {
            return "A quantidade deve ser maior que zero.";
        }

        catalogo.adicionar(new Livro(codigo, titulo, autor, quantidade));
        return null;
    }

    public String cadastrarCliente(String nomeCompleto, String usuario, String senha) {
        if (cadastroClientes.estaCheio()) {
            return "Limite de 100 clientes atingido.";
        }
        if (usuarioJaExiste(usuario)) {
            return "O usuário '" + usuario + "' já está em uso.";
        }

        //O id do cliente é gerado automaticamente: 1, 2, 3...
        int novoId = cadastroClientes.getTotal() + 1;
        cadastroClientes.adicionar(new Cliente(novoId, nomeCompleto, usuario, senha));
        return null;
    }

    // ------------------------- EMPRÉSTIMO E DEVOLUÇÃO -------------------------

    public String registrarEmprestimo(int idCliente, int codigoLivro) {
        Cliente cliente = cadastroClientes.buscarPorId(idCliente);
        if (cliente == null) {
            return "Cliente não encontrado.";
        }
        Livro livro = catalogo.buscar(codigoLivro);
        if (livro == null) {
            return "Livro não encontrado.";
        }
        if (registroEmprestimos.estaCheio()) {
            return "Limite de 100 empréstimos atingido.";
        }
        if (registroEmprestimos.contarEmAndamento(idCliente) >= limiteLivrosPorCliente) {
            return "O cliente já está com " + limiteLivrosPorCliente + " livros emprestados (limite máximo).";
        }
        if (livro.getQuantidade() <= 0) {
            return "Livro indisponível: não há exemplares.";
        }

        //FACTORY: pedimos o empréstimo para a fábrica, que calcula as datas
        int novoId = registroEmprestimos.getTotal() + 1;
        Emprestimo novo = fabricaEmprestimos.criar(novoId, idCliente, codigoLivro, prazoDias);
        registroEmprestimos.adicionar(novo);

        //Um exemplar saiu da biblioteca
        livro.setQuantidade(livro.getQuantidade() - 1);
        return null;
    }

    public String registrarDevolucao(int idEmprestimo) {
        Emprestimo emprestimo = registroEmprestimos.buscar(idEmprestimo);
        if (emprestimo == null) {
            return "Empréstimo não encontrado.";
        }
        if (!emprestimo.getSituacao().equals("EM ANDAMENTO")) {
            return "Este empréstimo já foi devolvido.";
        }

        //Registra a data de hoje e confere se passou do prazo
        LocalDate hoje = LocalDate.now();
        emprestimo.setDataDevolucao(hoje);
        if (hoje.isAfter(emprestimo.getDataPrevista())) {
            emprestimo.setSituacao("DEVOLVIDO COM ATRASO");
        } else {
            emprestimo.setSituacao("DEVOLVIDO NO PRAZO");
        }

        //O exemplar voltou para a biblioteca
        Livro livro = catalogo.buscar(emprestimo.getCodigoLivro());
        livro.setQuantidade(livro.getQuantidade() + 1);
        return null;
    }
}

// ------------------------- CLASSE MENUS ------------------------- 

class Menus {

    //O Menus precisa do sistema para chamar as regras
    private SistemaBiblioteca sistema;
    private Scanner sc = new Scanner(System.in);

    //Recebe o sistema que foi criado na Main (CONSTRUTOR)
    public Menus(SistemaBiblioteca sistema) {
        this.sistema = sistema;
    }

    // ------------------------- TELA INICIAL E LOGIN -------------------------

    public void iniciar() {
        int opcao = 0;

        while (opcao != 2) {
            exibirTitulo("BOOKTRACK - SISTEMA DE BIBLIOTECA");
            System.out.println("1. Entrar");
            System.out.println("2. Encerrar sistema");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Sistema encerrado. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha 1 ou 2.");
            }
        }
    }

    private void login() {
        exibirTitulo("LOGIN");
        String usuario = lerTexto("Usuário: ");
        String senha = lerTexto("Senha: ");

        //Primeiro vemos se é um funcionário; se não for, procuramos entre os clientes
        String perfil = sistema.autenticarFuncionario(usuario, senha);

        if (perfil == null) {
            Cliente cliente = sistema.autenticarCliente(usuario, senha);
            if (cliente == null) {
                System.out.println("Usuário ou senha incorretos.");
            } else {
                menuCliente(cliente);
            }
        } else if (perfil.equals("GERENTE")) {
            menuGerente();
        } else {
            menuBibliotecario();
        }
    }

    // ------------------------- MENUS DOS PERFIS -------------------------

    private void menuBibliotecario() {
        int opcao = 0;

        while (opcao != 8) {
            exibirTitulo("ÁREA DO BIBLIOTECÁRIO");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Cadastrar cliente");
            System.out.println("3. Consultar catálogo de livros");
            System.out.println("4. Consultar disponibilidade de livros");
            System.out.println("5. Registrar empréstimo");
            System.out.println("6. Registrar devolução");
            System.out.println("7. Consultar empréstimos registrados");
            System.out.println("8. Sair");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    exibirTitulo("CATÁLOGO DE LIVROS");
                    listarCatalogo();
                    break;
                case 4:
                    exibirTitulo("DISPONIBILIDADE DE LIVROS");
                    listarDisponibilidade();
                    break;
                case 5:
                    registrarEmprestimo();
                    break;
                case 6:
                    registrarDevolucao();
                    break;
                case 7:
                    exibirTitulo("EMPRÉSTIMOS REGISTRADOS");
                    listarEmprestimos(0, 0);
                    break;
                case 8:
                    System.out.println("Saindo da área do bibliotecário...");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha um número de 1 a 8.");
            }
        }
    }

    //O menu do cliente recebe o cliente que fez login, para mostrar só os dados dele
    private void menuCliente(Cliente cliente) {
        int opcao = 0;

        while (opcao != 5) {
            exibirTitulo("ÁREA DO CLIENTE - " + cliente.getNomeCompleto());
            System.out.println("1. Consultar catálogo de livros");
            System.out.println("2. Consultar disponibilidade de livros");
            System.out.println("3. Consultar meus empréstimos");
            System.out.println("4. Consultar meu histórico de empréstimos e devoluções");
            System.out.println("5. Sair");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    exibirTitulo("CATÁLOGO DE LIVROS");
                    listarCatalogo();
                    break;
                case 2:
                    exibirTitulo("DISPONIBILIDADE DE LIVROS");
                    listarDisponibilidade();
                    break;
                case 3:
                    exibirTitulo("MEUS EMPRÉSTIMOS EM ANDAMENTO");
                    listarEmprestimos(cliente.getId(), 1);
                    break;
                case 4:
                    exibirTitulo("MEU HISTÓRICO");
                    listarEmprestimos(cliente.getId(), 0);
                    break;
                case 5:
                    System.out.println("Saindo da sua conta...");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha um número de 1 a 5.");
            }
        }
    }

    private void menuGerente() {
        int opcao = 0;

        while (opcao != 5) {
            exibirTitulo("ÁREA DO GERENTE");
            System.out.println("1. Consultar catálogo de livros");
            System.out.println("2. Consultar empréstimos");
            System.out.println("3. Consultar devoluções");
            System.out.println("4. Consultar usuários cadastrados");
            System.out.println("5. Sair da área do gerente");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    exibirTitulo("CATÁLOGO DE LIVROS");
                    listarCatalogo();
                    break;
                case 2:
                    exibirTitulo("EMPRÉSTIMOS");
                    listarEmprestimos(0, 0);
                    break;
                case 3:
                    exibirTitulo("DEVOLUÇÕES");
                    listarEmprestimos(0, 2);
                    break;
                case 4:
                    exibirTitulo("USUÁRIOS CADASTRADOS");
                    listarUsuarios();
                    break;
                case 5:
                    System.out.println("Saindo da área do gerente...");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha um número de 1 a 5.");
            }
        }
    }

    // ------------------------- AÇÕES DO BIBLIOTECÁRIO -------------------------

    private void cadastrarLivro() {
        exibirTitulo("CADASTRAR LIVRO");
        int codigo = lerInteiro("Código do livro: ");
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        int quantidade = lerInteiro("Quantidade disponível: ");

        //Se retornar null deu certo; se não, o retorno é a mensagem de erro
        String erro = sistema.cadastrarLivro(codigo, titulo, autor, quantidade);
        if (erro == null) {
            System.out.println("Livro cadastrado com sucesso!");
        } else {
            System.out.println("Erro: " + erro);
        }
    }

    private void cadastrarCliente() {
        exibirTitulo("CADASTRAR CLIENTE");
        String nome = lerTexto("Nome completo: ");
        String usuario = lerTexto("Nome de usuário: ");
        String senha = lerTexto("Senha: ");

        String erro = sistema.cadastrarCliente(nome, usuario, senha);
        if (erro == null) {
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println("Entregue estas credenciais ao cliente:");
            System.out.println("  Usuário: " + usuario);
            System.out.println("  Senha: " + senha);
        } else {
            System.out.println("Erro: " + erro);
        }
    }

    private void registrarEmprestimo() {
        exibirTitulo("REGISTRAR EMPRÉSTIMO");

        listarClientes();
        int idCliente = lerInteiro("ID do cliente (0 para cancelar): ");
        if (idCliente == 0) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println();
        listarDisponibilidade();
        int codigoLivro = lerInteiro("Código do livro (0 para cancelar): ");
        if (codigoLivro == 0) {
            System.out.println("Operação cancelada.");
            return;
        }

        String erro = sistema.registrarEmprestimo(idCliente, codigoLivro);
        if (erro != null) {
            System.out.println("Erro: " + erro);
            return;
        }

        //O empréstimo que acabou de ser criado é o último do array
        Emprestimo novo = sistema.getEmprestimo(sistema.getTotalEmprestimos() - 1);
        System.out.println("Empréstimo registrado com sucesso!");
        System.out.println("  Número do empréstimo: " + novo.getId());
        System.out.println("  Data do empréstimo: " + formatarData(novo.getDataEmprestimo()));
        System.out.println("  Devolver até: " + formatarData(novo.getDataPrevista()));
    }

    private void registrarDevolucao() {
        exibirTitulo("REGISTRAR DEVOLUÇÃO");

        System.out.println("Empréstimos em andamento:");
        listarEmprestimos(0, 1);
        int id = lerInteiro("Número do empréstimo (0 para cancelar): ");
        if (id == 0) {
            System.out.println("Operação cancelada.");
            return;
        }

        String erro = sistema.registrarDevolucao(id);
        if (erro != null) {
            System.out.println("Erro: " + erro);
            return;
        }

        Emprestimo e = sistema.buscarEmprestimo(id);
        System.out.println("Devolução registrada com sucesso!");
        System.out.println("  Data da devolução: " + formatarData(e.getDataDevolucao()));
        System.out.println("  Situação: " + e.getSituacao());
    }

    // ------------------------- LISTAGENS -------------------------

    private void listarCatalogo() {
        if (sistema.getTotalLivros() == 0) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (int i = 0; i < sistema.getTotalLivros(); i++) {
            Livro l = sistema.getLivro(i);
            System.out.println("Código " + l.getCodigo() + " | " + l.getTitulo() + " | "
                    + l.getAutor() + " | Quantidade: " + l.getQuantidade());
        }
    }

    private void listarDisponibilidade() {
        if (sistema.getTotalLivros() == 0) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (int i = 0; i < sistema.getTotalLivros(); i++) {
            Livro l = sistema.getLivro(i);
            String situacao = "Indisponível";
            if (l.getQuantidade() > 0) {
                situacao = "Disponível";
            }
            System.out.println("Código " + l.getCodigo() + " | " + l.getTitulo()
                    + " | Quantidade: " + l.getQuantidade() + " | " + situacao);
        }
    }

    private void listarClientes() {
        if (sistema.getTotalClientes() == 0) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (int i = 0; i < sistema.getTotalClientes(); i++) {
            Cliente c = sistema.getCliente(i);
            System.out.println("ID " + c.getId() + " | " + c.getNomeCompleto()
                    + " | usuário: " + c.getUsuario());
        }
    }

    //O gerente vê os funcionários e os clientes (as senhas nunca aparecem)
    private void listarUsuarios() {
        System.out.println("Funcionários:");
        System.out.println("  Gerente | usuário: gerente01");
        System.out.println("  Bibliotecário 1 | usuário: bibliotecario01");
        System.out.println("  Bibliotecário 2 | usuário: bibliotecario02");
        System.out.println();
        System.out.println("Clientes:");
        listarClientes();
    }

    //Lista empréstimos.
    //idCliente: 0 = de todos os clientes; outro número = somente daquele cliente
    //tipo: 0 = todos; 1 = somente em andamento; 2 = somente devolvidos
    private void listarEmprestimos(int idCliente, int tipo) {
        int encontrados = 0;

        for (int i = 0; i < sistema.getTotalEmprestimos(); i++) {
            Emprestimo e = sistema.getEmprestimo(i);
            boolean mostrar = true;

            if (idCliente != 0 && e.getIdCliente() != idCliente) {
                mostrar = false;
            }
            if (tipo == 1 && !e.getSituacao().equals("EM ANDAMENTO")) {
                mostrar = false;
            }
            if (tipo == 2 && e.getSituacao().equals("EM ANDAMENTO")) {
                mostrar = false;
            }

            if (mostrar) {
                encontrados++;
                String nomeCliente = sistema.buscarClientePorId(e.getIdCliente()).getNomeCompleto();
                String tituloLivro = sistema.buscarLivro(e.getCodigoLivro()).getTitulo();

                System.out.println("Empréstimo nº " + e.getId());
                System.out.println("  Cliente: " + nomeCliente);
                System.out.println("  Livro: " + tituloLivro + " (código " + e.getCodigoLivro() + ")");
                System.out.println("  Data do empréstimo: " + formatarData(e.getDataEmprestimo()));
                System.out.println("  Devolução prevista: " + formatarData(e.getDataPrevista()));
                if (e.getDataDevolucao() != null) {
                    System.out.println("  Data da devolução: " + formatarData(e.getDataDevolucao()));
                }
                System.out.println("  Situação: " + e.getSituacao());
                System.out.println("  ------------------------------");
            }
        }

        if (encontrados == 0) {
            System.out.println("Nenhum empréstimo encontrado.");
        }
    }

    // ------------------------- ENTRADA E TELA -------------------------

    //Lê um número inteiro e repete a pergunta se o usuário digitar outra coisa
    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!sc.hasNextInt()) {
            sc.next(); //descarta o que foi digitado
            System.out.print("Entrada inválida! Digite um número inteiro: ");
        }
        int numero = sc.nextInt();
        sc.nextLine(); //limpa o ENTER que sobrou
        return numero;
    }

    //Lê um texto e repete a pergunta se ficar vazio
    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        String texto = sc.nextLine().trim();
        while (texto.equals("")) {
            System.out.print("O campo não pode ficar vazio. " + mensagem);
            texto = sc.nextLine().trim();
        }
        return texto;
    }

    private void exibirTitulo(String texto) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("  " + texto);
        System.out.println("==================================================");
    }

    //Mostra a data no formato dia/mês/ano
    private String formatarData(LocalDate data) {
        return data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
    }
}

//------------------------- MAIN (inicializando o sistema) -------------------------

public class Main {
    public static void main(String[] args) {
        //SINGLETON: não usamos "new"; pedimos a única instância do sistema
        SistemaBiblioteca sistema = SistemaBiblioteca.getInstancia();
        Menus menus = new Menus(sistema);
        menus.iniciar();
    }
}
