package kg.alatoo.notesapplication.services;

import kg.alatoo.notesapplication.entity.Note;
import kg.alatoo.notesapplication.entity.Category;
import kg.alatoo.notesapplication.repositories.CategoryRepository;
import kg.alatoo.notesapplication.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoteCategoryService
{
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public void addCategoryToNote(Long noteId, Long categoryId) {

        Note note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));

        note.getCategories().add(category);

        noteRepository.save(note);
    }
}
