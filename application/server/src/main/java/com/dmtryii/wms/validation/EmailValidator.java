package com.dmtryii.wms.validation;

import com.dmtryii.wms.exception.InvalidEmailException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    public void validateEmail(String email) {
        if(!isValid(email)) {
            throw new InvalidEmailException("Invalid email: " + email);
        }
    }

    private boolean isValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
