package kg.alatoo.notesapplication.repositories;

import kg.alatoo.notesapplication.entity.Note;
import kg.alatoo.notesapplication.services.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class NoteRepositoryTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void testFindById() {
        Long noteId = 1L;
        Note expectedNote = new Note();
        expectedNote.setId(noteId);
        expectedNote.setTitle("Test Note");

        when(noteRepository.findById(noteId)).thenReturn(Optional.of(expectedNote));

        Optional<Note> actualNote = noteRepository.findById(noteId);

        assertEquals(expectedNote.getId(), actualNote.get().getId());
        assertEquals(expectedNote.getTitle(), actualNote.get().getTitle());
        verify(noteRepository, times(1)).findById(noteId);
    }
}
