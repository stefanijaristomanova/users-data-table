package com.pvmeinster.users.controller;

import com.pvmeinster.users.dto.User;
import com.pvmeinster.users.dto.request.AddUserRequest;
import com.pvmeinster.users.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("v1/user-service/users")
@Api(value = "Providing operations for the user entity")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Find all users", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users successfully found"),
            @ApiResponse(code = 500, message = "Internal server error while fetching all users")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @ApiOperation(value = "Find a user by id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully found"),
            @ApiResponse(code = 404, message = "The user you were trying to retrieve was not found")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User response = userService.findUserById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Remove an user ", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User successfully removed"), @ApiResponse(code = 500, message = "Problem while removing the user")})
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> removeUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.removeUser(id));
    }



    @ApiOperation(value = "Add an user", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully created"),
            @ApiResponse(code = 500, message = "Problem while creating the user")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
        User response = userService.addUser(addUserRequest);
        if (response != null) {
            return ResponseEntity.created(URI.create("")).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(User.builder().build());
        }
    }

}
