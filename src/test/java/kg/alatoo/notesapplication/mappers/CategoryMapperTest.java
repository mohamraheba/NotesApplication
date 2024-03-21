package kg.alatoo.notesapplication.mappers;

import kg.alatoo.notesapplication.dto.CategoryDTO;
import kg.alatoo.notesapplication.entity.Category;
import kg.alatoo.notesapplication.mappers.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CategoryMapperTest {

    @Test
    public void testConvertToDto() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        ModelMapper modelMapper = mock(ModelMapper.class);
        CategoryMapper categoryMapper = new CategoryMapper(modelMapper);

        CategoryDTO categoryDTO = categoryMapper.convertToDto(category);

        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());
    }

    @Test
    public void testConvertToEntity() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test Category");

        ModelMapper modelMapper = mock(ModelMapper.class);
        CategoryMapper categoryMapper = new CategoryMapper(modelMapper);

        Category category = categoryMapper.convertToEntity(categoryDTO);

        assertEquals(categoryDTO.getId(), category.getId());
        assertEquals(categoryDTO.getName(), category.getName());
    }
}
