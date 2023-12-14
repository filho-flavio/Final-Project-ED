import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String nameCategory;
    private String keywords;
    private int parentId;
    List<Category> subcategories;

    public Category(int id, String name, String keywords, int parentId) {
        this.id = id;
        this.nameCategory = name;
        this.keywords = keywords;
        this.parentId = parentId;
        this.subcategories = new ArrayList<>();
    }

    // Getters and Setters
    public List<Category> getSubcategories() {
        return this.subcategories;
    }

    public int getId() {
        return this.id;
    }

    public int getParendId() {
        return this.parentId;
    }

    public String getNameCategory() {
        return this.nameCategory;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keyword){
        this.keywords = keyword;
    }
}
