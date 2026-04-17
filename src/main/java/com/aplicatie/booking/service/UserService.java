package com.aplicatie.booking.service;

import com.aplicatie.booking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.aplicatie.booking.repo.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository =userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Integer id){
        Optional<User> tempUser = userRepository.findById(id);

        if(tempUser.isEmpty()){
            throw new RuntimeException("USER_NOT_FOUND");
        }

        return tempUser.get();
    }

    public User registerUser(User user){
        String email = user.getEmail().toLowerCase();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("EMAIL_ALREADY_EXISTS");
        }


        if (!email.endsWith("@gmail.com")) {
            throw new RuntimeException("INVALID_EMAIL_DOMAIN");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }


    public User authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("USER_NOT_FOUND");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("INVALID_PASSWORD");
        }

        return user;
    }
}
