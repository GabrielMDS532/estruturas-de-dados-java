# TD1 — Fila circular e merge ordenado

Implementação em Java de duas filas de inteiros, armazenadas em arrays de capacidade fixa, com inserção, remoção e combinação em uma terceira fila ordenada.

## Execução

Requisito: JDK 17 ou superior. O programa foi compilado e executado com Java 17 e não utiliza dependências externas.

Na pasta `TD1`, execute:

```bash
javac -encoding UTF-8 ListaNumeros.java
java ListaNumeros
```

O arquivo deve se chamar exatamente `ListaNumeros.java`.

## Organização

| Classe | Responsabilidade |
| --- | --- |
| `Lista` | Armazenar os valores e implementar as operações da fila circular e o merge. |
| `ListaNumeros` | Ler capacidades, apresentar o menu e tratar as entradas do usuário. |

As duas classes estão no arquivo `ListaNumeros.java`.

## Menu

Ao iniciar, informe a capacidade de A e de B. Capacidades negativas e entradas não inteiras são rejeitadas. A capacidade zero é aceita, mas não permite inserções.

| Opção | Ação |
| --- | --- |
| A | Inserir valores na fila A. |
| B | Inserir valores na fila B. |
| C | Executar o merge de A e B e imprimir C. |
| D | Escolher A ou B e remover seu primeiro elemento. |
| E | Encerrar. |

Durante a inserção, responda `s` para informar um valor ou `n` para voltar ao menu. Quando a capacidade é atingida, a etapa de inserção termina automaticamente.

## Funcionamento da fila circular

- `inicio` indica a posição física do primeiro elemento.
- `quantidade` registra quantos elementos estão em uso.
- `valores.length` representa a capacidade fixa do array.

| Operação | Cálculo do índice |
| --- | --- |
| Inserir | `(inicio + quantidade) % valores.length` |
| Avançar após remover | `(inicio + 1) % valores.length` |
| Consultar o último | `(inicio + quantidade - 1) % valores.length` |

A remoção segue FIFO: o primeiro elemento inserido é o primeiro removido. Os elementos não são deslocados; a posição inicial avança circularmente. A impressão considera somente os elementos em uso e respeita a ordem lógica da fila.

O preenchimento inicial com `-1` não define se uma posição está ocupada: esse controle depende de `quantidade`. Valores negativos, inclusive `-1`, podem ser inseridos.

## Ordenação e merge

Nesta implementação, cada fila aceita somente valores maiores ou iguais ao último elemento presente. Um valor menor é rejeitado. Valores repetidos são permitidos. Assim, A e B permanecem em ordem crescente, admitindo empates.

O método `merge` compara os primeiros valores de duas filas distintas, remove o menor e o insere em C. Quando uma fila fica vazia, transfere os elementos restantes da outra.

**O merge consome as filas de origem: A e B ficam vazias.** A capacidade de C é a soma das quantidades de A e B antes da transferência. O menu imprime C imediatamente, mas não oferece operações posteriores sobre ela.

## Exemplo

1. Informe capacidade `3` para cada fila.
2. Pela opção A, insira `1`, `3` e `5`.
3. Pela opção B, insira `2`, `4` e `6`.
4. Selecione C.

Saída do merge:

```text
Array: [1, 2, 3, 4, 5, 6]
```

Depois disso, remover de A ou B informa que não há elementos. As filas podem receber novas inserções.

## Métodos principais

| Método | Finalidade |
| --- | --- |
| `cheia()` / `vazia()` | Consultar o estado da fila. |
| `adicionar(int)` | Inserir respeitando capacidade e ordenação. |
| `remover()` | Remover e retornar o primeiro valor. |
| `consultaPrimeiro()` / `consultaUltimo()` | Consultar valores sem removê-los. |
| `merge(Lista)` | Retornar uma nova fila com os valores combinados. |
| `imprimir()` | Exibir os elementos em uso. |

Inserção, remoção e consultas têm custo O(1). O merge tem custo O(n + m), considerando as quantidades das duas filas, e utiliza O(n + m) de espaço para o resultado.

Os dados permanecem apenas na memória durante a execução.
