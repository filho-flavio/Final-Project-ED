# Projeto de Estrutura de Dados em Java
**Descrição do Projeto:**

Este projeto implementa uma estrutura de dados para gerenciar categorias de produtos em um e-commerce. A estrutura utiliza uma combinação de classes `Category` e `Tree` para organizar as categorias de forma hierárquica e permitir a navegação eficiente entre elas.

**Arquivos:**

* **Category.java:** Define a classe `Category` para representar uma categoria de produto. A classe possui atributos como `id`, `name`, `parentCategory` e `subCategories`.
* **Tree.java:** Implementa uma estrutura de dados em árvore para organizar as categorias. A árvore utiliza a classe `Category` como elemento base e permite operações como adicionar, remover e buscar categorias.
* **Main.java:** Contém o código principal do projeto, que demonstra como usar as classes `Category` e `Tree` para gerenciar categorias de produtos.

**Funcionalidades:**

* **Adicionar categorias:** Permite adicionar novas categorias à árvore de categorias, definindo seus nomes e categorias pai.
* **Remover categorias:** Permite remover categorias da árvore de categorias, atualizando as referências das categorias filhas.
* **Buscar categorias:** Permite buscar categorias por ID ou nome, percorrendo a árvore de categorias de forma eficiente.
* **Navegar na árvore de categorias:** Permite navegar na árvore de categorias, exibindo as categorias pai e filhas em cada nível.

**Exemplos de Uso:**

```java
// Hash com categories
        HashMap<Integer, Category> categories = new HashMap<>();

        // Preenchendo as categorias de hash com cada linha e atributos
        for (Object[] row : data) {
            int id = (int) row[0];
            String name = (String) row[1];
            String keywords = (String) row[2];
            int parentId = (int) row[3];

            Category category = new Category(id, name, keywords, parentId);
            categories.put(id, category);
        }

        // Construindo a árvore
        for (Category category : categories.values()) {
            if (category.getParendId() != -1) {
                // Get the parentId of each category and create a parent
                Category parent = categories.get(category.getParendId()); // First I get the root
                parent.subcategories.add(category); // Adding ID 2 as child
            }
        }

        Category root = categories.get(1);
        Tree myTree = new Tree(root);
        myTree.heritage(categories);
        // myTree.printTree(root, 0);
```

**Tecnologias Utilizadas:**

* Java
* Estrutura de dados em árvore

**Requisitos de Execução:**

* Ambiente de desenvolvimento Java instalado
* JDK 8 ou superior

**Para executar o projeto:**

1. Clone o repositório do projeto em seu diretório local.
2. Compile os arquivos .java usando o comando `javac`.
3. Execute a classe `Main.java` usando o comando `java Main`.

**Observações:**

* Este projeto é um exemplo básico de como usar estruturas de dados para gerenciar categorias de produtos em um e-commerce.
* O projeto pode ser expandido para incluir funcionalidades adicionais, como a persistência de dados em um banco de dados.

