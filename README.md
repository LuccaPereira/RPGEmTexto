# Crônicas Esquecidas: Um RPG de Texto em Java

Este projeto é um RPG de texto clássico, focado em escolhas, combate e gerenciamento de inventário. O verdadeiro desafio e foco deste meu projeto não foi apenas criar uma história, mas construir um sistema **modular, flexível e robusto**, seguindo os princípios fundamentais da Programação Orientada a Objetos.

Pensei o "design" do código para que cada classe tenha uma responsabilidade única, permitindo que o jogo seja facilmente expandido (novas classes, novos itens, novas habilidades) sem quebrar a lógica existente.

## 🚀 Funcionalidades (Features)

* **Sistema de Batalha em Turnos:** Um loop de combate tático (`Battle.java`) que gerencia as ações do jogador e do inimigo.
* **Escolha de Classes (Polimorfismo):** O jogador pode escolher entre `Mago`, `Guerreiro` e `Arqueiro`, cada um com habilidades e lógicas de ataque únicas (ex: `lancarBolaDeFogo`) que são chamadas durante a batalha.
* **História Ramificada:** O jogo é movido por um "motor" de história (`Jogo.java`) que avança por capítulos (`progressoHistoria`) e apresenta ao jogador escolhas que afetam o desenrolar dos eventos.
* **Sistema de Inventário Avançado:**
    * **Empilhamento de Itens:** `addItem` verifica se o item já existe (`encontrarItemPorNome`) e incrementa a `quantidade`, em vez de criar duplicatas.
    * **Consumo de Itens:** `removerItem` decrementa a `quantidade` e só remove o item da lista se a quantidade chegar a zero.
    * **Ordenação:** `listarInventario` ordena os itens alfabeticamente usando `sort()` e uma expressão *lambda*.
* **Sistema de Loot (Clonagem):** Inimigos (`InventarioInimigo`) soltam "clones" independentes (`item.clone()`) de seus itens ao morrer, garantindo que o item original do "molde" do inimigo não seja alterado.
* **Gerenciamento de Recursos:** Classes como `Mago` e `Guerreiro` gerenciam seus próprios recursos (Mana e Fúria) para usar habilidades especiais.
* **Lógica de Efeitos (Switch):** Um `switch` centralizado (no `Personagem.java`) controla o *efeito* de cada item (`Pocao`, `Pocao de Mana`), mantendo a lógica de uso separada da lógica de armazenamento.

---

## 🛠️ Passo a Passo: O "Design" do Código

O desenvolvimento foi focado em separar as responsabilidades. Eu usei um "desenho" (design pattern) que combina Herança, Composição e Delegação para criar um código limpo.

### 1. O "Desenho": Herança (`É UM`) vs. Composição (`TEM UM`)

Eu dividi a arquitetura do projeto em "Famílias" de classes que se conectam:

* **Herança (Relação `É UM`):** Usei para criar "tipos" de uma mesma família.
    * `Mago` **É UM** `Personagem`.
    * `Guerreiro` **É UM** `Personagem`.
    * `InventarioInimigo` **É UM** `Inventario`.

* **Composição (Relação `TEM UM`):** Usei para "conectar" as famílias e dar funcionalidades.
    * `Jogo` **TEM UM** `Jogador` (que é um `Personagem`).
    * `Jogo` **TEM UM** `Battle` (o "Gerente" da batalha).
    * `Personagem` **TEM UM** `Inventario` (a "Mochila").

### 2. O Princípio da Responsabilidade Única (SRP)

Cada classe tem apenas **um trabalho**. Isso foi crucial para eu "arrumar" o código:

* **`Jogo.java` (O "Controlador da História"):** Seu único trabalho é gerenciar o `loopPrincipal`, o `progressoHistoria` e chamar os Capítulos.
* **`Battle.java` (O "Diretor da Batalha"):** Seu único trabalho é gerenciar o loop de combate, o menu de batalha, e os turnos.
* **`InputHelper.java` (O "Recepcionista"):** Seu único trabalho é lidar com o `Scanner` e obter entradas válidas do usuário.
* **`Personagem.java` (O "Ator" ou "Chefe"):** Seu único trabalho é gerenciar o *estado* do personagem (PV, Ataque, Nível) e *decidir* o que fazer (ex: `usarItem`, `tomarDano`).
* **`Inventario.java` (O "Especialista"):** Seu único trabalho é gerenciar o *armazenamento* e a *lógica* da lista de itens (adicionar, remover, encontrar).

### 3. O Padrão "Chefe-Especialista" (Delegação)

Este foi o "desenho" principal que eu implementei. O "Chefe" (`Personagem`) nunca faz o trabalho sujo; ele **delega** para o "Especialista" (`Inventario`).

* **Para usar um item:**
    1.  A `Battle` (Diretor) manda: `jogador.usarItem("Pocao")`.
    2.  O `Personagem` (Chefe) recebe o comando.
    3.  Ele aplica o efeito (cura a si mesmo: `this.setPontosVida(...)`).
    4.  Ele **delega** a remoção: `this.inventario.removerItem(itemEncontrado)`.
    5.  O `Inventario` (Especialista) faz a lógica de decrementar a quantidade.

* **Para tomar dano:**
    1.  A `Battle` (Diretor) calcula o `dano`.
    2.  Ela **delega**: `defensor.tomarDano(dano, atacante)`.
    3.  O `Personagem` (Defensor) recebe o comando e aplica o dano *em si mesmo*: `this.setPontosVida(this.getPontosVida() - dano)`.

### 4. O "Setter Inteligente" (Encapsulamento)

Para evitar que a cura (`usarItem`) ultrapassasse a vida base, eu implementei um "Setter Inteligente" na classe `Personagem`.

* A classe `Personagem` armazena `pontosVidaMaximo`.
* O método `setPontosVida(int novaVida)` **impõe as regras**:
    * Ele usa `Math.max(0, novaVida)` para garantir que a vida nunca fique negativa.
    * Ele usa `Math.min(..., this.pontosVidaMaximo)` para garantir que a vida nunca ultrapasse o limite máximo.
