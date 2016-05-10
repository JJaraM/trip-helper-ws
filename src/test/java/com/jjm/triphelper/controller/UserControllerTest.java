package com.jjm.triphelper.controller;

import com.jjm.triphelper.domain.ErrorModel;
import com.jjm.triphelper.entity.dto.UserDTO;
import com.jjm.triphelper.entity.jpa.UserJPA;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class UserControllerTest extends AbstractIntegrationTest {

    @Resource private UserController userController;
    @Mock private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(userController, "userService", userService);
    }

    @Test
    public void signIn_expectedInvalidParameterException() {
        ResponseEntity<ErrorModel> emailError = getEntity("api/user/signIn", ErrorModel.class, signIn_invalidParameterException_getParameters("", ""));
        Assert.assertEquals(emailError.getStatusCode().value(), 409);
        Assert.assertEquals(emailError.getBody().getMessage(), "The parameter email must not be empty");
        ResponseEntity<ErrorModel> passwordError = getEntity("api/user/signIn", ErrorModel.class, signIn_invalidParameterException_getParameters("email", ""));
        Assert.assertEquals(passwordError.getStatusCode().value(), 409);
        Assert.assertEquals(passwordError.getBody().getMessage(), "The parameter password must not be empty");
    }

    @Test
    public void signIn_expectedUserNotFoundException() {
        ResponseEntity<ErrorModel> error = getEntity("api/user/signIn", ErrorModel.class, signIn_invalidParameterException_getParameters("email1", "password1"));
        Mockito.when(userService.signIn("email1", "password1")).thenReturn(null);
        Assert.assertEquals(error.getStatusCode().value(), 404);
        Assert.assertEquals(error.getBody().getMessage(), "The user email1 does not exist");
    }

    @Test
    public void signIn_expectedUserDTO() {
        User user = new UserJPA();
        user.setName("name");
        user.setEmail("email");
        user.setPassword("password");
        Mockito.when(userService.signIn(user.getEmail(), user.getPassword())).thenReturn(user);
        ResponseEntity<UserDTO> responseEntity = getEntity("api/user/signIn", UserDTO.class, signIn_invalidParameterException_getParameters("email", "password"));
        Assert.assertEquals(responseEntity.getStatusCode().value(), 200);
        Assert.assertEquals(responseEntity.getBody().getEmail(), user.getEmail());
        Assert.assertEquals(responseEntity.getBody().getName(), user.getName());
    }

    @Test
    public void signUp_expectedInvalidParameterException() {
        ResponseEntity<ErrorModel> emailError = getEntity("api/user/signUp", ErrorModel.class, signUp_invalidParameterException_getParameters("", "", ""));
        Assert.assertEquals(emailError.getStatusCode().value(), 409);
        Assert.assertEquals(emailError.getBody().getMessage(), "The parameter email must not be empty");
        ResponseEntity<ErrorModel> nameError = getEntity("api/user/signUp", ErrorModel.class, signUp_invalidParameterException_getParameters("email", "", ""));
        Assert.assertEquals(nameError.getStatusCode().value(), 409);
        Assert.assertEquals(nameError.getBody().getMessage(), "The parameter name must not be empty");
        ResponseEntity<ErrorModel> passwordError = getEntity("api/user/signUp", ErrorModel.class, signUp_invalidParameterException_getParameters("email", "name", ""));
        Assert.assertEquals(passwordError.getStatusCode().value(), 409);
        Assert.assertEquals(passwordError.getBody().getMessage(), "The parameter password must not be empty");
    }

    @Test
    public void signUp_expectedUserAlreadyExistException() {
        User user = new UserJPA();
        user.setEmail("email3");
        user.setName("name3");
        user.setPassword("password3");
        Mockito.when(userService.findByEmail(user.getEmail())).thenReturn(user);
        ResponseEntity<ErrorModel> error = getEntity("api/user/signUp", ErrorModel.class, signUp_invalidParameterException_getParameters(user.getEmail(), user.getName(), user.getPassword()));
        Assert.assertEquals(error.getStatusCode().value(), 409);
        Assert.assertEquals(error.getBody().getMessage(), "The user email3 already exist.");
    }

    @Test
    public void signUp_expectedUserDTO() {
        User user = new UserJPA();
        user.setEmail("email2");
        user.setName("name2");
        user.setPassword("password2");
        Mockito.when(userService.signUp(user.getEmail(), user.getName(), user.getPassword())).thenReturn(user);
        ResponseEntity<UserDTO> responseEntity = getEntity("api/user/signUp", UserDTO.class, signUp_invalidParameterException_getParameters(user.getEmail(), user.getName(), user.getPassword()));
        Assert.assertEquals(responseEntity.getStatusCode().value(), 201);
        Assert.assertEquals(responseEntity.getBody().getEmail(), user.getEmail());
        Assert.assertEquals(responseEntity.getBody().getName(), user.getName());
    }

    public Map<String, Object> signIn_invalidParameterException_getParameters(String email, String password) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("password", password);
        return parameters;
    }

    public Map<String, Object> signUp_invalidParameterException_getParameters(String email, String name, String password) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("name", name);
        parameters.put("password", password);
        return parameters;
    }
}
