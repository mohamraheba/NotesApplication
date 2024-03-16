package kg.alatoo.notesapplication.services;

import kg.alatoo.notesapplication.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO findById(Long id);
    List<CategoryDTO> findAll();
    CategoryDTO update(Long id, CategoryDTO categoryDTO);
    void delete(Long id);
}
