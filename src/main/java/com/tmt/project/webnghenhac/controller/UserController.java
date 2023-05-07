package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Account;
import com.tmt.project.webnghenhac.domain.Role;
import com.tmt.project.webnghenhac.service.UserService;
import com.tmt.project.webnghenhac.service.request.ChangePasswordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<Account>> getAllAccount(){
        var accounts = this.userService.getAllAccount();
        return ResponseEntity.ok(accounts);
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Integer id){
        this.userService.deleteAccountById(id);
        return ResponseEntity.ok("Xoa thanh cong !");
    }

    @PutMapping("/admin/update/role/{id}")
    public ResponseEntity<String> changeRoleById(@PathVariable("id") Integer id, @RequestParam(value = "Role") Role role){
        this.userService.changeRoleForUserById(id,role);
        return ResponseEntity.ok("Da Thay role thanh cong !");
    }
}
