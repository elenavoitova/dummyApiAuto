package data;

import core.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RuntimeData {

    private static final List<User> UsersPool = new ArrayList<>();

    public static List<User> getUsersPool() {
        if(UsersPool.size() == 0) {
            System.out.println("No users have been added yet");
        }
        return UsersPool.stream()
                .filter(candidate -> candidate.getId() != null)
                .collect(Collectors.toList());
    }

    public static void addUser(User user) {
        UsersPool.add(user);
    }
}
