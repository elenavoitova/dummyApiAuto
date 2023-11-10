package utils;

import core.model.User;
import data.RuntimeData;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Randomizer {

    public String randomString(int length) {
        return randomString(length, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }

    public String randomString(int length, String scope) {
        if (length < 1 || scope == null || scope.length() == 0)
            return null;
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(scope.charAt(rnd.nextInt(scope.length())));
        return sb.toString();
    }

    public User randomUserFromPool() {
        List<User> list = RuntimeData.getUsersPool();
        return list.get(new Random().nextInt(list.size()));
    }

}
