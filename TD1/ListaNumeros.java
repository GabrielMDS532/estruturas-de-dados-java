import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Arrays;

class Lista{
    private int[] valores;
    private int quantidade;
    private int inicio;

    public Lista(int capacidade){
        if(capacidade < 0){
            throw new InputMismatchException("A capacidade não pode ser negativa!");
        }else{

            this.valores = new int[capacidade];
            Arrays.fill(valores, -1);
        }
        this.quantidade = 0;
        this.inicio = 0;
    }


    public boolean cheia(){
        return quantidade == valores.length;
    }
    public boolean vazia(){
        return quantidade == 0;
    }

    public void adicionar(int valor){

        if(cheia()){
            throw new IllegalStateException("Array Cheio!");
        }else{
            if(vazia()){

                valores[(inicio + quantidade) % valores.length] = valor;
                quantidade += 1;
            }else{

                if(valor < consultaUltimo()){
                    System.out.println("rejeitado");
                }else{
                    valores[(inicio + quantidade) % valores.length] = valor;
                    quantidade+=1;
                }

            }
        }



    }

    public int remover(){


        int valorRemovido = 0;
        if(vazia()){
            throw new NoSuchElementException("Array vaziooo");
        }else{
            int PrimeiroValor = valores[inicio];
            inicio = (inicio + 1) % valores.length;
            valorRemovido = PrimeiroValor;
            quantidade--;
        }

        

        return valorRemovido;
    }
    
    public int consultaUltimo(){
        if(vazia()){
            throw new NoSuchElementException("Array Vazio");
        }else{
            return valores[(inicio + quantidade - 1) % valores.length];
        }
    }

    public int consultaPrimeiro(){
        if(vazia()){
            throw new NoSuchElementException("Array sem valores!");
        }else{
            return valores[inicio];
        }
    }



    public Lista merge(Lista filaAtual){

        int capacidadeC = this.quantidade + filaAtual.quantidade;

        Lista filaC = new Lista(capacidadeC);

        while(!this.vazia() && !filaAtual.vazia()){
            if(this.consultaPrimeiro() < filaAtual.consultaPrimeiro()){
                int valorRemovidoA = this.remover();
                filaC.adicionar(valorRemovidoA);
            }else{
                int valorRemovidoB = filaAtual.remover();
                filaC.adicionar(valorRemovidoB);
            }
        }

        while (!this.vazia()){
            int valorARemovido = this.remover();
            filaC.adicionar(valorARemovido);
        }
        while(!filaAtual.vazia()){
            int valorBRemovido = filaAtual.remover();
            filaC.adicionar(valorBRemovido);
        }

        return filaC;

    }



    public void imprimir(){
        int[] Dados = new int[quantidade];

        for(int i = 0; i < quantidade;i++){
              Dados[i] = valores[(inicio + i) % valores.length];
        }
        System.out.println("Array: " + Arrays.toString(Dados));
    }


}


public class ListaNumeros {
    private static int lerCapacidade(Scanner entrada){
        while(true){
            try{
                int capacidade = entrada.nextInt();
                if(capacidade >= 0){
                    return capacidade;
                }
                System.out.println("A capacidade não pode ser negativa. Digite novamente:");
            }catch(InputMismatchException e){
                System.out.println("Digite uma capacidade inteira válida:");
                entrada.nextLine();
            }
        }
    }

    public static void main(String[] args){

    Scanner entrada = new Scanner(System.in);
    boolean sair = false;

    System.out.println("Digite a capacidade do Array A: ");
    int capacidadeA = lerCapacidade(entrada);
    System.out.println("Digite a capacidade do Array B: ");
    int capacidadeB = lerCapacidade(entrada);

    Lista filaA = new Lista(capacidadeA);
    Lista filaB = new Lista(capacidadeB);

    Lista filaEscolhida;

    while(!sair){
        System.out.println("Digite qual fila quer adicionar: A/B ou C pra executar o Merge ou E para sair, ou D para Remover valor!");
        String opcao = entrada.next();

        switch (opcao.toLowerCase()){
            case "a":
                filaEscolhida = filaA;
                break;

            case "b":
                filaEscolhida = filaB;
                break;

            case "c":
                Lista filaC = filaA.merge(filaB);
                filaC.imprimir();
                continue;

            case "d":
                System.out.println("Escolhe uma fila pra remover: A/B");
                String Opcao = entrada.next();
                if(Opcao.equalsIgnoreCase("a")){
                    filaEscolhida = filaA;
                }else if(Opcao.equalsIgnoreCase("b")){
                    filaEscolhida = filaB;
                }else{
                    System.out.println("Escolha A ou B.");
                    continue;
                }
                try{

                    int valorRemovido = filaEscolhida.remover();
                    int meuUltimoValor = 0;
                    System.out.println("Valor Removido: " + valorRemovido);
                    if(!filaEscolhida.vazia()){

                        meuUltimoValor = filaEscolhida.consultaUltimo();
                        System.out.println("Último Valor: " + meuUltimoValor);
                    }

                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Index inválido");
                } catch (NoSuchElementException e) {
                    System.out.printf("Não há elementos no Array" + "\n");
                }

                continue;

            case "e":
                System.out.println("Saindo....");
                sair = true;
                continue;

            default:
                System.out.print("Opção inválida!");
                continue;


        }
        try{

            while(!filaEscolhida.cheia()){
                System.out.println("Deseja adicionar um valor: s/n");
                String OpcaoDesejada = entrada.next();
                if(OpcaoDesejada.equalsIgnoreCase("s")){

                    System.out.println("Digite o valor a inserir: ");
                    int valor = entrada.nextInt();
                    filaEscolhida.adicionar(valor);

                }else{
                    break;
                }
            }


            filaEscolhida.imprimir();

        }catch(NumberFormatException e){
            System.out.println("Formato Inválido");
        }catch(InputMismatchException e){
            System.out.println("Digite um número inteiro válido!");
            entrada.nextLine();
        }catch(NegativeArraySizeException e){
            System.out.println("Digite uma capacidade válida!");
        }


    }


    }
}
