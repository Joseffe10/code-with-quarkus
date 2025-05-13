package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.UserEntity;
import org.acme.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userRepository.persist(userEntity);
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return userRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(Integer userId) {
        return (UserEntity) userRepository.findByIdOptional(userId)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com id: " + userId));
    }

    public UserEntity updateUser(Integer userId, UserEntity userEntity) {
        var user = findById(userId);

        user.setUsername(userEntity.getUsername());

        userRepository.persist(user);

        return user;
    }

    public void deleteById(Integer userId) {
        var user = findById(userId);
        userRepository.deleteById(user.getUserId());
    }
}