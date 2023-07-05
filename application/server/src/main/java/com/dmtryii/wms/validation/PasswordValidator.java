package com.dmtryii.wms.validation;

import com.dmtryii.wms.exception.InvalidPasswordException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator {
    /*
        At least one upper case English letter, (?=.*?[A-Z])
        At least one lower case English letter, (?=.*?[a-z])
        At least one digit, (?=.*?[0-9])
        At least one special character, (?=.*?[#?!@$%^&*-])
        Minimum eight in length .{8,} (with the anchors)
     */
    private static final String PASSWORD_PATTERN =
            "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    public void validatePassword(String password) {
        if(!isValid(password)) {
            throw new InvalidPasswordException("Invalid password");
        }
    }

    private boolean isValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
