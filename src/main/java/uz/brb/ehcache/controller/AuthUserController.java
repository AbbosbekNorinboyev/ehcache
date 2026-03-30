package uz.brb.ehcache.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.brb.ehcache.dto.request.LoginRequest;
import uz.brb.ehcache.dto.request.RegisterRequest;
import uz.brb.ehcache.dto.request.UpdatePasswordRequest;
import uz.brb.ehcache.dto.response.Response;
import uz.brb.ehcache.service.AuthUserService;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/register")
    public Response<?> register(@RequestBody RegisterRequest registerRequest) {
        return authUserService.register(registerRequest);
    }

    @PostMapping("/login")
    public Response<?> login(@RequestBody LoginRequest loginRequest) {
        return authUserService.login(loginRequest);
    }

    @PutMapping("/change-password")
    public Response<?> changePassword(@RequestBody UpdatePasswordRequest request) {
        return authUserService.changePassword(request);
    }
}