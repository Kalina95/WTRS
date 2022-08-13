package com.kalina95.wtrs.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private List<User> listOfUsers;
    @BeforeEach
    public void setup(){
        User user1 = User.builder()
                .userId(1)
                .login("login1")
                .role(SystemRole.ROLE_USER)
                .password("somePassword")
                .build();

        User user2 = User.builder()
                .userId(2)
                .login("login2")
                .role(SystemRole.ROLE_ADMIN)
                .password("somePassword2")
                .build();

        listOfUsers = newArrayList(user1, user2);
    }
    @Test
    void getAll_twoElementsInList_returnsTwoElements() {
        when(repository.findAll()).thenReturn(listOfUsers);
        assertThat(service.getAll().size()).isEqualTo(2);
        assertThat(service.getAll().get(0)).isInstanceOf(UserDto.class);

    }

    @Test
    void getAll_zeroElementsInList_returnsEmptyList() {
        listOfUsers.clear();
        when(repository.findAll()).thenReturn(listOfUsers);
        assertThat(service.getAll().size()).isEqualTo(0);
    }

    @Test
    void getById_elementFound_returnsElementAsDto() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(listOfUsers.get(0)));
        assertThat(service.getById(1).getLogin()).isEqualTo("login1");
        assertThat(service.getById(1)).isInstanceOf(UserDto.class);
    }

    @Test
    void getById_elementNotFound_throwsRunTimeError() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getById(1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void create() {
        User newUser = User.builder()
                .userId(3)
                .login("login3")
                .role(SystemRole.ROLE_ADMIN)
                .password("somePassword3")
                .build();

        when(repository.save(newUser)).thenReturn(newUser);
        assertThat(service.create(newUser)).isEqualTo(0);
        verify(repository).save(newUser);
    }

    @Test
    void update_userWithGivenIdFound_returnId() {
        int id = 1;
        User newUser = User.builder()
                .userId(3)
                .login("login3")
                .role(SystemRole.ROLE_ADMIN)
                .password("somePassword3")
                .build();

        when(repository.findById(id)).thenReturn(Optional.ofNullable(newUser));
        when(repository.save(newUser)).thenReturn(newUser);
        assertThat(service.update(1, newUser)).isEqualTo(1);
        verify(repository).save(newUser);
    }

    @Test
    void update_userWithGivenIdNotFound_createsNewUser() {
        int id = 1;
        User newUser = User.builder()
                .userId(3)
                .login("login3")
                .role(SystemRole.ROLE_ADMIN)
                .password("somePassword3")
                .build();

        when(repository.findById(id)).thenReturn(Optional.empty());
        when(repository.save(newUser)).thenReturn(newUser);
        assertThat(service.update(1, newUser)).isEqualTo(0);
    }

    @Test
    void delete_userFound_deleted() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(listOfUsers.get(0)));
        service.delete(1);
        verify(repository).delete(listOfUsers.get(0));
    }

    @Test
    void delete_userNotFound_exceptionThrown() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(()->service.delete(1)).isInstanceOf(RuntimeException.class);
    }
}