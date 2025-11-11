package rpgEmTexto;

import java.util.Random; 

public class Battle {
    private InputHelper input;
    private Random dado;

    public Battle(InputHelper input, Random dado) {
        this.input = input;
        this.dado = dado;
    }

    public void iniciar(Personagem jogador, Personagem inimigo) {

        System.out.println("\n--- BATALHA INICIADA: " + jogador.getNome() + " vs. " + inimigo.getNome() + " ---");

        while (ambosEstaoVivos(jogador, inimigo)) {

            exibirMenuBatalha(jogador, inimigo);
            
            int escolha = input.obterEscolhaNumerica(3);
            boolean jogadorFugiu = false;

            switch (escolha) {
                case 1:
                    if (jogador instanceof Mago) {
                        
                        ((Mago) jogador).lancarBolaDeFogo(inimigo);
                    
                    } else if (jogador instanceof Guerreiro) {
                        ((Guerreiro) jogador).AttackDamage(inimigo);
                    
                    } else if(jogador instanceof Arqueiro){
                        ((Arqueiro) jogador).tiroPreciso(inimigo);
                    }
                    
                    else {
                        System.out.println(jogador.getNome() + " usa um ataque básico!");
                        executarTurnoAtaque(jogador, inimigo);
                    }
                    break; 

                case 2:
                    System.out.println("Qual item você quer usar? (Digite o nome exato)");
                    String nomeDoItem = this.input.obterTexto();
                    jogador.usarItem(nomeDoItem);
                    break; 

                case 3:
                    jogadorFugiu = tentarFugir(jogador);
                    break;
            }

            if (jogadorFugiu) {
                break; 
            }
            if (inimigo.getPontosVida() <= 0) {
                System.out.println("O " + inimigo.getNome() + " foi derrotado!");
                break;
            }

            System.out.println("\n--- Turno do Inimigo ---");
            executarTurnoAtaque(inimigo, jogador);

            if (jogador.getPontosVida() <= 0) {
                System.out.println("Você foi derrotado!");
                break;
            }
        }
        
        System.out.println("--- Batalha Terminada ---");
    }

    private void executarTurnoAtaque(Personagem atacante, Personagem defensor) {
        System.out.println("---------- Turno de " + atacante.getNome() + " ----------");
        
        int rolagem = this.dado.nextInt(10) + 1; 
        System.out.println(atacante.getNome() + " rolou o dado: " + rolagem);

        int ataqueEfetivo = calcularAtaqueEfetivo(atacante, rolagem);
        System.out.println("Poder de ataque total: " + ataqueEfetivo);

        int dano = calcularDano(ataqueEfetivo, defensor.getDefesa());
        
        defensor.tomarDano(dano, atacante); 

        System.out.println(defensor.getNome() + " recebeu " + dano + " de dano.");
        System.out.println("Vida restante de " + defensor.getNome() + ": " + defensor.getPontosVida());
        System.out.println("--------------------------------------\n");
    }

    private boolean tentarFugir(Personagem jogador) {
        System.out.println(jogador.getNome() + " tenta fugir!");
        int rolagem = this.dado.nextInt(100); 
        
        if (rolagem >= 50) {
            System.out.println("Você conseguiu escapar!");
            return true;
        } else {
            System.out.println("A fuga falhou! O inimigo ataca!");
            return false;
        }
    }

    private void exibirMenuBatalha(Personagem p1, Personagem p2) {
        System.out.println("\n--- Turno do Jogador ---");
        System.out.println("Jogador PV: " + p1.getPontosVida());
        System.out.println("Inimigo PV: " + p2.getPontosVida());
        System.out.println("1. Atacar");
        System.out.println("2. Usar Item");
        System.out.println("3. Tentar Fugir");
    }

    private int calcularAtaqueEfetivo(Personagem atacante, int rolagem) {
        return atacante.getAtaque() + rolagem;
    }

    private int calcularDano(int ataque, int defesa) {
        return Math.max(0, ataque - defesa);
    }

    private boolean ambosEstaoVivos(Personagem p1, Personagem p2) {
        return p1.getPontosVida() > 0 && p2.getPontosVida() > 0;
    }
}