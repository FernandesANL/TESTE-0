import java.util.Random; import java.util.Scanner;

public class BatalhaNaval { private static final int TAMANHO = 5; private static final char AGUA = '~'; private static final char NAVIO = 'N'; private static final char ACERTO = 'X'; private static final char ERRO = 'O';

    private char[][] tabuleiro = new char[TAMANHO][TAMANHO];
    private int naviosRestantes = 3;

    public BatalhaNaval() {
        inicializarTabuleiro();
        posicionarNavios();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                tabuleiro[i][j] = AGUA;
            }
        }
    }

    private void posicionarNavios() {
        Random aleatorio = new Random();
        int colocados = 0;
        while (colocados < naviosRestantes) {
            int x = aleatorio.nextInt(TAMANHO);
            int y = aleatorio.nextInt(TAMANHO);
            if (tabuleiro[x][y] == AGUA) {
                tabuleiro[x][y] = NAVIO;
                colocados++;
            }
        }
    }

    public void exibirTabuleiro(boolean mostrarNavios) {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (tabuleiro[i][j] == NAVIO && !mostrarNavios) {
                    System.out.print(AGUA + " ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean atacar(int x, int y) {
        if (tabuleiro[x][y] == NAVIO) {
            tabuleiro[x][y] = ACERTO;
            naviosRestantes--;
            System.out.println("Acertou um navio!");
            return true;
        } else if (tabuleiro[x][y] == AGUA) {
            tabuleiro[x][y] = ERRO;
            System.out.println("Errou!");
        } else {
            System.out.println("Já tentou essa posição antes.");
        }
        return false;
    }

    public boolean fimDeJogo() {
        return naviosRestantes == 0;
    }

    public static void main(String[] args) {
        Scanner escaner = new Scanner(System.in);
        teste jogo = new teste();

        System.out.println("Bem-vindo ao Batalha Naval!");

        while (!jogo.fimDeJogo()) {
            jogo.exibirTabuleiro(false);
            System.out.print("Digite linha e coluna (0 a " + (TAMANHO - 1) + "): ");
            int x = escaner.nextInt();
            int y = escaner.nextInt();

            if (x >= 0 && x < TAMANHO && y >= 0 && y < TAMANHO) {
                jogo.atacar(x, y);
            } else {
                System.out.println("Coordenadas inválidas. Tente novamente.");
            }
        }

        System.out.println("Parabéns! Você venceu!");
        jogo.exibirTabuleiro(true);
        escaner.close();
    }

}
