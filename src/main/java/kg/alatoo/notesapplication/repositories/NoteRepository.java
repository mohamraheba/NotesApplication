package kg.alatoo.notesapplication.repositories;

import kg.alatoo.notesapplication.entity.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note,Long> {
}