package com.base.bookstore.domain.service;

import com.base.bookstore.domain.model.request.JwtRequest;
import com.base.bookstore.domain.model.request.JwtResponse;
import com.base.model.response.ServiceResponse;

public interface JwtService {
    ServiceResponse<JwtResponse> getToken(JwtRequest authenticationRequest) throws Exception;
}
