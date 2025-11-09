package rpgEmTexto;
import java.util.Random;

public class Jogo {

    private Personagem jogador;
    private InputHelper input;
    private Random dado;
    private Battle gerenciadorDeBatalha;
    private boolean estaRodando;
    
    private int progressoHistoria = 0;

    public void iniciarJogo() {
        this.input = new InputHelper();
        this.dado = new Random();
        this.estaRodando = true;
        
        this.gerenciadorDeBatalha = new Battle(this.input, this.dado);

        System.out.println("=========================================");
        System.out.println("  BEM-VINDO ÀS CRÔNICAS ESQUECIDAS");
        System.out.println("=========================================");
        System.out.println("Você acorda com o som de aço e gritos. Sua cabeça dói.");
        System.out.println("O quartel onde você treina está em chamas.");

        selecionarClasse(); 

        loopPrincipal();
    }

    private void selecionarClasse() {
        System.out.println("\nQuem é você?");
        System.out.println("1. Um(a) Guerreiro(a), forte e resistente.");
        System.out.println("2. Um(a) Arqueiro(a), ágil e preciso(a).");
        System.out.println("3. Um(a) Mago(a), focado(a) em poder arcano.");
        
        int escolhaClasse = input.obterEscolhaNumerica(3);
        System.out.print("Qual é o seu nome? ");
        String nome = input.obterTexto();

        Inventario inventarioJogador = new InventarioPersonagem();
        switch (escolhaClasse) {
            case 1:
                this.jogador = new Guerreiro(nome, 120, 10, 8, (short)1, inventarioJogador);
                break;
            case 2:
                this.jogador = new Arqueiro(nome, 100, 12, 4, (short)1, inventarioJogador);
                break;
            case 3:
                this.jogador = new Mago(nome, 80, 5, 3, (short)1, inventarioJogador, 100, 15);
                break;
        }

        System.out.println("\nCerto, " + this.jogador.getNome() + ". Você precisa sobreviver.");
        
        Item pocaoInicial = new Item("Poção", "Uma poção vermelha simples.", "Cura 20 PV", 3);
        this.jogador.receberItem(pocaoInicial);
    }

    public void loopPrincipal() {
        while (this.estaRodando) {
            
            if (this.jogador.getPontosVida() <= 0) {
                System.out.println("\n================ GAME OVER ================");
                System.out.println("Seu corpo cai no chão. A escuridão toma conta.");
                this.estaRodando = false;
                break; 
            }

            exibirMenuPrincipal();
            int escolha = input.obterEscolhaNumerica(3);

            switch (escolha) {
                case 1:
                    explorar();
                    break;
                case 2:
                    abrirInventario();
                    break;
                case 3:
                    System.out.println("Você senta e descansa por um momento, recuperando o fôlego.");
                    break;
                case 0:
                    System.out.println("Você saiu do jogo. Até logo!");
                    this.estaRodando = false;
                    break;
            }
        }
        input.fechar();
    }
    
    private void exibirMenuPrincipal() {
        System.out.println("\n--- O que você faz? ---");
        System.out.println("PV Atuais: " + this.jogador.getPontosVida());
        System.out.println("1. Continuar explorando");
        System.out.println("2. Abrir Inventário");
        System.out.println("3. Descansar");
        System.out.println("0. Sair do Jogo");
    }

    private void explorar() {
        switch (this.progressoHistoria) {
            case 0:
                capituloUm_A_Armaria();
                break;
            case 1:
                capituloDois_O_Corredor();
                break;
            case 2:
                capituloTres_O_SalaoPrincipal();
                break;
            case 3:
                capituloQuatro_O_Portao();
                break;
            default:
                System.out.println("\nA cidade queima ao seu redor... (Fim do Jogo)");
                this.estaRodando = false;
        }
    }

    private void capituloUm_A_Armaria() {
        System.out.println("\n--- Capítulo 1: A Armaria em Chamas ---");
        System.out.println("Você está na armaria do quartel. O fogo lambe as paredes.");
        System.out.println("Você precisa de uma arma. Você vê duas opções:");
        System.out.println("1. No suporte à sua frente, um kit de treino: [Espada] e [Escudo].");
        System.out.println("2. No canto, encostado na parede, um [Arco] longo de recruta.");
        System.out.println("3. Ignorar as armas e procurar sobreviventes.");
        
        int escolha = input.obterEscolhaNumerica(3);

        if (escolha == 1) {
            System.out.println("Você pega a Espada e amarra o Escudo em seu braço.");
            this.jogador.receberItem(new Item("Espada", "Espada de treino, sem fio.", "Arma", 1));
            this.jogador.receberItem(new Item("Escudo", "Escudo de madeira básico.", "Defesa", 1));
        } else if (escolha == 2) {
            System.out.println("Você pega o Arco e uma aljava com algumas flechas.");
            this.jogador.receberItem(new Item("Arco", "Arco longo de recruta.", "Arma", 1));
        } else {
            System.out.println("Você decide não pegar armas. Corajoso, ou tolo.");
        }
        
        this.progressoHistoria++;
    }

