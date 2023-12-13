import java.util.ArrayList;
import java.util.List;

class Tree {
    private Category root;

    public Tree(Category root) {
        this.root = root;
    }

    public int getLevel(Category category) {
        return getLevelRecursive(root, category, 1);
    }

    private int getLevelRecursive(Category currentCategory, Category targetCategory, int currentLevel) {
        if (currentCategory.equals(targetCategory)) {
            return currentLevel;
        }

        for (Category subcategory : currentCategory.getSubcategories()) {
            int level = getLevelRecursive(subcategory, targetCategory, currentLevel + 1);
            if (level != -1) {
                return level; // Encontrou a categoria, retorna o nível
            }
        }

        return -1; // Categoria não encontrada na subárvore atual
    }

    public List<String[]> getCategoryByKeyword(String keyword) {
        List<String[]> result = new ArrayList<>();
        searchCategoriesByKeyword(root, keyword, result);
        return result;
    }

    private void searchCategoriesByKeyword(Category currentCategory, String keyword, List<String[]> result) {
        if (currentCategory.getKeywords().contains(keyword)) {
            // Se possuir, cria um array com os dados e adiciona à lista de resultados.
            String[] categoryData = {
                    String.valueOf(currentCategory.getId()),
                    String.valueOf(getLevel(currentCategory)),
                    currentCategory.getNameCategory(),
                    keyword
            };
            result.add(categoryData);
        }

        // Percorre as subcategorias da categoria atual.
        for (Category subcategory : currentCategory.getSubcategories()) {
            // Chama recursivamente o método para cada subcategoria.
            searchCategoriesByKeyword(subcategory, keyword, result);
        }
    }

    public static void printCategories(List<String[]> categoriesResult) {
        StringBuilder jsonOutput = new StringBuilder("[\n");
    
        for (int i = 0; i < categoriesResult.size(); i++) {
            String[] categoryData = categoriesResult.get(i);
    
            jsonOutput.append("{");
            jsonOutput.append("\"ID\": \"" + categoryData[0] + "\", ");
            jsonOutput.append("\"Nível\": \"" + categoryData[1] + "\", ");
            jsonOutput.append("\"Nome\": \"" + categoryData[2] + "\", ");
            jsonOutput.append("\"Palavra-chave\": \"" + categoryData[3] + "\", ");
            jsonOutput.append("\"------\": \"------\"");
            jsonOutput.append("}");
    
            if (i < categoriesResult.size() - 1) {
                jsonOutput.append(",\n");
            }
        }
    
        jsonOutput.append("\n]");
    
        System.out.println(jsonOutput.toString());
    }
    

    public void printTree(Category category, int level) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indentation.append("  ");
        }

        System.out.println(indentation.toString() + category.getNameCategory() + " - " + category.getKeywords());

        for (Category subcategory : category.getSubcategories()) {
            printTree(subcategory, level + 1);
        }
    }
}