import java.util.Scanner;

public class Main {
    static Scanner entrada  = new Scanner(System.in);
    static public void main(String[] args){
    ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();

    boolean sair = false;

    while(!sair){
        System.out.println("Digte: A pra inserir" + "\n" + "Digite: B para buscar" + "\n" + "Digite: C para imprimir Arvore inteira" +
                "\n" + "Digite: D pra sair");
        String opcao = entrada.nextLine();
        switch (opcao.toLowerCase()){
            case "a":
                boolean ContinuarInserindo = true;
                while(ContinuarInserindo){

                    System.out.println("Digite o codigo: ");
                    String codigo = entrada.nextLine();
                    System.out.println("Digite o caractere: ");
                    String textoCaractere = entrada.nextLine();
                    if(textoCaractere.length() != 1 || textoCaractere.charAt(0) == ' '){
                        System.out.println("Digite exatamente um caractere, diferente de espaço.");
                        continue;
                    }
                    char caractere = textoCaractere.charAt(0);
                    try{
                        arvore.inserir(codigo,caractere);
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.println("Deseja inserir outro codigo? (s/n)");
                    String resposta = entrada.nextLine();
                    if(resposta.equalsIgnoreCase("n")){
                        ContinuarInserindo = false;
                    }


                }
                break;

            case "b":
                System.out.println("Digite o código do caractere: ");
                String codigo = entrada.nextLine();
                try{
                    String Resultado = arvore.traduzir(codigo);
                    System.out.println("Resultado: " + Resultado);
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                break;

            case "c":
                System.out.println("\n--- ÁRVORE MORSE HIERÁRQUICA ---");
                arvore.imprimir(arvore.getRaiz(),"");
                System.out.println("--------------------------------\n");
                break;

            case "d":
                sair = true;
                System.out.println("Saindo....");
                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }


    }
}
