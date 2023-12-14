import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tree {
    private Category root;

    public Tree(Category root) {
        this.root = root;
    }

    // Method for categories empty keywords inherit keywords froim their parents
    public void heritage(HashMap<Integer, Category> categories) {
        for (Category category : categories.values()) {
            if (category.getKeywords().isEmpty()) {
                int parentId = category.getParendId();
                if (parentId != -1) {
                    Category parent = categories.get(parentId);
                    category.setKeywords(parent.getKeywords());
                }
            }
        }
    }

    // Method for get Level of a category
    public int getLevel(Category category) {
        return getLevelRecursive(root, category, 0);
    }

    private int getLevelRecursive(Category currentCategory, Category targetCategory, int currentLevel) {
        // verifying if is the current
        if (currentCategory.equals(targetCategory)) {
            return currentLevel;
        }

        for (Category subcategory : currentCategory.getSubcategories()) {
            int level = getLevelRecursive(subcategory, targetCategory, currentLevel + 1);
            if (level != -1) {
                return level; // Level found
            }
        }

        return -1; // Category not found in this level
    }

    // Method to search categories by keyword
    public List<String[]> getCategoryByKeyword(String keyword) {
        List<String[]> result = new ArrayList<>();
        searchCategoriesByKeyword(root, keyword, result);
        return result;
    }

    private void searchCategoriesByKeyword(Category currentCategory, String keyword, List<String[]> result) {
        if (currentCategory.getKeywords().contains(keyword)) {
            String[] categoryData = {
                    String.valueOf(currentCategory.getId()),
                    String.valueOf(getLevel(currentCategory)),
                    currentCategory.getNameCategory(),
                    keyword
            };
            result.add(categoryData);
        }

        for (Category subcategory : currentCategory.getSubcategories()) {
            searchCategoriesByKeyword(subcategory, keyword, result);
        }
    }

    public void printCategories(List<String[]> categoriesResult) {
        StringBuilder jsonOutput = new StringBuilder("\n[");

        for (int i = 0; i < categoriesResult.size(); i++) {
            String[] categoryData = categoriesResult.get(i);

            String[] keywordsArray = categoryData[2].split(" ");
            String formattedKeywords = splitKeywords(keywordsArray);
            jsonOutput.append("{");
            jsonOutput.append("\"" + categoryData[0] + "\", ");
            jsonOutput.append("\"" + categoryData[1] + "\", ");
            jsonOutput.append(formattedKeywords + ", ");
            jsonOutput.append("\"" + categoryData[3] + "\"");
            jsonOutput.append("}");

            if (i < categoriesResult.size() - 1) {
                jsonOutput.append(",\n");
            }
        }

        jsonOutput.append("]\n");

        System.out.println(jsonOutput);
    }

    // Method get categories and details
    public void getDetailsByCategory(HashMap<Integer, Category> categories, int id) {
        StringBuilder jsonOutput = new StringBuilder("\n[");

        for (Category category : categories.values()) {
            if (category.getId() == id) {
                int level = getLevel(category);
                String[] keywordsArray = category.getKeywords().split(", ");
                String formattedKeywords = splitKeywords(keywordsArray);
                jsonOutput.append("{");
                jsonOutput.append("\"" + level + "\", ");
                jsonOutput.append(formattedKeywords);
                jsonOutput.append("}");
            }
        }
        jsonOutput.append("]\n");
        System.out.println(jsonOutput);
    }

    // Method to print split keywords
    private String splitKeywords(String[] keywordsArray) {
        StringBuilder formattedKeywords = new StringBuilder();
        for (int i = 0; i < keywordsArray.length; i++) {
            formattedKeywords.append("\"").append(keywordsArray[i]).append("\"");
            if (i < keywordsArray.length - 1) {
                formattedKeywords.append(", ");
            }
        }
        return formattedKeywords.toString();
    }

    // Print tree
    public void printTree(Category category, int level) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indentation.append("  ");
        }

        System.out.println(indentation.toString() + category.getId() + " " + category.getNameCategory() + " - "
                + category.getKeywords());

        for (Category subcategory : category.getSubcategories()) {
            printTree(subcategory, level + 1);
        }
    }
}