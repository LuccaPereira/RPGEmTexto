package rpgEmTexto;

public class Personagem {
    private String nome;
    private int pontosVida;
    private int pontosVidaMaximo;
    private int ataque;
    private int defesa;
    private short nivel;
    Inventario inventario;


    public Personagem(String nome, int pontosVida, int ataque, int defesa, short nivel, Inventario inventario, int pontosVidaMaximo){
        this.nome = nome;
        this.pontosVida =  pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = inventario;
        this.pontosVidaMaximo = pontosVidaMaximo;
    }

    public String getNome(){
        return nome;
    }

    public int getPontosVida(){
        return pontosVida;
    }

    public int getPontosVidaMax(){
        return pontosVidaMaximo;
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

    public void setPontosVida(int novaVida) {
        
        if (novaVida > this.pontosVidaMaximo) {
            
            this.pontosVida = this.pontosVidaMaximo;

        } else if (novaVida < 0) {
            
            this.pontosVida = 0;
            System.out.println("Você Faleceu!");

        } else {
            
            this.pontosVida = novaVida;
        }
    }
    
    public void receberItem(Item itemParaReceber) {
        if (this.inventario == null) {
            System.out.println("ERRO CRÍTICO: " + this.getNome() + " não tem um inventário!");
            return;
        }

        System.out.println("[+] " + this.getNome() + " recebeu: " + itemParaReceber.toString());

        this.inventario.addItem(itemParaReceber);
    }

    public boolean efeitoItem(String itemUsado){
        String usavel = itemUsado;
        Item itemParaUsar = this.inventario.encontrarItemPorNome(usavel);

        if (itemParaUsar == null) {
            System.out.println("Você tenta usar '" + usavel + "', mas não o possui!");
            return false;
        }

        boolean itemFoiConsumido = false; 

        switch (usavel.toLowerCase()) {
            
            case "pocao":
                int cura = 20; 
                this.setPontosVida(this.getPontosVida() + cura);
                System.out.println(this.getNome() + " usou uma Poção e curou " + cura + " PV!");
                itemFoiConsumido = true;
                break;

            case "pocao de mana":
                if (this instanceof Mago) {
                    Mago mago = (Mago) this;
                    int manaRecuperada = 10;
                    mago.setMana(mago.getMana() + manaRecuperada); 
                    System.out.println(this.getNome() + " usou uma Poção de Mana!");
                    itemFoiConsumido = true;
                } else {
                    System.out.println("Você tenta beber, mas nada acontece. (Não é um Mago)");
                }
                break;
            
            case "staff":
                if (this instanceof Mago) {
                    Mago mago = (Mago) this;
                    int danoStaff = 10;
                    mago.setAbilityPower(danoStaff + mago.getAtaque());
                    System.out.println(this.getNome() + " está usando o Staff");
                    itemFoiConsumido = true;
                } else {
                    System.out.println("Você tenta usar, mas nada acontece. (Não é um Mago)");
                }
                break;

            case "espada curta":
                int ataqueEspada = 15;
                this.setAtaque(this.getAtaque() + ataqueEspada);
                System.out.println(this.getNome() + " equipou a Espada Curta ganhou " + ataqueEspada + " AD (Attack Damage)!");
                break;
            
            case "escudo":
                int defesaEscudo = 15;
                this.setDefesa(this.getDefesa() + defesaEscudo);
                System.out.println(this.getNome() + " equipou a Escudo de Madeira ganhou " + defesaEscudo + " AD (Attack Damage)!");
                break;
            
           
            case "Arco":
                if(this instanceof Arqueiro){
                    Arqueiro arqueiro = (Arqueiro) this;
                    int arcoDamage = 11;
                    arqueiro.setAtaque(arqueiro.getAtaque() + arcoDamage);  
                    System.out.println(this.getNome() + " equipou o Arco ganhou " + arcoDamage + " AD (Attack Damage)!");
                    break;
                } else {
                    System.out.println("Você tenta equipar, mas nada acontece. (Não é um Arqueiro)");
                }
            
                 

            default:
                System.out.println("Item '" + usavel + "' não é utilizável.");
                break;
        }

        if (itemFoiConsumido) {
            String itemParaUsarNome = itemParaUsar.getNome();
            inventario.removerItem(itemParaUsarNome);
        }
        return itemFoiConsumido;
    }

    public void tomarDano(int dano, Personagem atacante){
        this.setPontosVida(this.getPontosVida() - dano);

        if(this.getPontosVida() <= 0){
            this.inventario.soltarLootPara(atacante);
        }
    }
    public void usarItem(String nomeDoItem) {     
        if (this.inventario == null) {
            System.out.println("Inventario vazio!");
            return;
        }
        Item itemParaUsar = this.inventario.encontrarItemPorNome(nomeDoItem);

        if (itemParaUsar != null) {    
            boolean foiConsumido = this.efeitoItem(nomeDoItem);

        } else {
            System.out.println("Você tenta usar '" + nomeDoItem + "', mas não o possui!");
        }
    }

    @Override
    public String toString() {
        return this.nome + " [PV: " + this.pontosVida + "] [Atk: " + this.ataque + " / Def: " + this.defesa + "]";
    }

}


