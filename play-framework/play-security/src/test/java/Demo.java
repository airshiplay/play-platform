import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author airlenet
 * @version 2018-05-07
 */
public class Demo {
    public static void main(String []args) throws NoSuchAlgorithmException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8, SecureRandom.getInstanceStrong());
        SecureRandom.getSeed(16);
        System.out.println(passwordEncoder.encode("123456"));
        BCrypt.gensalt();
        boolean matches = passwordEncoder.matches("123456", "$P$BtDqwDF3JMs3I4Liio81xZnYWtQ51Z.");
        System.out.println(matches);
    }
}
