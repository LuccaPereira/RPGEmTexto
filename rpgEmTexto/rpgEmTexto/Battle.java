package rpgEmTexto;

public class Battle {

    public Battle(Personagem inimigo, Personagem personagem){
        boolean dadoRolado = false;
        
        if(personagem.getPontosVida() > 0){
           
            System.out.println("---------- Rolagem dos dados ----------");
            int rolagemDados = Dado.roll();
            System.out.println("após rolagem o dados caiu no número: " + rolagemDados);
            int ataqueAtual = personagem.getAtaque() + rolagemDados;
            System.out.println("Poder de luta foi foi aumentado para " + ataqueAtual);
            personagem.setAtaque(ataqueAtual);
            System.out.println("---------- Rodada de Rolagem Finalizada ----------");

            System.out.println("---------- Rodada de Ataque Iniciada ----------");
            int ataquePersonagem = personagem.getAtaque() - inimigo.getDefesa();
            int danoVida = ataquePersonagem - inimigo.getPontosVida();
            System.out.println("Inimigo foi atacado, sua vida restante é: " + danoVida);
            inimigo.setPontosVida(danoVida);
            System.out.println("---------- Rodada de Ataque Finalizada ----------");

            dadoRolado = true;
        } else {
            if(personagem.getPontosVida() < 0){
                System.out.println("Você Morreu! Pontos de vida: " + personagem.getPontosVida());
            }
        }
           
        if(dadoRolado = true){
            dadoRolado = false;

            if(inimigo.getPontosVida() > 0){
                System.out.println("---------- Rolagem dos dados ----------");
                int rolagemDados = Dado.roll();
                System.out.println("após rolagem o dados caiu no número: " + rolagemDados);
                int ataqueAtual = inimigo.getAtaque() + rolagemDados;
                System.out.println("Poder de luta foi foi aumentado para " + ataqueAtual);
                inimigo.setAtaque(ataqueAtual);
                System.out.println("---------- Rodada de Rolagem Finalizada ----------");

                System.out.println("---------- Rodada de Ataque Iniciada ----------");
                int ataqueInimigo = inimigo.getAtaque() - personagem.getDefesa();
                int danoVida = ataqueInimigo - personagem.getPontosVida();
                System.out.println("Inimigo foi atacado, sua vida restante é: " + danoVida);
                personagem.setPontosVida(danoVida);
                System.out.println("---------- Rodada de Ataque Finalizada ----------");

                dadoRolado = true;
            } else {
                if(personagem.getPontosVida() < 0){
                    System.out.println("Você Morreu! Pontos de vida: " + personagem.getPontosVida());
                }
            }
        } 

    }
}
