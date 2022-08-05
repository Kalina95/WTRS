package com.kalina95.wtrs.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserDto> getAll() {
        List<UserDto> listOfDTOs = repository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
        return listOfDTOs;
    }

    public UserDto getById(int id) {
        UserDto userDto = new UserDto(repository.findById(id).orElseThrow(RuntimeException::new));
        return userDto;
    }

    public int create(User user) {
        user.setUserId(0);
        repository.save(user);
        return user.getUserId();
    }

    public int update(int id, User user) {
        user.setUserId(id);
        repository.save(user);
        return user.getUserId();
    }

    public void delete(int id) {
        repository.delete(repository.findById(id).orElseThrow(RuntimeException::new));
    }
}
