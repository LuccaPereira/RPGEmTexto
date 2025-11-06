package rpgEmTexto;

public class Inimigo extends Personagem {
    
    public Inimigo(String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario){
        super(nome, pontosVida, ataque, defesa, nivel, inventario);
    }

    // O Inimigo não precisa de 1 método padrão, que sera o seu ataque
    // pois a lógica de "tomarDano" (que chama o 'aoMorrer'
    // do inventário) já é herdada de 'Personagem'.
}