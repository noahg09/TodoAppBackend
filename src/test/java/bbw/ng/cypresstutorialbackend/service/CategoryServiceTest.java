package bbw.ng.cypresstutorialbackend.service;

import bbw.ng.cypresstutorialbackend.model.Category;
import bbw.ng.cypresstutorialbackend.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    public CategoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCategory_shouldSaveAndReturnCategory() {
        String name = "TestCategory";
        Category category = new Category();
        category.setName(name);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.addCategory(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void getAllCategories_shouldReturnAllCategories() {
        Iterable<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn((List<Category>) categories);

        Iterable<Category> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void addCategory_shouldThrowExceptionForEmptyName() {
        String emptyName = "";

        assertThrows(IllegalArgumentException.class, () -> categoryService.addCategory(emptyName));
    }

    @Test
    void addCategory_shouldThrowExceptionForWhitespaceName() {
        String name = "   ";

        assertThrows(IllegalArgumentException.class, () -> categoryService.addCategory(name));
    }

    @Test
    void getAllCategories_shouldReturnEmptyIterableIfNoCategories() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        Iterable<Category> result = categoryService.getAllCategories();

        assertFalse(result.iterator().hasNext());
        verify(categoryRepository, times(1)).findAll();
    }

}
