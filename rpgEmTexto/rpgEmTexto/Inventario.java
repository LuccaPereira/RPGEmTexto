package rpgEmTexto;
import java.text.Normalizer;
import java.util.ArrayList;

public abstract class Inventario implements Cloneable{
   protected ArrayList<Item> inventario = new ArrayList<>();

    public void addItem(Item novoItem){
        if(inventario != null){
            boolean itemFoiEncontrado = false;
            for(Item itens : inventario){
                Item antigoItem = itens;
                String itemNome = novoItem.getNome();
                if(itemNome.equals(antigoItem.getNome())){
                    antigoItem.setQuantidade(antigoItem.getQuantidade() + 1);
                    itemFoiEncontrado = true;
                    break;
                }
            }
            if(inventario.isEmpty() || itemFoiEncontrado == false){
                inventario.add(novoItem);
            }
            
        }
    }

    public void removerItem(String itemUsado){
        boolean itemFoiEncontrado = false;
        for(Item itens : inventario){
            Item itemInventario = itens;
            if(itemUsado.equals(itemInventario.getNome())){
                int quantidade = itemInventario.getQuantidade();
                if(quantidade > 0){
                    System.out.println("Item Utilizado");
                    quantidade = quantidade - 1;
                    if(quantidade == 0){
                        inventario.remove(itemInventario);
                        break;
                    }
                    itemInventario.setQuantidade(quantidade);         
                }  
                itemFoiEncontrado = true;
                break;
            }
        }
        if(inventario.isEmpty() || itemFoiEncontrado == false){
            System.out.println("Item não existe! Ou já foi retirado! ");
        }
    }

    public void listarInventario(){

       if (this.inventario == null || this.inventario.isEmpty()) {
            System.out.println("--- Inventário Vazio ---");
            return;
        }
        
        this.inventario.sort((a, b) -> 
            a.getNome().compareTo(b.getNome())
        );

        System.out.println("--- Inventário (Ordenado por Nome) ---");
        for (Item item : this.inventario) {
            System.out.println(item); 
        }
    }
    
    private static String removerAcentos(String s) {
        if (s == null) {
            return null;
        }
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);

        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
    }

    
    public Item encontrarItemPorNome(String nomeProcurado) {
        if (this.inventario == null) {
            System.out.println("x: Inventario Vazio!");
            return null;
        }

        boolean itemFoiEncontrado = false;

        String nomeProcuradoLimpo = removerAcentos(nomeProcurado);

        for (Item itemDaLista : inventario) {
            
            String nomeNaListaLimpo = removerAcentos(itemDaLista.getNome());

            if (nomeNaListaLimpo.equalsIgnoreCase(nomeProcuradoLimpo)) {
                
                itemFoiEncontrado = true;
                return itemDaLista; 
            }
        }

        if (!itemFoiEncontrado) { 
            System.out.println("Você não possui esse item no inventario...");
        }
        
        return null;
    }

    public abstract void soltarLootPara(Personagem atacante);
}
