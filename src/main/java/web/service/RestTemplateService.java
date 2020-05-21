package web.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.dto.RoleDto;
import web.dto.UserDto;
import web.dto.UsersDto;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.basicAuthentication("admin@1", "admin").build();
    }

    public List<UserDto> getUsers() {
        String url = "http://localhost:8081/rest/allUsers";
        return restTemplate.getForObject(url, List.class, UserDto.class);
    }

    public UserDto userById(Long id){
        String url = "http://localhost:8081/rest/getUser?id={id}";
        return restTemplate.getForObject(url, UserDto.class, id);
    }


    public void delete(Long id){
        String url = "http://localhost:8081/rest/delete?id={id}";
        restTemplate.delete(url,id);

    }

    public RoleDto getRole(String roleSet){
        String url = "http://localhost:8081/rest/getRole?roleSet={roleSet}";
        return restTemplate.getForObject(url,  RoleDto.class, roleSet);
    }

    public void createUser(UserDto user){
        String url = "http://localhost:8081/rest/createUser";
         restTemplate.postForObject(url,  user , UserDto.class);
    }

    public void update(UserDto user){
        String url = "http://localhost:8081/rest/update";
        HttpEntity<UserDto> request = new HttpEntity<>(user);
        restTemplate.put(url,  request , UserDto.class);
    }
}
