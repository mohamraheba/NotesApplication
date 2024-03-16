package kg.alatoo.notesapplication.dto;

import java.util.Set;

public class CategoryDTO {
    private Long id;
    private String name;
    private Set<Long> noteIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getNoteIds() {
        return noteIds;
    }

    public void setNoteIds(Set<Long> noteIds) {
        this.noteIds = noteIds;
    }
}