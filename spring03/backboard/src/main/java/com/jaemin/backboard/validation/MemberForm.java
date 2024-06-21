package com.jaemin.backboard.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @Size(min = 4, max = 40)
    private String username;

    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다")
    private String password1;

    @NotBlank(message = "비밀번호 확인은 필수입니다")
    private String password2;


}
