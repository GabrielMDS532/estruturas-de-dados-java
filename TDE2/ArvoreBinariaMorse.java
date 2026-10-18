public class ArvoreBinariaMorse {
    No raiz;
    No letra;

    public No getRaiz() {
        return raiz;
    }

    public No getLetra() {
        return letra;
    }

    public No inicializar(){
        if(raiz == null){
            raiz = new No(' ');
            return raiz;
        }else{
            No atual;
            atual = raiz;
            return atual;
        }

    }

    public No inserir(String codigo, char letra){
        validarCodigo(codigo);
        No atual = inicializar();
        for(int i = 0; i < codigo.length(); i++){
            if(codigo.charAt(i) == '.'){
                if(atual.esquerda != null){
                    atual = atual.esquerda;
                }else{
                    No novoNo = new No(' ');
                    atual.esquerda = novoNo;
                    atual = novoNo;
                }
            }else if(codigo.charAt(i) == '-'){
                if(atual.direita != null){
                    atual = atual.direita;
                }else{
                    No novoNo = new No(' ');
                    atual.direita = novoNo;
                    atual = novoNo;
                }
            }
        }
        atual.letra = letra;
        return atual;
    }

    public char buscar(String codigo){
        validarCodigo(codigo);
        No atual = inicializar();
        for(int i  = 0; i < codigo.length(); i++){
            if(atual == null){
                return ' ';
            }
            if(codigo.charAt(i) == '.') {
                atual = atual.esquerda;
            }else if(codigo.charAt(i) =='-'){
                atual = atual.direita;
            }
        }

        if(atual == null){
            return ' ';
        }

        return atual.getLetra();
    }

    public String traduzir(String fraseMorse){
        if(fraseMorse == null){
            throw new IllegalArgumentException("A mensagem não pode ser nula.");
        }
        String codigoLetra = "";
        String fraseFinal = "";

        for(int i =0; i < fraseMorse.length(); i++){
            if(fraseMorse.charAt(i) !=' '){
                codigoLetra = codigoLetra + fraseMorse.charAt(i);
            }else{
                if(codigoLetra.length() > 0){
                    char Letra = buscar(codigoLetra);
                    fraseFinal = fraseFinal + Letra;
                }else{
                    fraseFinal = fraseFinal + " ";
                }
                codigoLetra = "";
            }
        }
        if(codigoLetra.length() > 0){
            char Letra = buscar(codigoLetra);
            fraseFinal = fraseFinal + Letra;
        }

        return fraseFinal;
    }

    private void validarCodigo(String codigo){
        if(codigo == null || codigo.length() == 0){
            throw new IllegalArgumentException("Digite um código Morse não vazio.");
        }
        for(int i = 0; i < codigo.length(); i++){
            if(codigo.charAt(i) != '.' && codigo.charAt(i) != '-'){
                throw new IllegalArgumentException("Use somente ponto (.) e traço (-) em cada código.");
            }
        }
    }

    public void imprimir(No atual, String identacao){
        if(atual == null){
            return;
        }

        System.out.println(identacao + atual.getLetra());

        imprimir(atual.esquerda, identacao + "   . ");

        imprimir(atual.direita, identacao + "   - ");



    }


}
