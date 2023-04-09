package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Account;
import com.tmt.project.webnghenhac.service.request.ChangePasswordRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    public Account updateUserById(Account account, Integer id);

    public Account getUserById(Integer id);

    public Account updatePictureForUserById(MultipartFile file, Integer id) throws IOException;

    Account updatePasswordAccountById(Integer id, ChangePasswordRequest passwordRequest);
}
