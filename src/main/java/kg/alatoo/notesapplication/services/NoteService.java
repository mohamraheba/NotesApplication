package kg.alatoo.notesapplication.services;
import kg.alatoo.notesapplication.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    NoteDTO create(NoteDTO noteDTO);
    NoteDTO findById(Long id);
    List<NoteDTO> findAll();
    NoteDTO update(Long id, NoteDTO noteDTO);
    void delete(Long id);
}
