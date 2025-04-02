package Controller10;

import DTO.Request.UserRequest;
import DTO.Response.UserResponse;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserResponse addUser(@RequestBody UserRequest userRequest) {
        return userService.add(userRequest);
    }

    @GetMapping("/users")
    public List<UserResponse> getAll() {
        return userService.all();
    }

    @PutMapping("/users/{id}")
    public UserResponse getAll(@RequestBody UserRequest userRequest, @PathVariable int id) {
        return userService.fix(userRequest,id);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody UserRequest userRequest){
        return userService.login(userRequest);
    }

    @PostMapping("/register")
    public UserResponse handleLogin(@RequestBody UserRequest userRequest){
        return userService.add(userRequest);
    }





}
