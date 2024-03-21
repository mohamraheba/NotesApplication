package kg.alatoo.notesapplication.mappers;

import kg.alatoo.notesapplication.dto.NoteDTO;
import kg.alatoo.notesapplication.entity.Note;
import kg.alatoo.notesapplication.mappers.NoteMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class NoteMapperTest {

    @Test
    public void testConvertToDto() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");
        note.setContent("Test Content");

        ModelMapper modelMapper = mock(ModelMapper.class);
        NoteMapper noteMapper = new NoteMapper(modelMapper);

        NoteDTO noteDTO = noteMapper.convertToDto(note);

        assertEquals(note.getId(), noteDTO.getId());
        assertEquals(note.getTitle(), noteDTO.getTitle());
        assertEquals(note.getContent(), noteDTO.getContent());
    }

    @Test
    public void testConvertToEntity() {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(1L);
        noteDTO.setTitle("Test Title");
        noteDTO.setContent("Test Content");

        ModelMapper modelMapper = mock(ModelMapper.class);
        NoteMapper noteMapper = new NoteMapper(modelMapper);
        Note note = noteMapper.convertToEntity(noteDTO);

        assertEquals(noteDTO.getId(), note.getId());
        assertEquals(noteDTO.getTitle(), note.getTitle());
        assertEquals(noteDTO.getContent(), note.getContent());
    }
}
