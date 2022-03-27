package com.base.bookstore.domain.controller;

import com.base.controller.GenericController;
import com.base.controller.Runner;
import com.base.model.response.EndpointResponse;
import com.base.bookstore.domain.model.request.JwtRequest;
import com.base.bookstore.domain.model.request.JwtResponse;
import com.base.bookstore.domain.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController extends GenericController<JwtService> {


    public JwtAuthenticationController(Runner<JwtService, JwtResponse> runner,
                                       JwtService service) {
        setRunner(runner);
        setServiceProvider(service);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<EndpointResponse<JwtResponse>> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        return run(service -> {
            try {
                return service.getToken(authenticationRequest);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
