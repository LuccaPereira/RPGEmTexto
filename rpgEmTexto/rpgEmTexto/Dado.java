package rpgEmTexto;
public class Dado {
    
    private static int[] dado = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


    public Dado(int[] dado) {
        Dado.dado = dado;
    }

    public static int roll() {
        int idx = (int) (Math.random() * dado.length);
        return dado[idx];
    }

}
