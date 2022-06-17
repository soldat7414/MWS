package com.Soldat.MWS.services.controller_services;

import com.Soldat.MWS.entity.supporting_classes.Role;
import com.Soldat.MWS.entity.supporting_classes.User;
import com.Soldat.MWS.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private MailSender mailSender;

    public UserService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setActive(true);
        user.setRole(Role.ROLE_USER);
        user.setRole(Role.ROLE_ADMIN);
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if(StringUtils.hasLength(user.getEmail())&
        StringUtils.hasText(user.getEmail())){
            String message = String.format(
                    "Вітаю, %s! \n" +
                            "Ласкаво просимо до Вашого робочого місця у MyWorkSpace!\n" +
                            "Для підтвердження Вашої електронної адреси перейдіть за посиланням:\n" +
                            "http://localhost:8080/activate/%s",
                    user.getName(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if(user == null){
            return false;
        }

        user.setActivationCode(null);
//        user.setRole(Role.ROLE_USER);

        userRepo.save(user);

        return true;
    }
}
