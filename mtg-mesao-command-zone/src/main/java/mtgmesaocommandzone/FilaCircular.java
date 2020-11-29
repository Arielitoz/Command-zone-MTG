package mtgmesaocommandzone;

public class FilaCircular {
    int tamanho, inicio, fim;		// inicio - índice do primeiro da fila
                                    // fim - (índice do último)+1 da fila
    String[] fila;

    // Construtor - recebe a capacidade da fila (tamanho total do vetor)
    public FilaCircular(int capacidade) {
        fila = new String[capacidade];      // cria o vetor para armazenar a fila
        tamanho = 0;                        // inicializa tamam
        inicio = 0;
        fim = 0;
    }

    // Método isEmpty() - retorna true se a fila está vazia e false caso contrário
    public boolean isEmpty() {
        return tamanho == 0;
    }

    // Método isFull() - retorna true se a fila está cheia e false caso contrário
    public boolean isFull() {
        return tamanho == fila.length;
    }

    // Método insert - recebe info a ser inserida na fila
    public void insert(String info) {
        if(!isFull()) {				// Se a fila não está cheia
            fila[fim] = info;		// então insere info na fila, índice fim
            fim++;					// incrementa fim
            fim = fim % fila.length;	// acerta fim, para que volte a ser zero, caso
                                        // fim seja igual a fila.length
            tamanho++;				// incrementa tamanho
        }else {
            System.out.println("Fila cheia!");	// senão exibe uma mensagem de fila cheia
        }
    }

    // Método peek() - retorna o primeiro da fila, sem remover
    public String peek() {
        return fila[inicio];
    }

    // Método poll() - retorna o primeiro da fila, removendo-o
    public String poll() {
        String primeiro = peek();   // salva o primeiro numa variável

        if (!isEmpty()) {		// Se a fila não está vazia
            fila[inicio] = null;            // "anula" o valor removido
            inicio++;						// incrementa inicio
            inicio = inicio % fila.length;	// ajusta inicio para que vire zero se for = fila.length
            tamanho--;						// decrementa tamanho
        }
        // Retorna o primeiro
        return primeiro;
    }

    // Método exibe() - exibe os elementos da fila
    public void exibe() {
        if(isEmpty()) {
            System.out.println("Fila vazia!");
        }
        else {
            for(int i = inicio, cont = 0; cont < tamanho ; i = (i+1) % fila.length, cont++) {
                System.out.println(fila[i]);
            }
        }
    }

}

