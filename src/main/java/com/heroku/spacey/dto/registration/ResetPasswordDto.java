package com.heroku.spacey.dto.registration;

import com.heroku.spacey.utils.validators.PasswordConstraint;
import lombok.Data;

@Data
public class ResetPasswordDto {
    @PasswordConstraint
    private String password;

    @PasswordConstraint
    private String passwordRepeat;
}
