import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    
    // Email validation pattern
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@(.+)$";
    
    // Phone number validation pattern (for simplicity, assuming 10 digits)
    private static final String PHONE_PATTERN = 
        "^[0-9]{10}$";
    
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    public static void validateEmail(String email) throws InvalidInputException {
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidInputException("Invalid email format: " + email);
        }
    }

    public static void validatePhone(String phoneNumber) throws Exception {
        Matcher matcher = phonePattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new InvalidInputException("Invalid phone number format: " + phoneNumber);
        }
    }
}
