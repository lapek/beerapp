package pl.beerapp.security;

import org.mindrot.jbcrypt.BCrypt;

final class PasswordService {

    static String hashPassword(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt());
    }

    static boolean checkPassword(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }
}
