package kg.alatoo.notesapplication.repositories;

import kg.alatoo.notesapplication.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
