package bbw.ng.cypresstutorialbackend.controller;

import bbw.ng.cypresstutorialbackend.model.Category;
import bbw.ng.cypresstutorialbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5174/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<Category> addCategory(@RequestBody String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Category category = categoryService.addCategory(name);
        return ResponseEntity.ok(category);
    }


    @GetMapping("/get-all-categories")
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}