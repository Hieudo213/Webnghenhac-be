package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Account;
import com.tmt.project.webnghenhac.domain.Role;
import com.tmt.project.webnghenhac.service.request.ChangePasswordRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public Account updateUserById(Account account, Integer id);

    List<Account> getAllAccount();

    public Account getUserById(Integer id);

    public Account updatePictureForUserById(MultipartFile file, Integer id) throws IOException;

    Account updatePasswordAccountById(Integer id, ChangePasswordRequest passwordRequest);

    void deleteAccountById(Integer id);
    void changeRoleForUserById(Integer id, Role role);
}
