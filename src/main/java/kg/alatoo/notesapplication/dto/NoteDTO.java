package kg.alatoo.notesapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class NoteDTO {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private Set<CategoryDTO> categories;
}