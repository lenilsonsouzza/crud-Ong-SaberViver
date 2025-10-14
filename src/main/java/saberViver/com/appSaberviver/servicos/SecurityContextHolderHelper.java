package saberViver.com.appSaberviver.servicos;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import saberViver.com.appSaberviver.entidades.user.User;

public class SecurityContextHolderHelper {

    public static User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();

            // Quando está autenticado com seu próprio User
            if (principal instanceof User) {
                return (User) principal;
            }

            // Evita erro caso ainda seja "anonymousUser"
            if ("anonymousUser".equals(principal)) {
                return null;
            }
        }

        return null;
    }
}