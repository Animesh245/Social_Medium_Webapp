package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.enums.AccountStatus;
import com.animesh245.social_medium.enums.Role;
import com.animesh245.social_medium.repository.LocationRepo;
import com.animesh245.social_medium.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/auth")
public class AuthController
{
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final LocationRepo locationRepo;

    public AuthController(PasswordEncoder passwordEncoder, UserRepo userRepo, LocationRepo locationRepo)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.locationRepo = locationRepo;
    }

    private void generateUsers()
    {
        if(userRepo.findByEmailId("user@ex.com") == null)
        {
            var user = new User();
            user.setId(1L);
            user.setFullName("User");
            user.setUserName("user123");
            user.setEmailId("user@ex.com");
            user.setRole(Role.ROLE_USER);
            user.setLocation(locationRepo.findByLocationName("Dhaka"));
            user.setPassword(passwordEncoder.encode("1234"));
            user.setAccountStatus(AccountStatus.ACTIVE);
            user.setDateOfBirth(LocalDate.parse("1990-09-09"));
        }

        if(userRepo.findByEmailId("admin@ex.com") == null)
        {
            var admin = new User();
            admin.setId(1L);
            admin.setFullName("User");
            admin.setUserName("admin123");
            admin.setEmailId("admin@ex.com");
            admin.setRole(Role.ROLE_ADMIN);
            admin.setLocation(locationRepo.findByLocationName("Dhaka"));
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setAccountStatus(AccountStatus.ACTIVE);
            admin.setDateOfBirth(LocalDate.parse("1990-09-09"));
        }
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(name="error", required = false) String error)
    {
        generateUsers();
        model.addAttribute("error", error);
        return "main/navbar";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        if(authentication != null)
        {
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/auth/login";
    }

    @GetMapping("/403")
    public ModelAndView accessDeniedPage(Principal user)
    {
        ModelAndView mv = new ModelAndView();

        if (user != null)
        {
            mv.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else
        {
            mv.addObject("msg",
                    "You do not have permission to access this page!");
        }

        mv.setViewName("auth/403");
        return mv;
    }
}
