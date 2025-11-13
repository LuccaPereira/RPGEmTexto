package rpgEmTexto;

public class InventarioPersonagem extends Inventario {
@Override
    public void soltarLootPara(Personagem atacante) {
        System.out.println("Você foi derrotado!");
        System.out.println("Seus itens caem no chão e se perdem...");

        this.inventario.clear(); 
    }
}