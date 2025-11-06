package rpgEmTexto;

public class Guerreiro extends Personagem {

    public Guerreiro (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario){
        super(nome, pontosVida, ataque, defesa, nivel, inventario);
        
        // (Pode ter lógicas extras, ex: 'this.furia = 50;')
    }

    public void AttackDamage(Personagem alvo) {
        System.out.println(this.getNome() + " usa um Ataque Poderoso!");
    }
}