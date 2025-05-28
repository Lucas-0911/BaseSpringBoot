package com.company.model.form.auth;

import com.company.model.validation.auth.OldPasswordCorrect;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class ChangePasswordForm {

    @OldPasswordCorrect
    @NotBlank
    private String oldPassword;

    @NotBlank
    @Length(min = 6, max = 20)
    private String newPassword;

}
