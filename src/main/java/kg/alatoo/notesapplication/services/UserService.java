package kg.alatoo.notesapplication.services;

import kg.alatoo.notesapplication.dto.UserDTO;
import kg.alatoo.notesapplication.dto.authorization.RegistrationDTO;
import kg.alatoo.notesapplication.entity.Roles;
import kg.alatoo.notesapplication.entity.User;
import kg.alatoo.notesapplication.mappers.UserMapper;
import kg.alatoo.notesapplication.repositories.UserRepository;
import kg.alatoo.notesapplication.repositories.UserRolesRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRolesRepository userRolesRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserDTO register(RegistrationDTO registrationDTO) {
        Roles roles = userRolesRepository.findByRoleName(Roles.Name.USER)
                .orElseThrow(() -> new RuntimeException("USER role not found")); // Handle if the role doesn't exist
        User user = User.builder()
                .name(registrationDTO.getName())
                .username(registrationDTO.getUsername())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .roles(Set.of(roles))
                .build();
        User savedUser = userRepository.save(user);
        return userMapper.convertToDto(savedUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}