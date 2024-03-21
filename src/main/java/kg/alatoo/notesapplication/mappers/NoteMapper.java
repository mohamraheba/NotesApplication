package kg.alatoo.notesapplication.mappers;

import kg.alatoo.notesapplication.dto.NoteDTO;
import kg.alatoo.notesapplication.entity.Note;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public NoteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NoteDTO convertToDto(Note note) {
        return modelMapper.map(note, NoteDTO.class);
    }

    public Note convertToEntity(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, Note.class);
    }
}