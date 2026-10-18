public class No {
    char letra;
    No esquerda;
    No direita;

    public No(char letra){
        this.letra = letra;
        this.esquerda = null;
        this.direita = null;
    }

    public char getLetra() {
        return this.letra;
    }
}
