package com.example.cookunityuserservice.controller;

import com.example.cookunityuserservice.dtos.ResponseObject;
import com.example.cookunityuserservice.mapper.DTO.SecretQuestionDTO;
import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.mapper.DTO.UserListDTO;
import com.example.cookunityuserservice.model.User;
import com.example.cookunityuserservice.resource.General;
import com.example.cookunityuserservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/user";

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/status")
    public String status(){
        return "User Controller is working";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseObject<UserListDTO>> getUsers(){
        ResponseObject<UserListDTO> object = new ResponseObject<>();
        try {
            List<UserDTO> users = userService.getAllUser();
            object.setData(Collections.singletonList(new UserListDTO(users)));
            object.setMessage(General.RETRIEVED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<ResponseObject<UserDTO>> getUser(@PathVariable("id") Long id){
        ResponseObject<UserDTO> object = new ResponseObject<>();
        try {
            Optional<UserDTO> user = userService.getUser(id);
            object.setData(Collections.singletonList(user.get()));
            object.setMessage(General.RETRIEVED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable("id") Long id){
        ResponseObject object = new ResponseObject();
        try {
            userService.deleteUser(id);
            object.setValid(true);
            object.setMessage(General.DELETED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObject<UserDTO>> createUser(@RequestBody User user){
        ResponseObject<UserDTO> object = new ResponseObject<>();
        try {
            UserDTO userDTO = userService.saveUser(user);
            object.setMessage(General.CREATED);
            object.setData(Collections.singletonList(userDTO));
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<ResponseObject<UserDTO>> update(@RequestBody User user, @PathVariable("id") Long id){
        ResponseObject<UserDTO> object = new ResponseObject<>();
        try {
            Optional<UserDTO> userDTO = userService.updateUser(user, id);
            object.setMessage(General.UPDATED);
            object.setData(Collections.singletonList(userDTO.get()));
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }
}
