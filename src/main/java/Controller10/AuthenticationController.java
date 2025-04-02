package Controller10;

import DTO.Request.UserRequest;
import DTO.Response.TokenResponse;
import Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login1")
    public TokenResponse login1(@RequestBody UserRequest userRequest) {
        return authenticationService.login(userRequest);
    }
}
