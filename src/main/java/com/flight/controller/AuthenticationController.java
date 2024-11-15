package com.flight.controller;

import com.flight.dto.MessageDTO;
import com.flight.dto.ProductDto;
import com.flight.dto.SessionDTO;
import com.flight.dto.UserDTO;
import com.flight.dto.request.SignUpRequestDto;
import com.flight.dto.response.SignUpResponseDto;
import com.flight.exception.InvalidCredentialException;
import com.flight.exception.UserAlreadyExistsException;
import com.flight.model.User;
import com.flight.service.AuthenticationService;
import com.flight.utils.HttpUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private HttpUtils httpUtils;

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignUpResponseDto> userSignUp(
            @Valid @RequestBody SignUpRequestDto signUpRequestDto) throws Exception {
        return new ResponseEntity<>(authenticationService.userSignUp(signUpRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<SessionDTO> userSignIn(@Valid @RequestBody UserDTO user)
            throws UserAlreadyExistsException, InvalidCredentialException {
        SessionDTO sdt = authenticationService.userLogin(user);
        return new ResponseEntity<>(sdt, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageDTO> userLogout(@RequestParam(value = "key") String authKey)
            throws InvalidCredentialException {
        MessageDTO message = new MessageDTO();
        String msg = authenticationService.userLogout(authKey);
        message.setMessage(msg);
        message.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user)
            throws InvalidCredentialException {
        authenticationService.updateUser(user);
        return new ResponseEntity<>("User updated successfully...", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam Integer userid, @RequestParam String authKey)
            throws InvalidCredentialException {
        User u = authenticationService.deleteUser(userid, authKey);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PostMapping("/appoint")
    public ResponseEntity<User> appointNewAdmin(@RequestParam("email") String email,
                                                @RequestParam("code") String passcode) throws InvalidCredentialException {
        User user = authenticationService.makeUserAdmin(email, passcode);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<ProductDto> test(@PathVariable("id") String productId) {
        ResponseEntity<ProductDto> response = httpUtils.get(
                "https://fakestoreapi.com/products/" + productId, null, null, ProductDto.class);
        return ResponseEntity.ok(response.getBody());
    }
}
