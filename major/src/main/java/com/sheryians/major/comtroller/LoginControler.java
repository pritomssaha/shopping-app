package com.sheryians.major.comtroller;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginControler {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        GlobalData.cart.clear();
        return "login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute ("user") User user, HttpServletRequest httpServletRequest) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
        httpServletRequest.login(user.getEmail(), password);
        return "redirect:/";
    }


//    @PostMapping("/register")
//    public String postRegisterAdmin(@ModelAttribute ("user") User user, HttpServletRequest httpServletRequest) throws ServletException {
//        String password = user.getPassword();
//        user.setPassword(bCryptPasswordEncoder.encode(password));
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleRepository.findById(1).get());
//        user.setRoles(roles);
//        userRepository.save(user);
//        httpServletRequest.login(user.getEmail(), password);
//        return "redirect:/";
//    }


}
