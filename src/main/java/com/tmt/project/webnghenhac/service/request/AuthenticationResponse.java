package com.tmt.project.webnghenhac.service.request;

import com.tmt.project.webnghenhac.domain.Picture;
import com.tmt.project.webnghenhac.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Picture picture_id;
    private String token;
    private Role role;
}
