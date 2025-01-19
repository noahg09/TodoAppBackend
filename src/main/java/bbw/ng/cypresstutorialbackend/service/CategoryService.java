package bbw.ng.cypresstutorialbackend.service;

import bbw.ng.cypresstutorialbackend.model.Category;
import bbw.ng.cypresstutorialbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }


    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}