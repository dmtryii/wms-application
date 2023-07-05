package com.dmtryii.wms.validation;

import com.dmtryii.wms.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterValidator {
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;

    public void validate(RegisterRequest request) {
        emailValidator.validateEmail(request.getEmail());
        passwordValidator.validatePassword(request.getPassword());
    }
}
