package util;

import domain.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {

    private RoleConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> roleArrayToStringArray(List<Role> roles) {
        List<String> stringRoles = new ArrayList<>();
        for (Role role : roles)
            stringRoles.add(role.getName());
        return stringRoles;
    }
}
