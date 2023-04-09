package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Account;
import com.tmt.project.webnghenhac.domain.Role;
import com.tmt.project.webnghenhac.repository.AccountRepository;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import com.tmt.project.webnghenhac.security.JwtService;
import com.tmt.project.webnghenhac.service.request.AuthenticateRequest;
import com.tmt.project.webnghenhac.service.request.AuthenticationResponse;
import com.tmt.project.webnghenhac.service.request.RegisterRequest;
import com.tmt.project.webnghenhac.service.request.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final PictureRepository pictureRepository;

    public RegisterResponse registerForUser(RegisterRequest request) {
        var checkedEmail = this.repository.findByEmail(request.getEmail());
        var defaultPicture = this.pictureRepository.findById(35);
        if (!checkedEmail.isPresent()){
            var user = Account.builder()
                    .lastname(request.getLastname())
                    .firstname(request.getFirstname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .picture(defaultPicture.get())
                    .build();
            repository.save(user);

            return RegisterResponse.builder()
                    .message("Đăng ký tài khoản thành công !")
                    .build();
        }
        throw new RuntimeException("Email is already exits!");
    }

    public RegisterResponse registerForAdmin(RegisterRequest request) {
        var checkedEmail = this.repository.findByEmail(request.getEmail());
        var defaultPicture = this.pictureRepository.findById(36);
        if (!checkedEmail.isPresent()){
            var user = Account.builder()
                    .lastname(request.getLastname())
                    .firstname(request.getFirstname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .picture(defaultPicture.get())
                    .build();
            repository.save(user);
            return RegisterResponse.builder()
                    .message("Đăng ký tài khoản thành công !")
                    .build();
        }
        throw new RuntimeException("Email is already exist!");
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .role(user.getRole())
                .build();
    }
}
