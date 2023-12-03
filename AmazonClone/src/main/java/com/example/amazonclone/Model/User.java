package com.example.amazonclone.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username should not be empty")
    @Size(min = 6,message = "Username must be more than 5")
    @Column(columnDefinition = "varchar(25) not null")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 7,message = "Password must be more than 6")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{7,}$",message = "password should be 7 characters at least and contains one uppercase letter, lowercase, a special character (#$@^&*-!?) and digits ")
    @Column(columnDefinition = "varchar(30) not null ")
    private String password;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email")
    @Column(columnDefinition = "varchar(30) not null")
    private String email;
    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "Admin|Customer",message = "Role should be Admin or Customer")
    @Column(columnDefinition = "varchar(10) not null check(role = 'Admin' or role ='Customer')")
    private String role;
    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance should be a positive number")
    @Column(columnDefinition = "double not null")
    private double balance;

}
