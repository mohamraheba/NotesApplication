package kg.alatoo.notesapplication.services;

import kg.alatoo.notesapplication.dto.NoteDTO;
import kg.alatoo.notesapplication.entity.Category;
import kg.alatoo.notesapplication.entity.Note;
import kg.alatoo.notesapplication.exception.ResourceNotFoundException;
import kg.alatoo.notesapplication.mappers.CategoryMapper;
import kg.alatoo.notesapplication.mappers.NoteMapper;
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
    private final CategoryMapper categoryMapper;
    private final NoteMapper noteMapper;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, CategoryMapper categoryMapper, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.categoryMapper = categoryMapper;
        this.noteMapper = noteMapper;
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
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + id));
        return noteMapper.convertToDto(note);
    }

    @Override
    public List<NoteDTO> findAllWithCategories() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream()
                .map(this::convertToDTOWithCategories)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteDTO> findAll() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream()
                .map(noteMapper::convertToDto)
                .collect(Collectors.toList());
    }

    private NoteDTO convertToDTOWithCategories(Note note) {
        NoteDTO noteDTO = modelMapper.map(note, NoteDTO.class);
        noteDTO.setCategories(note.getCategories().stream()
                .map(categoryMapper::convertToDto)
                .collect(Collectors.toSet()));
        return noteDTO;
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
