package com.flesk.messageriee.services;
import com.flesk.messageriee.models.User;
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

    public void processForgotPassword(User user) {
        // Générer un code de réinitialisation à 4 chiffres
        String resetCode = generateResetCode();

        // Enregistrer le code de réinitialisation dans l'objet utilisateur ou dans une table de codes de réinitialisation
        user.setResetCode(resetCode);
        userRepository.save(user); // Assurez-vous de sauvegarder l'utilisateur avec le code de réinitialisation

        // Envoyer l'e-mail de réinitialisation contenant le code
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
}
