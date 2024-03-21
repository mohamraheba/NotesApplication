package kg.alatoo.notesapplication.controllers;

import kg.alatoo.notesapplication.dto.NoteDTO;
import kg.alatoo.notesapplication.mappers.NoteMapper;
import kg.alatoo.notesapplication.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @Autowired
    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @PostMapping
    public ResponseEntity<NoteDTO> create(@RequestBody NoteDTO noteDTO) {
        NoteDTO createdNote = noteService.create(noteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> findById(@PathVariable Long id) {
        NoteDTO noteDTO = noteService.findById(id);
        return ResponseEntity.ok(noteDTO);
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> findAll() {
        List<NoteDTO> noteDTOs = noteService.findAllWithCategories();
        return ResponseEntity.ok(noteDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> update(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        NoteDTO updatedNote = noteService.update(id, noteDTO);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
