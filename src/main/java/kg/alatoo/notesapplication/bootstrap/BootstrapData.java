package kg.alatoo.notesapplication.bootstrap;

import kg.alatoo.notesapplication.entity.Category;
import kg.alatoo.notesapplication.entity.Note;
import kg.alatoo.notesapplication.repositories.CategoryRepository;
import kg.alatoo.notesapplication.repositories.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {

    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;

    public BootstrapData(NoteRepository noteRepository, CategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category study = Category.builder()
                .name("Study")
                .build();
        Category home = Category.builder()
                .name("Home")
                .build();

        study = categoryRepository.save(study);
        home = categoryRepository.save(home);

        Note homework = Note.builder()
                .title("Homework")
                .content("Do homework")
                .categories(Set.of(study))
                .build();

        Note cleaning = Note.builder()
                .title("Cleaning")
                .content("Clean your room")
                .categories(Set.of(home))
                .build();

        noteRepository.save(homework);
        noteRepository.save(cleaning);

        System.out.println("In Bootstrap");
        System.out.println("Note Count:" + noteRepository.count());
        System.out.println("Category Count:" + categoryRepository.count());
    }
}
