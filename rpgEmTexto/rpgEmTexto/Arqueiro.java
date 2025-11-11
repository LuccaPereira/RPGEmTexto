package rpgEmTexto;

public class Arqueiro extends Personagem {

    public Arqueiro (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario, int pontosVidaMaximo){
        super(nome, pontosVida, ataque, defesa, nivel, inventario, pontosVidaMaximo);
        
    }

    public void tiroPreciso(Personagem alvo) {
        int danoBase = 10;

        int flechaDePrecisao = this.getAtaque() + danoBase;
        alvo.tomarDano(flechaDePrecisao, this);

        System.out.println(this.getNome() + " dispara um Tiro Preciso!");
    }
}