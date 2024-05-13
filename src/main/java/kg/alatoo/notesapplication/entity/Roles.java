package kg.alatoo.notesapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Roles {

    public static final Roles ADMIN = new Roles(Name.ADMIN);
    public static final Roles STAFF = new Roles(Name.STAFF);
    public static final Roles USER = new Roles(Name.USER);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use an autogenerated ID for the primary key

    @Enumerated(EnumType.STRING)
    @Column(unique = true) // Ensure role names are unique
    private Name roleName;

    public Roles(Name roleName) {
        this.roleName = roleName;
    }

    public enum Name {
        ADMIN,
        STAFF,
        USER,
    }
}