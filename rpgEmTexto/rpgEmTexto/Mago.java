package rpgEmTexto;

public class Mago extends Personagem {
    private int mana;
    private int abilityPower;

    public Mago (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario, int mana, int abilityPower){
        super(nome, pontosVida, ataque, defesa, nivel, inventario);
        //adicionar mana
        this.mana = mana;
        this.abilityPower = abilityPower;
        
    }

    public void lancarBolaDeFogo(Personagem alvo) {
        InputHelper input = new InputHelper();
        int custoMana = 5; 
        int danoBase = 10;
        String pocao = "Poção";

        if (this.getMana() >= custoMana) {
            
            int novaMana = this.getMana() - custoMana;
            this.setMana(novaMana);

            int danoDeFogo = this.getAtaque() + danoBase; 
            System.out.println(this.getNome() + " lança uma BOLA DE FOGO!");

            alvo.tomarDano(danoDeFogo, this);

        } 
        else {
        
            System.out.println(this.getNome() + " tenta lançar o feitiço, mas não tem mana suficiente!");
        
        }
    }

    public int getMana(){
        return mana;
    }

    public int getAbilityPower(){
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower){
        this.abilityPower = abilityPower;
    }

    public void setMana(int mana){
        this.mana = mana;
    }

    public void restaurarMana(){
        this.setMana(100);
    }

}
