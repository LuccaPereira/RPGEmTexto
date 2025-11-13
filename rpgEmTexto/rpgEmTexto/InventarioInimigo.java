package rpgEmTexto;

public class InventarioInimigo extends Inventario {
    @Override
    public void soltarLootPara(Personagem inimigo) {
        System.out.println("....." + inimigo.getNome() + " está pegando os itens!");
        for (Item itemOriginal : this.inventario) {
            Item copia = itemOriginal.clone();
            inimigo.receberItem(copia);
        }
    }
}