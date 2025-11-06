package rpgEmTexto;

public class Mago extends Personagem {
    private int mana;

    public Mago (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario, int mana){
        super(nome, pontosVida, ataque, defesa, nivel, inventario);
        //adicionar mana
        this.mana = mana;
        
    }

    public void lancarBolaDeFogo(Personagem alvo) {
        int custoMana = 5; 
        int danoBase = 10;

        if (this.getMana() >= custoMana) {
            
            int novaMana = this.getMana() - custoMana;
            this.setMana(novaMana);

            int danoDeFogo = this.getAtaque() + danoBase; 
            System.out.println(this.getNome() + " lança uma BOLA DE FOGO!");

            alvo.tomarDano(danoDeFogo, this);

        } else {
            System.out.println(this.getNome() + " tenta lançar o feitiço, mas não tem mana suficiente!");
        }

    }
    public int getMana(){
        return mana;
    }

    public void setMana(int mana){
        this.mana = mana;
    }

    public void restaurarMana(){
        this.setMana(100);
    }
}