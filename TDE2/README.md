# TD2 — Árvore binária de código Morse

Programa em Java para cadastrar associações entre códigos Morse e caracteres, traduzir sequências cadastradas e visualizar a árvore hierarquicamente.

**A árvore começa vazia, sem um alfabeto previamente carregado.** Os códigos e caracteres são inseridos manualmente pelo menu.

## Execução

Requisito: JDK 17 ou superior. O programa foi compilado e executado com Java 17 e não utiliza dependências externas.

Mantenha estes arquivos na mesma pasta `TD2`:

- `Main.java`
- `No.java`
- `ArvoreBinariaMorse.java`

Dentro dessa pasta, execute:

```bash
javac -encoding UTF-8 Main.java No.java ArvoreBinariaMorse.java
java Main
```

## Organização

| Classe | Responsabilidade |
| --- | --- |
| `No` | Guardar um caractere e referências aos filhos esquerdo e direito. |
| `ArvoreBinariaMorse` | Inicializar a raiz, inserir códigos, buscar caracteres, traduzir mensagens e imprimir a árvore. |
| `Main` | Apresentar o menu, ler entradas e exibir resultados e mensagens de validação. |

## Menu

| Opção | Ação |
| --- | --- |
| A | Cadastrar um código e seu caractere. |
| B | Buscar um código ou traduzir vários códigos separados por espaços. |
| C | Imprimir a árvore hierarquicamente. |
| D | Encerrar. |

Na opção A, informe primeiro o código e depois um único caractere diferente de espaço. Responda `n` para finalizar o cadastro ou `s` para continuar.

## Estrutura e inserção

Cada símbolo determina um movimento a partir da raiz:

| Símbolo | Movimento |
| --- | --- |
| `.` | Filho esquerdo. |
| `-` | Filho direito. |

A raiz é criada sob demanda por `inicializar()`, chamada na inserção e na busca. Ela contém um espaço como caractere inicial. Ao inserir, os nós ausentes no caminho são criados com espaço; o nó final recebe o caractere informado.

Por exemplo, cadastrar `.-` como `A` cria ou percorre o filho esquerdo da raiz e, em seguida, seu filho direito. Cadastrar `.` como `E` depois disso preenche o nó intermediário sem apagar o caminho até A.

A posição de cada caractere é determinada pelo código informado, não pela ordem alfabética. Cadastrar novamente o mesmo código substitui seu caractere. O programa valida a sequência de pontos e traços, mas não verifica se a associação informada corresponde à tabela internacional de Morse.

## Tradução

A opção B usa os caracteres já cadastrados:

- Um espaço separa códigos de caracteres.
- Dois espaços entre códigos produzem um espaço entre palavras no resultado.
- Códigos com símbolos diferentes de `.` e `-` são rejeitados.
- Um caminho inexistente ou um nó ainda sem caractere cadastrado retorna um espaço.

A barra `/` não é utilizada como separador. Esta versão realiza a tradução de Morse para caracteres; não implementa a conversão inversa de texto para Morse.

## Exemplo de uso

Pela opção A, cadastre:

| Código | Caractere |
| --- | --- |
| `...` | S |
| `---` | O |

Depois, selecione B e informe:

```text
... --- ...
```

Resultado:

```text
Resultado: SOS
```

Somente S e O precisam estar cadastrados para esse exemplo. Os nós intermediários são criados automaticamente, mas seus caracteres continuam em branco até serem cadastrados.

## Impressão e recursão

O método `imprimir` percorre a árvore em pré-ordem:

1. Imprime o nó atual com sua indentação.
2. Visita recursivamente o filho esquerdo.
3. Visita recursivamente o filho direito.

O caso de parada é `atual == null`. Os marcadores `.` e `-` acrescentados à indentação indicam o caminho percorrido. Antes da primeira inicialização, a opção C exibe apenas o cabeçalho e o rodapé, pois ainda não existe raiz.

## Métodos principais

| Método | Finalidade |
| --- | --- |
| `inicializar()` | Criar a raiz caso ainda não exista e retorná-la. |
| `inserir(String, char)` | Criar ou percorrer um caminho e registrar seu caractere. |
| `buscar(String)` | Percorrer um código e retornar o caractere encontrado. |
| `traduzir(String)` | Separar os códigos por espaços e compor a mensagem. |
| `imprimir(No, String)` | Exibir recursivamente a hierarquia. |
| `getRaiz()` | Disponibilizar a referência inicial para a impressão. |

A inserção e a busca percorrem O(k) símbolos, sendo k o tamanho do código. A impressão visita cada nó uma vez e utiliza uma pilha de chamadas proporcional à altura da árvore; a formatação acrescenta o custo de construir e exibir as indentações.

Os cadastros permanecem apenas na memória e são perdidos ao encerrar o programa.

## Transparência sobre o uso de IA

A elaboração e a revisão deste README contaram com auxílio de inteligência artificial, incluindo a organização das explicações, tabelas e exemplos para facilitar o entendimento.
