package kg.alatoo.notesapplication.repositories;

import kg.alatoo.notesapplication.entity.Category;
import kg.alatoo.notesapplication.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testFindById() {
        Long categoryId = 1L;
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);
        expectedCategory.setName("Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        Optional<Category> actualCategory = categoryRepository.findById(categoryId);

        assertEquals(expectedCategory.getId(), actualCategory.get().getId());
        assertEquals(expectedCategory.getName(), actualCategory.get().getName());
        verify(categoryRepository, times(1)).findById(categoryId);
    }

}
