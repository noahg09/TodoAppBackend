// CategoryControllerTest.java
package bbw.ng.cypresstutorialbackend.controller;

import bbw.ng.cypresstutorialbackend.model.Category;
import bbw.ng.cypresstutorialbackend.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    public CategoryControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCategory_shouldReturnCategory() {
        String name = "TestCategory";
        Category category = new Category();
        category.setName(name);

        when(categoryService.addCategory(name)).thenReturn(category);

        ResponseEntity<Category> result = categoryController.addCategory(name);

        assertNotNull(result.getBody());
        assertEquals(name, result.getBody().getName());
    }

    @Test
    void getAllCategories_shouldReturnAllCategories() {
        Iterable<Category> categories = Arrays.asList(new Category(), new Category());

        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<Iterable<Category>> result = categoryController.getAllCategories();

        assertNotNull(result.getBody());
    }

    @Test
    void addCategory_shouldReturnBadRequestForNullName() {
        ResponseEntity<Category> result = categoryController.addCategory(null);

        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void addCategory_shouldReturnBadRequestForEmptyName() {
        ResponseEntity<Category> result = categoryController.addCategory("");

        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void getAllCategories_shouldReturnEmptyListWhenNoCategoriesExist() {
        when(categoryService.getAllCategories()).thenReturn(Collections.emptyList());

        ResponseEntity<Iterable<Category>> result = categoryController.getAllCategories();

        assertNotNull(result.getBody());
        assertFalse(result.getBody().iterator().hasNext());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void getAllCategories_shouldReturnEmptyListIfNoCategoriesExist() {
        when(categoryService.getAllCategories()).thenReturn(Collections.emptyList());

        ResponseEntity<Iterable<Category>> response = categoryController.getAllCategories();

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().iterator().hasNext());
    }

}