    private void capituloDois_O_Corredor() {
        System.out.println("\n--- Capítulo 2: O Corredor ---");
        System.out.println("Você sai da armaria e entra em um corredor tomado pela fumaça.");
        System.out.println("No final do corredor, um [Goblin Patrulheiro] vasculha um corpo.");
        System.out.println("1. Atacar o Goblin pelas costas!");
        System.out.println("2. Tentar passar em silêncio pela outra porta.");

        int escolha = input.obterEscolhaNumerica(2);

        if (escolha == 1) {
            System.out.println("Você corre e ataca o Goblin de surpresa!");
            Inimigo goblin = new Inimigo("Goblin Patrulheiro", 30, 8, 3, (short)1, new InventarioInimigo());
            iniciarBatalha(goblin);
        } else {
            System.out.println("Você tenta passar em silêncio...");
            int rolagem = dado.nextInt(20) + 1;
            if (rolagem > 10) {
                System.out.println("Você consegue passar sem ser notado!");
            } else {
                System.out.println("Você pisa em um pedaço de vidro! O Goblin ouve e ataca!");
                Inimigo goblin = new Inimigo("Goblin Patrulheiro", 30, 8, 3, (short)1, new InventarioInimigo());
                iniciarBatalha(goblin);
            }
        }
        
        if (this.jogador.getPontosVida() > 0) {
            this.progressoHistoria++;
        }
    }

    private void capituloTres_O_SalaoPrincipal() {
        System.out.println("\n--- Capítulo 3: O Salão Principal ---");
        System.out.println("Você chega ao salão principal. Está destruído, mas silencioso.");
        System.out.println("A saída principal está logo à frente, bloqueada por destroços em chamas.");
        System.out.println("Algo chama sua atenção: perto da lareira, uma tábua do piso parece solta.");
        System.out.println("1. Investigar a tábua solta.");
        System.out.println("2. Ignorar e ir para a saída.");

        int escolha = input.obterEscolhaNumerica(2);

        if (escolha == 1) {
            System.out.println("Você força a tábua e encontra um compartimento secreto!");
            System.out.println("Dentro, envolto em panos, está um [Staff Antigo] que pulsa com uma leve energia.");
            this.jogador.receberItem(new Item("Staff Antigo", "Um cajado de carvalho nodoso.", "Arma (Mago)", 1));
        } else {
            System.out.println("Você ignora a tábua. Não há tempo para isso.");
        }
        
        System.out.println("Você se aproxima da saída. O caminho está quase livre...");
        this.progressoHistoria++;
    }

    private void capituloQuatro_O_Portao() {
        System.out.println("\n--- Capítulo 4: O Portão ---");
        System.out.println("Quando você está prestes a sair, um barulho de armadura chama sua atenção.");
        System.out.println("Um [Chefe Goblin], maior e mais forte, bloqueia seu caminho.");
        System.out.println("'Você não vai a lugar nenhum, humano!' ele rosna.");
        
        Inventario inventarioChefe = new InventarioInimigo();
        inventarioChefe.addItem(new Item("Poção de Mana", "Uma poção azul borbulhante.", "Recupera 10 Mana", 1));

        Inimigo chefeGoblin = new Inimigo("Chefe Goblin Armado", 60, 12, 6, (short)3, inventarioChefe);
        
        iniciarBatalha(chefeGoblin);

        if (this.jogador.getPontosVida() > 0) {
            System.out.println("\nCom o Chefe Goblin derrotado, você corre para a noite.");
            System.out.println("A cidade queima ao seu redor, mas você está vivo.");
            System.out.println("Esta foi apenas a primeira batalha de muitas.");
            System.out.println("\n...FIM...");
            this.estaRodando = false;
        }
    }


    private void abrirInventario() {
        this.jogador.getInventario().listarInventario();
        
        System.out.println("\nQual item você quer usar? (Digite o nome exato, ou 'sair')");
        String escolhaItem = input.obterTexto();

        if (escolhaItem.equalsIgnoreCase("sair")) {
            return;
        }

        this.jogador.usarItem(escolhaItem);
    }

    private void iniciarBatalha(Inimigo inimigo) {
        System.out.println("\n!!! UM " + inimigo.getNome() + " APARECE !!!");
        
        this.gerenciadorDeBatalha.iniciar(this.jogador, inimigo);
    }
}