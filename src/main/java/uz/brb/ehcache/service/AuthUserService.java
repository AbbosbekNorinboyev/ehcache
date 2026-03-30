package uz.brb.ehcache.service;

import uz.brb.ehcache.dto.request.LoginRequest;
import uz.brb.ehcache.dto.request.RegisterRequest;
import uz.brb.ehcache.dto.request.UpdatePasswordRequest;
import uz.brb.ehcache.dto.response.Response;

public interface AuthUserService {
    Response<?> register(RegisterRequest registerRequest);

    Response<?> login(LoginRequest loginRequest);

    Response<?> changePassword(UpdatePasswordRequest request);
}