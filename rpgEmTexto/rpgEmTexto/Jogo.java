package rpgEmTexto;
import java.util.Random;
import java.util.Scanner;

import rpgEmTexto.Classes.Inimigo;

public class Jogo {

    private Personagem jogador;
    private Scanner scanner; 
    private Random dado; 
    private boolean estaRodando;

    Iniciar();
    public void loopPrincipal() {
        while (this.estaRodando) {
            
            if (this.jogador.getPontosVida() <= 0) {
                System.out.println("GAME OVER. Você foi derrotado.");
                this.estaRodando = false;
                break; 
            }

            exibirMenuPrincipal();
            int escolha = obterEscolhaDoJogador(3); 

            switch (escolha) {
                case 1:
                    explorar(); 
                    break;
                case 2:
                    abrirInventario();
                    break;
                case 3:
                    System.out.println("Você decide descansar um pouco...");
                    break;
                case 0:
                    System.out.println("Você saiu do jogo. Até logo!");
                    this.estaRodando = false;
                    break;
            }
        }
        scanner.close();
    }
    
    private void exibirMenuPrincipal() {
        System.out.println("\n--- O que você faz? ---");
        System.out.println("PV Atuais: " + this.jogador.getPontosVida());
        System.out.println("1. Explorar os arredores");
        System.out.println("2. Abrir Inventário");
        System.out.println("3. Descansar (ainda não implementado)");
        System.out.println("0. Sair do Jogo");
    }

    private void explorar() {
        System.out.println("\nVocê caminha pela floresta escura...");
        System.out.println("Você vê uma bifurcação. À esquerda, uma caverna escura. À direita, um caminho estreito.");
        System.out.println("O que você faz?");
        System.out.println("1. Entrar na caverna");
        System.out.println("2. Seguir pelo caminho");
        
        int escolha = obterEscolhaDoJogador(2);

        if (escolha == 1) {
            System.out.println("Você entra na caverna e ouve um rosnado...");
            System.out.println("Um Goblin pula das sombras!");
            
            Inimigo goblin = new Inimigo("Goblin", 30); // 30 PV
            
            iniciarBatalha(goblin);

        } else if (escolha == 2) {
            System.out.println("Você segue pelo caminho e pisa em algo...");
            System.out.println("É UMA ARMADILHA!");
            int danoArmadilha = dado.nextInt(10) + 5; // Dano de 5 a 14
            System.out.println("Você tomou " + danoArmadilha + " de dano!");
            this.jogador.tomarDano(danoArmadilha, null);
        }
    }

    private void abrirInventario() {
        this.jogador.listarMeusItens();
        
        System.out.println("Qual item você quer usar? (Digite o nome exato, ou 'sair')");
        String escolhaItem = scanner.nextLine();

        if (escolhaItem.equalsIgnoreCase("sair")) {
            return;
        }

        if (escolhaItem.equalsIgnoreCase("Poção")) {
            Item pocao = new Item("Poção", 1);
            this.jogador.usarItem(pocao);
        } else {
            System.out.println("Você não pode usar este item agora.");
        }
    }

    private boolean tentarFugir() {
        System.out.println(this.jogador.getNome() + " tenta fugir!");
        int rolagem = dado.nextInt(100); 
        if (rolagem >= 50) { 
            System.out.println("Você conseguiu escapar!");
            return true;
        } else {
            System.out.println("A fuga falhou!");
            return false;
        }
    }

    private void iniciarBatalha(Inimigo inimigo) {
        System.out.println("\n--- BATALHA INICIADA: " + jogador.getNome() + " vs. " + inimigo.getNome() + " ---");
        
        while (jogador.getPontosVida() > 0 && inimigo.getPontosVida() > 0) {
            
            System.out.println("\n--- Turno do Jogador ---");
            System.out.println("Jogador PV: " + jogador.getPontosVida());
            System.out.println("Inimigo PV: " + inimigo.getPontosVida());
            System.out.println("1. Atacar");
            System.out.println("2. Usar Item (Não implementado na batalha)");
            System.out.println("3. Tentar Fugir");

            int escolhaBatalha = obterEscolhaDoJogador(3);
            boolean jogadorFugiu = false;

            switch (escolhaBatalha) {
                case 1:
                    int danoJogador = dado.nextInt(10) + 5;
                    inimigo.tomarDano(danoJogador, jogador);
                    break;
                case 2:
                    System.out.println("Inventário de batalha ainda não implementado.");
                    break;
                case 3:
                    jogadorFugiu = tentarFugir();
                    break;
            }

            if (jogadorFugiu) {
                break; 
            }

            if (inimigo.getPontosVida() > 0) {
                System.out.println("--- Turno do Inimigo ---");
                int danoInimigo = dado.nextInt(8) + 3; // Dano de 3 a 10
                jogador.tomarDano(danoInimigo, inimigo);
            }
        }
        
        System.out.println("--- Batalha Terminada ---");
    }

    private int obterEscolhaDoJogador(int maxOpcao) {
        int escolha = -1;
        while (escolha < 0 || escolha > maxOpcao) {
            try {
                System.out.print("Sua escolha: ");
                escolha = Integer.parseInt(scanner.nextLine());
                if (escolha < 0 || escolha > maxOpcao) {
                    System.out.println("Escolha inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número.");
            }
        }
        return escolha;
    }
}