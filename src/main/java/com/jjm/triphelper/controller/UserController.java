package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.UserAlreadyExistException;
import com.jjm.triphelper.controller.exceptions.UserNotFoundException;
import com.jjm.triphelper.entity.dto.UserDTO;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.dto.UserRepositoryDTO;
import com.jjm.triphelper.service.CryptoService;
import com.jjm.triphelper.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.security.InvalidParameterException;

@RestController
@RequestMapping(value = "api/user", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {

    @Resource private UserService userService;
    @Resource private UserRepositoryDTO userRepositoryDTO;
    @Resource private CryptoService cryptoService;

    @ApiOperation(value = "Sign In", notes = "<p>Sign In into the application</p>")
    @RequestMapping(value = "signIn", method = RequestMethod.GET )
    public ResponseEntity<UserDTO> signIn(@RequestParam(value = "email", required = true) final String email,
                                          @RequestParam(value = "password", required = true) final String password) {
        if (StringUtils.isEmpty(email))
            throw new InvalidParameterException("The parameter email must not be empty");
        if (StringUtils.isEmpty(password))
            throw new InvalidParameterException("The parameter password must not be empty");
        User user = userService.signIn(email, password);
        if (user == null)
            throw new UserNotFoundException(email);
        return new ResponseEntity<>(userRepositoryDTO.find(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Sign Up", notes = "<p>Sign Up into the application</p>")
    @RequestMapping(value = "signUp", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> signUp(@RequestParam(value = "email", required = true) final String email,
                                          @RequestParam(value = "name", required = true) final String name,
                                          @RequestParam(value = "password", required = true) final String password){
        if (StringUtils.isEmpty(email))
            throw new InvalidParameterException("The parameter email must not be empty");
        if (StringUtils.isEmpty(name))
            throw new InvalidParameterException("The parameter name must not be empty");
        if (StringUtils.isEmpty(password))
            throw new InvalidParameterException("The parameter password must not be empty");
        if (userService.findByEmail(email) != null)
            throw new UserAlreadyExistException(email);
        return new ResponseEntity<>(userRepositoryDTO.find(userService.signUp(email, name, password)), HttpStatus.CREATED);
    }
}
