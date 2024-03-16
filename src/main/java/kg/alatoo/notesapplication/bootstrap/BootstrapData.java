package kg.alatoo.notesapplication.bootstrap;

import kg.alatoo.notesapplication.entity.Category;
import kg.alatoo.notesapplication.entity.Note;
import kg.alatoo.notesapplication.repositories.CategoryRepository;
import kg.alatoo.notesapplication.repositories.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Note homework = new Note();

        homework.setTitle("Homework");
        homework.setContent("Do homework");

        Category abc = new Category();
        abc.setName("Abc");

        Note homeworkSaved = noteRepository.save(homework);
        Category abcSaved = categoryRepository.save(abc);

        Note cleaning = new Note();

        cleaning.setTitle("Cleaning");
        cleaning.setContent("Clean your room");

        Category abb = new Category();
        abb.setName("Abb");

        Note cleaningSaved = noteRepository.save(cleaning);
        Category abbSaved = categoryRepository.save(abb);

        abcSaved.getNotes().add(homeworkSaved);
        abbSaved.getNotes().add(cleaningSaved);
        homeworkSaved.getCategories().add(abcSaved);
        cleaningSaved.getCategories().add(abbSaved);


        System.out.println("In Bootstrap");
        System.out.println("Note Count:" + noteRepository.count());
        System.out.println("Category Count:" + categoryRepository.count());
    }
}
