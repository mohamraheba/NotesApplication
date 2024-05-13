package kg.alatoo.notesapplication.bootstrap;

import kg.alatoo.notesapplication.entity.Category;
import kg.alatoo.notesapplication.entity.Note;
import kg.alatoo.notesapplication.entity.Roles;
import kg.alatoo.notesapplication.entity.User;
import kg.alatoo.notesapplication.repositories.CategoryRepository;
import kg.alatoo.notesapplication.repositories.NoteRepository;
import kg.alatoo.notesapplication.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {

    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public BootstrapData(NoteRepository noteRepository, CategoryRepository categoryRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        User admin = User.builder()
                .name("Raheba")
                .username("admin")
                .roles(Set.of(Roles.ADMIN, Roles.STAFF))
                .password(passwordEncoder.encode("pass"))
                .build();
        User staff = User.builder()
                .name("Staff")
                .username("staff")
                .password(passwordEncoder.encode("pass"))
                .roles(Set.of(Roles.STAFF))
                .build();


        User user = User.builder()
                .name("user")
                .username("user")
                .roles(Set.of(Roles.USER))
                .password(passwordEncoder.encode("pass"))
                .build();
        userRepository.saveAll(List.of(admin, staff, user));

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
