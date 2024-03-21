package kg.alatoo.notesapplication.mappers;

import kg.alatoo.notesapplication.dto.CategoryDTO;
import kg.alatoo.notesapplication.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO convertToDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}