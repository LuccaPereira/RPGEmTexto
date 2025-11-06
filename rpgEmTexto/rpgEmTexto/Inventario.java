package rpgEmTexto;
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

    public void removerItem(Item itemUsado){
        boolean itemFoiEncontrado = false;
        for(Item itens : inventario){
            Item itemInventario = itens;
            String itemNome = itemUsado.getNome();
            if(itemNome.equals(itemInventario.getNome())){
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
}
