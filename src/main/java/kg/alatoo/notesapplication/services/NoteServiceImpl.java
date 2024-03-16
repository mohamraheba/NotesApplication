package kg.alatoo.notesapplication.services;

import kg.alatoo.notesapplication.dto.NoteDTO;
import kg.alatoo.notesapplication.entity.Category;
import kg.alatoo.notesapplication.entity.Note;
import kg.alatoo.notesapplication.repositories.NoteRepository;
import kg.alatoo.notesapplication.repositories.CategoryRepository; // Add this import
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) { // Modify constructor
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NoteDTO create(NoteDTO noteDTO) {
        Note note = modelMapper.map(noteDTO, Note.class);
        Note savedNote = noteRepository.save(note);
        return modelMapper.map(savedNote, NoteDTO.class);
    }

    @Override
    public NoteDTO findById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id: " + id));
        return modelMapper.map(note, NoteDTO.class);
    }

    @Override
    public List<NoteDTO> findAllWithCategories() {
        return null;
    }

    @Override
    public List<NoteDTO> findAll() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream()
                .map(note -> modelMapper.map(note, NoteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public NoteDTO update(Long id, NoteDTO noteDTO) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id: " + id));
        existingNote.setTitle(noteDTO.getTitle());
        existingNote.setContent(noteDTO.getContent());
        Note updatedNote = noteRepository.save(existingNote);
        return modelMapper.map(updatedNote, NoteDTO.class);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public NoteDTO addCategoriesToNote(Long noteId, List<Long> categoryIds) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id: " + noteId));

        categoryIds.forEach(categoryId -> {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));
            note.getCategories().add(category);
        });

        Note updatedNote = noteRepository.save(note);
        return modelMapper.map(updatedNote, NoteDTO.class);
    }
}
