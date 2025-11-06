package rpgEmTexto;

public class Arqueiro extends Personagem {

    public Arqueiro (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario){
        super(nome, pontosVida, ataque, defesa, nivel, inventario);
        
        // (Pode ter lógicas extras, ex: 'this.flechas = 20;')
    }

    public void tiroPreciso(Personagem alvo) {
        //logica do ataque
        System.out.println(this.getNome() + " dispara um Tiro Preciso!");
    }
}