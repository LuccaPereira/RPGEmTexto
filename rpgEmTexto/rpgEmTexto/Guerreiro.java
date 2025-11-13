package rpgEmTexto;

public class Guerreiro extends Personagem {
    private int furia;

    public Guerreiro (String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario, int pontosVidaMaximo, int furia){
        super(nome, pontosVida, ataque, defesa, nivel, inventario, pontosVidaMaximo);
        
        this.furia = furia;
    }

    public Guerreiro(Guerreiro personagem){
        super(personagem);
        this.furia = personagem.getFuria();
    }

    public void AttackDamage(Personagem alvo) {
        if(this.getFuria() > 0){
           int ataqueFurioso =  this.getAtaque() + 5;
           alvo.tomarDano(ataqueFurioso, this);
           this.setFuria(this.getFuria() - 5);
        } else {

        }
        System.out.println(this.getNome() + " usa um Ataque Poderoso!");
    }

    public int getFuria(){
        return furia;
    }

    public void setFuria(int furia){
        this.furia = furia;
    }

    public void restuararFuria(){
        this.setFuria(100);
    }
}