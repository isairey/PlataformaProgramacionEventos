package com.ess.essserver.module.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with email: " + userRequestDTO.getEmail());
        }

        User user = User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .role(userRequestDTO.getRole())
                .username(userRequestDTO.getUsername())
                .email(userRequestDTO.getEmail())
                .build();

        user = userRepository.save(user);

        return userMapper.toUserResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setRole(userRequestDTO.getRole());
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setUpdatedAt(LocalDate.now());

        userRepository.save(user);

        return userMapper.toUserResponseDTO(user);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return userMapper.toUserResponseDTO(user);
    }

    public List<UserResponseDTO> getPaginatedUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        // Map each User to a UserResponseDTO
        return userPage.stream()
                .map(userMapper::toUserResponseDTO)
                .toList();
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        userRepository.delete(user);
    }

}
