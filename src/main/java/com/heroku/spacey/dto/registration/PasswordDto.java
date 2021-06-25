package com.heroku.spacey.dto.registration;

import com.heroku.spacey.utils.validators.PasswordConstraint;
import lombok.Data;

@Data
public class PasswordDto {
    private String email;

    private String oldPassword;

    @PasswordConstraint
    private String newPassword;

    @PasswordConstraint
    private String newPasswordRepeat;
}
