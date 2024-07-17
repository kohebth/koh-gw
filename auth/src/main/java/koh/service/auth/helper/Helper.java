package koh.service.auth.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static boolean isSisEmailValid(String email) {
        Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@sis\\.hust\\.edu\\.vn$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
