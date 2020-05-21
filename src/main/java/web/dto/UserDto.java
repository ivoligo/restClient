package web.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String city;
    private Integer age;
    private ArrayList<RoleDto> role ;

    public UserDto(){

    }

    public UserDto(Long id, String name, String surname, String email, String password, String city, Integer age, ArrayList<RoleDto> role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.city = city;
        this.age = age;
        this.role = role;
    }

}
