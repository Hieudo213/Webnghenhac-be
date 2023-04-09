package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Account;
import com.tmt.project.webnghenhac.service.UserService;
import com.tmt.project.webnghenhac.service.request.ChangePasswordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/public/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Integer id){
        Account account = this.userService.getUserById(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateUserById(@Valid @RequestBody Account account,
                                                  @PathVariable("id") Integer id)
    {
        Account user = this.userService.updateUserById(account,id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("update/picture/{id}")
    public ResponseEntity<Account> updatePictureForUserById(@RequestParam("newfile") MultipartFile file,
                                                            @PathVariable("id") Integer id) throws IOException {
        var user = this.userService.updatePictureForUserById(file, id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/password/{id}")
    public ResponseEntity<Account> updatePasswordAccountById(@PathVariable("id") Integer id,
                                                             @RequestBody ChangePasswordRequest passwordRequest){
        var user = this.userService.updatePasswordAccountById(id, passwordRequest);
        return ResponseEntity.ok(user);
    }
}
