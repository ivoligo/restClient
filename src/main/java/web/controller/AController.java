package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dto.RoleDto;
import web.dto.UserDto;
import web.dto.UsersDto;
import web.service.RestTemplateService;

import java.util.*;

@Controller
@RequestMapping("/")
public class AController {
    private RestTemplateService restTemplateService;
    @Autowired
    public void setRestTemplateService(RestTemplateService restTemplateService){
        this.restTemplateService = restTemplateService;
    }

    @RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public String getAllUsers(Model model)
    {
//        List<String> listUsers = Collections.singletonList(restTemplateService.getUsers());
        List<UserDto> listUsers = restTemplateService.getUsers();
        model.addAttribute("users", listUsers);
        return "/list";
    }

    @RequestMapping(value = "/add",  method = RequestMethod.GET)
    public String addUserPage(){
        return "/add";
    }

    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.POST)
    public String addUser( @ModelAttribute("user") UserDto user,
                           @RequestParam("roleSet") List<String> rolesName) {
        ArrayList<RoleDto> role = new ArrayList<>();
        for (String string : rolesName){
            RoleDto role1 = restTemplateService.getRole(string);
            role.add(role1);
        }

        user.setRole(role);
        restTemplateService.createUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteUser(@PathVariable("id") long id
    ){
        restTemplateService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public String editUserPage(@PathVariable("id") long id, Model model){
        UserDto user = restTemplateService.userById(id);
        model.addAttribute("user", user);
        return "/edit";
    }

    @RequestMapping(value = "edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  method = {RequestMethod.PUT, RequestMethod.POST})
    public String editUser(
            @ModelAttribute("user") UserDto user,
            @RequestParam("roleSet") List<String> rolesName
    )
    {
        ArrayList<RoleDto> role = new ArrayList<>();
        for (String string : rolesName){
            RoleDto role1 = restTemplateService.getRole(string);
            role.add(role1);
        }

        user.setRole(role);
        restTemplateService.update(user);

        return "redirect:/";
    }
}
