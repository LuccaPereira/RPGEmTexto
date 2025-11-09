package rpgEmTexto;

public class InventarioPersonagem extends Inventario {
    public void soltarLootPara(Personagem personagem) {
        
        System.out.println("....." + personagem.getNome() + " está pegando os itens!");
        for (Item itemOriginal : this.inventario) {
            Item copia = itemOriginal.clone();
            personagem.receberItem(copia);
        }
    }
}