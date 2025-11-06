package rpgEmTexto;

public class Personagem {
    private String nome;
    private int pontosVida;
    private int ataque;
    private int defesa;
    private short nivel;
    Inventario inventario;


    public Personagem(String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario){
        this.nome = nome;
        this.pontosVida =  pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = inventario;
    }

    public String getNome(){
        return nome;
    }

    public int getPontosVida(){
        return pontosVida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public short getNivel() {
        return nivel;
    }

    
    //Setters
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPontosVida(int pontosVida) {

        if(pontosVida <= 0){
            System.out.println("Você morreu");
            this.pontosVida = pontosVida;
        }

        this.pontosVida = pontosVida;
    }

    public void receberItem(){
        //fazer ainda
    }

    public int tomarDano(int dano, Personagem atacante){
        return atacante.getPontosVida() - dano;
    }

}


