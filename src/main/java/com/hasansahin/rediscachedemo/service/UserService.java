package com.hasansahin.rediscachedemo.service;

import com.hasansahin.rediscachedemo.dto.CreateUserDto;
import com.hasansahin.rediscachedemo.dto.UpdateUserDto;
import com.hasansahin.rediscachedemo.model.User;
import com.hasansahin.rediscachedemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @CacheEvict(value = {"users", "user_id"}, allEntries = true)
    public User createUser(CreateUserDto createUserDto) {
        return userRepository.save(createUserDto.toEntity(createUserDto));
    }

    @Cacheable(cacheNames = "users", key = "#root.methodName", unless = "#result==null")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Cacheable(cacheNames = "user_id", key = "#root.methodName+#id", unless = "#result==null")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @CachePut(cacheNames = "user_id", key = "'getUserById'+#updateUserDto.id", unless = "#result==null")
    public User updateUser(UpdateUserDto updateUserDto) {
        Optional<User> user = userRepository.findById(updateUserDto.getId());
        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setPassword(updateUserDto.getPassword());
            return userRepository.save(updateUser);
        } else return null;
    }

    @CacheEvict(cacheNames = {"users", "user_id"}, allEntries = true)
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}
