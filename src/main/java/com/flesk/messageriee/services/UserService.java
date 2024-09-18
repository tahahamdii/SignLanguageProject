package com.flesk.messageriee.services;

import com.flesk.messageriee.models.Contact;
import com.flesk.messageriee.models.Status;
import com.flesk.messageriee.models.User;
import com.flesk.messageriee.repositories.ContactRepo;
import com.flesk.messageriee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private ContactRepo contactRepo;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isResetCodeValid(User user, String resetCode) {
        return user.getResetCode() != null && user.getResetCode().equals(resetCode);
    }
    public List<User> findByUsernameContaining(String username) {
        return userRepository.findByUsernameContaining(username);
    }
    public List<Contact> searchContactsByName(String name) {
        return contactRepo.findByNameContainingIgnoreCase(name);
    }
    public void processForgotPassword(User user) {
        String resetCode = generateResetCode();

        user.setResetCode(resetCode);
        userRepository.save(user);

        String emailContent = "Bonjour " + user.getUsername() + ",\n\n"
                + "Vous avez demandé une réinitialisation de mot de passe. Votre code de réinitialisation est :\n"
                + resetCode + "\n\n"
                + "Veuillez utiliser ce code pour réinitialiser votre mot de passe.\n\n"
                + "Cordialement,\nVotre équipe de support";

        // Envoyer l'e-mail de réinitialisation
        emailService.sendEmail(user.getEmail(), "Réinitialisation de mot de passe", emailContent);
    }

    private String generateResetCode() {
        // Générer un code aléatoire à 4 chiffres
        Random random = new Random();
        int code = 1000 + random.nextInt(9000); // Assurez-vous que le code est dans la plage 1000-9999
        return String.valueOf(code);
    }


    public User findByResetCode(String resetCode) {
        return userRepository.findByResetCode(resetCode);
    }

    public void saveeUser(User user){
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }
    public void disconnect(User user){
        var storedUser = userRepository.findById(user.getId())
                .orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public String findUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getId(); // Assuming getId() returns the user ID as a String
        } else {
            return null;
        }
    }
    public Optional<String> getUsernameById(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(User::getUsername);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
