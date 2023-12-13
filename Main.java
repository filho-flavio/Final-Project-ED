import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Object[][] data = {
                { 1, "Raiz", "Produtos", -1 },
                { 2, "Móveis", "Móveis", 1 }, // level 1
                { 3, "Eletrônicos", "Eletrônicos, Gadgets", 1 }, // level 1
                { 4, "Casa e Eletrodomésticos", "Casa, Eletrodomésticos", 3 }, // level 2
                { 5, "Eletrodomésticos Principais", "", 4 }, // level 3
                { 6, "Eletrodomésticos Secundários", "", 4 }, // level 3
                { 7, "Gramado e Jardim", "Gramado, Jardim", 6 }, // level
                { 8, "Eletrodomésticos de Cozinha", "", 5 },
                { 9, "Eletrodomésticos em Geral", "", 5 }
        };

        HashMap<Integer, Category> categories = new HashMap<>();

        for (Object[] row : data) {
            int id = (int) row[0];
            String name = (String) row[1];
            String keywords = (String) row[2];
            int parentId = (int) row[3];

            Category category = new Category(id, name, keywords, parentId);
            categories.put(id, category);
        }

        // Building the tree
        for (Category category : categories.values()) {
            if (category.getParendId() != -1) {
                // Get the parentId of each category and create a parent
                Category parent = categories.get(category.getParendId());
                parent.subcategories.add(category);
            }
        }

        Category root = categories.get(1);
        Tree myTree = new Tree(root);

        // Getting the level of category
        Category targetCategory = categories.get(2);
        int level = myTree.getLevel(targetCategory);
        System.out.println("Level of the category: " + level);

        List<String[]> categoriesResult = myTree.getCategoryByKeyword("Casa");

        myTree.printCategories(categoriesResult);

        // myTree.printTree(root, 0);

        // for(Category category : categories.values()){
        // System.out.println("ID: " + category.getId() + ", Nome: " +
        // category.getNameCategory() + ", Palavra chave: " + category.getKeywords());
        // }

    }
}