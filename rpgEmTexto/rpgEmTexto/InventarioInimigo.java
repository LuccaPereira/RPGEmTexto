package rpgEmTexto;

public class InventarioInimigo extends Inventario {
    public void soltarLootPara(Personagem jogadorDestino) {
        
        System.out.println("....." + jogadorDestino.getNome() + " está pegando os itens!");
        for (Item itemOriginal : this.inventario) {
            Item copia = itemOriginal.clone();
            jogadorDestino.receberItem(copia);
        }
    }
}