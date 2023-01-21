package com.library.bookpad;

import com.library.bookpad.model.DeleteUserResponse;
import com.library.bookpad.model.User;
import com.library.bookpad.model.UserResponse;
import com.library.bookpad.model.UsersResponse;
import com.library.bookpad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public UserResponse getOneUserInfo(@PathVariable(value = "id") int userId) {
        User user = new User(1, "Aybüke");
        UserResponse userResponse = new UserResponse(null, "Kullanici bulunamadi");
        if (userId == user.id) {
            userResponse.data = user;
            userResponse.message = "kullanici basarili sekilde bulundu.";
        }
        return userResponse;
    }

    @GetMapping("/user")
    public UsersResponse getUsers() {
        List<User> userList = userRepository.findAll();
        UsersResponse usersResponse = new UsersResponse(userList, "İşlem başarılı.", userList.size());
        return usersResponse;

    }

    @PostMapping("/user")
    public UserResponse createNewUser(@RequestBody User user) {
        UserResponse userResponse = new UserResponse(user, "Kullanici basarili sekilde olusturuldu.");
        return userResponse;
    }

    @PutMapping("/user/{id}")
    public UserResponse updateUser(@PathVariable(value = "id") int userId,
                                   @RequestBody User user) {
        User userDb = new User(2, "Resul", "Silay", "rs@gmail.com", "456789", "123", 27, "man");
        UserResponse userResponse = new UserResponse(null, "Kullanici bulunamadi");
        if (userDb.id == userId) {
            userDb.phoneNumber = user.phoneNumber;
            userResponse.data = userDb;
            userResponse.message = "Başarılı şekilde guncellendi";
        }
        return userResponse;
    }

    @DeleteMapping("/user/{id}")
    public DeleteUserResponse deleteUser(@PathVariable(value = "id") int userId) {
        User userDb = new User(3, "Resul", "Silay", "rs@gmail.com", "456789", "123", 27, "man");
        DeleteUserResponse deleteUserResponse = new DeleteUserResponse("Kullanici silinemedi.");
        if (userDb.id == userId) {
            deleteUserResponse.setMessage("Kullanici basarili sekilde silindi");
        }
        return deleteUserResponse;
    }

}