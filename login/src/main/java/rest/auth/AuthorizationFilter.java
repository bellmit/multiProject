package rest.auth;

import domain.Role;
import domain.User;
import service.UserService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the resource class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<String> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<String> methodRoles = extractRoles(resourceMethod);

        String email = requestContext.getSecurityContext().getUserPrincipal().getName();
        User user = userService.findByEmail(email);
        List<String> userRoles = getRoleNames(user.getRoles());

        // Check if the user is allowed to execute the method
        // The method annotations override the class annotations
        boolean allowed;
        if (methodRoles.isEmpty()) {
            allowed = checkPermissions(classRoles, userRoles);
        } else {
            allowed = checkPermissions(methodRoles, userRoles);
        }

        if (!allowed) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    private List<String> getRoleNames(List<Role> roles) {
        List<String> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getName());
        }
        return roleNames;
    }

    // Extract the roles from the annotated element
    private List<String> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<>();
            } else {
                String[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private boolean checkPermissions(List<String> allowedRoles, List<String> userRoles) {
        // Check if the user contains one of the allowed roles
        // Throw an Exception if the user has not permission to execute the method
        boolean hasRole = false;
        if (!allowedRoles.isEmpty()) {
            for (String role : allowedRoles) {
                if (userRoles.contains(role)) {
                    hasRole = true;
                }
            }
            return hasRole;
        }
        return true;
    }
}
