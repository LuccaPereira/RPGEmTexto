package rpgEmTexto;

import java.util.Scanner;

public class InputHelper {

    private Scanner scanner;

    public InputHelper() {
        this.scanner = new Scanner(System.in);
    }

    public int obterEscolhaNumerica(int maxOpcao) {
        int escolha = -1;
        while (escolha < 0 || escolha > maxOpcao) {
            try {
                System.out.print("Sua escolha: ");
                escolha = Integer.parseInt(scanner.nextLine()); 
                if (escolha < 0 || escolha > maxOpcao) {
                    System.out.println("Escolha inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número.");
            }
        }
        return escolha;
    }

    public String obterTexto() {
        return scanner.nextLine();
    }

    public void fechar() {
        scanner.close();
    }
}