package com.arthurdrabazha.openmind.controller.client;

import com.arthurdrabazha.openmind.dto.CreateUserDto;
import com.arthurdrabazha.openmind.dto.DeletePeriod;
import com.arthurdrabazha.openmind.dto.UpdateUserDto;
import com.arthurdrabazha.openmind.dto.UpdateUserPasswordDto;
import com.arthurdrabazha.openmind.exception.ServiceException;
import com.arthurdrabazha.openmind.model.User;
import com.arthurdrabazha.openmind.model.UserRole;
import com.arthurdrabazha.openmind.service.UserService;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Controller
@RequestMapping("/u")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAll(Model model) {

        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "/users/all";
    }

    @GetMapping("/{id}")
    public String getOne(@AuthenticationPrincipal User sessionUser, @PathVariable(value = "id") Long id, Model model) {

        User user = userService.findById(id);

        model.addAttribute("user", user);
        model.addAttribute("sessionUser", sessionUser);

        return "/users/index";
    }

    @GetMapping("/account")
    public String getAccountPage(@AuthenticationPrincipal User user, Model model) {

        User persistedUser = userService.findById(user.getId());
        model.addAttribute("user", persistedUser);

        return "/users/index";
    }

    @GetMapping("/update")
    public String getUpdateForm(Model model) {

        List<DeletePeriod> periods = Arrays.asList(DeletePeriod.values());

        model.addAttribute("periods", periods);

        return "users/update";
    }

    @GetMapping("/update-password")
    public String getUpdatePasswordForm() {
        return "users/update_password";
    }

    @PostMapping("/update")
    public String updateAccount(@AuthenticationPrincipal User user,
                                @Validated UpdateUserDto updateUserDto, RedirectAttributes redirectAttributes) {

        userService.update(user, updateUserDto);

        redirectAttributes.addFlashAttribute("message", "User account successfully updated");

        return "redirect:/u/";
    }

    @PostMapping("/update-password")
    public String updatePassword(@AuthenticationPrincipal User user,
                                 @Validated UpdateUserPasswordDto updateUserPasswordDto,
                                 RedirectAttributes redirectAttributes) {

        User persistedUser = userService.findById(user.getId());
        userService.updatePassword(persistedUser, updateUserPasswordDto);

        redirectAttributes.addFlashAttribute("message", "Password successfully updated");

        return "redirect:/u/";
    }

    @GetMapping("/new")
    public String create(Model model) {

        List<UserRole> roles = Arrays.asList(UserRole.values());
        List<DeletePeriod> periods = Arrays.asList(DeletePeriod.values());

        model.addAttribute("roles", roles);
        model.addAttribute("periods", periods);

        return "/users/create";
    }

    @PostMapping("/create")
    public String createUser(@Nullable @AuthenticationPrincipal User user,
                                @Validated CreateUserDto createUserDto, RedirectAttributes redirectAttributes) {

        // Should I place following logic in service?

        if (nonNull(user))
            if (user.getRole() != UserRole.ROLE_ADMIN && createUserDto.getRole() == UserRole.ROLE_ADMIN) {
                throw new ServiceException("You don't have permissions to create admin account");
            }

        if (isNull(user))
            createUserDto.setRole(UserRole.ROLE_USER);

        userService.create(createUserDto);

        redirectAttributes.addFlashAttribute("message", "User created");

        return "redirect:/login";
    }

    @PostMapping("/activate/{id}")
    public String activate(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        userService.activate(id);

        redirectAttributes.addFlashAttribute("message", "User activated");

        return "redirect:/u/" + id;
    }

    @PostMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        userService.deactivate(id);

        redirectAttributes.addFlashAttribute("message", "User deactivated");

        return "redirect:/u/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (userService.isLastAdmin()) {
            throw new ServiceException("There should be at least one admin");
        }

        userService.delete(id);
        redirectAttributes.addFlashAttribute("message", "User deleted");

        return "redirect:/u/";
    }

}
