package com.tmt.project.webnghenhac.service.impl;

import com.tmt.project.webnghenhac.domain.Account;
import com.tmt.project.webnghenhac.domain.Picture;
import com.tmt.project.webnghenhac.domain.Role;
import com.tmt.project.webnghenhac.repository.AccountRepository;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import com.tmt.project.webnghenhac.service.UserService;
import com.tmt.project.webnghenhac.service.request.ChangePasswordRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AccountRepository accountRepository;

    private final PictureRepository pictureRepository;

    public UserServiceImpl(AccountRepository accountRepository, PictureRepository pictureRepository) {
        this.accountRepository = accountRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Account updateUserById(Account account, Integer id) {
        Optional<Account> checkedUser = this.accountRepository.findById(id);
        if (!checkedUser.isPresent()){
            throw new RuntimeException("Id not found!");
        }
        Account realUser = checkedUser.get();
        realUser.setFirstname(account.getFirstname());
        realUser.setLastname(account.getLastname());
        realUser.setUsername(account.getUsername());
        this.accountRepository.save(realUser);
        return realUser;
    }

    @Override
    public List<Account> getAllAccount() {
        var accounts = this.accountRepository.findAll();
        return accounts;
    }

    @Override
    public Account getUserById(Integer id) {
        Optional<Account> checkedAccount = this.accountRepository.findById(id);
        if (!checkedAccount.isPresent()){
            throw new RuntimeException("Account not Found!");
        }
        Account realAccount = checkedAccount.get();
        return  realAccount;
    }

    @Override
    public Account updatePictureForUserById(MultipartFile file, Integer id) throws IOException {
        var checkedUser = this.accountRepository.findById(id);
        if (!checkedUser.isPresent()){
            throw new RuntimeException("id not found");
        }
        var realUser = checkedUser.get();

        String relativePath = System.getProperty("user.dir");
        System.out.println(relativePath);
        String savedFilePath = relativePath + "/Pictures/" + file.getOriginalFilename();
        File savedFile = new File(savedFilePath);
        file.transferTo(savedFile);

        // Tạo bản ghi media
        Picture newPicture = new Picture();
        newPicture.setPictureName(file.getOriginalFilename());
        newPicture.setPictureURL(savedFilePath);
        newPicture.setUploadDate(LocalDate.now());
        var savedPicture = this.pictureRepository.save(newPicture);
        realUser.setPicture(savedPicture);
        var savedUser = this.accountRepository.save(realUser);
        return savedUser;
    }

    @Override
    public Account updatePasswordAccountById(Integer id, ChangePasswordRequest passwordRequest) {
        Optional<Account> checkedAccount = this.accountRepository.findById(id);
        if (!checkedAccount.isPresent()){
            throw new RuntimeException("Account not Found!");
        }
        Account realAccount = checkedAccount.get();
        realAccount.setPassword(passwordRequest.getPassword());
        this.accountRepository.save(realAccount);
        return realAccount;
    }

    @Override
    public void deleteAccountById(Integer id) {
        Optional<Account> checkedAccount = this.accountRepository.findById(id);
        if (!checkedAccount.isPresent()){
            throw new RuntimeException("Account not Found!");
        }
        Account realAccount = checkedAccount.get();
        realAccount.setPicture(null);
        this.accountRepository.delete(realAccount);
    }

    @Override
    public void changeRoleForUserById(Integer id, Role role) {
        Optional<Account> checkedAccount = this.accountRepository.findById(id);
        if (!checkedAccount.isPresent()){
            throw new RuntimeException("Account not Found!");
        }
        Account realAccount = checkedAccount.get();
        realAccount.setRole(role);
        this.accountRepository.save(realAccount);
    }
}
