package rpgEmTexto;

public class Classes {

    public class Mago extends Personagem {

        public Mago (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario){
            super(nome, pontosVida, ataque, defesa, nivel, inventario);
        }

    Personagem mago = new Mago(getNome(), getPontosVida(), getAtaque(), getDefesa(), getNivel(), getInventario());
}

    public class Guerreiro extends Personagem {

        public Guerreiro (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario){
            super(nome, pontosVida, ataque, defesa, nivel, inventario);
        }

        Personagem guerreiro = new Guerreiro(getNome(), getPontosVida(), getAtaque(), getDefesa(), getNivel(), getInventario());
    }

    public class Arqueiro extends Personagem {

        public Arqueiro (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario){
            super(nome, pontosVida, ataque, defesa, nivel, inventario);
        }

        Personagem arqueiro = new Arqueiro(getNome(), getPontosVida(), getAtaque(), getDefesa(), getNivel(), getInventario());
    }

    public class Inimigo extends Personagem {
        
        public Inimigo(String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario){
            super(nome, pontosVida, ataque, defesa, nivel, inventario);
        }

        Personagem inimigo = new Inimigo(getNome(), getPontosVida(), getAtaque(), getDefesa(), getNivel(), getInventario());
    }
}
