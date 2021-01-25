package uz.themind.springauthentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.themind.springauthentication.model.request.RegistrationRequest;
import uz.themind.springauthentication.model.response.ResponseData;
import uz.themind.springauthentication.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/registration")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
        ResponseData<?> response = authService.register(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<?> confirm(@RequestParam(name = "token") String token) {
        ResponseData<?> response = authService.confirmToken(token);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
