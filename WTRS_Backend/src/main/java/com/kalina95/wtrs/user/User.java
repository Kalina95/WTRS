package com.kalina95.wtrs.user;


import com.kalina95.wtrs.employee.Employee;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private String role;
    @OneToOne
    private Employee employee;

}
