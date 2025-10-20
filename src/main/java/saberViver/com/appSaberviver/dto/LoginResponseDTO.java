package saberViver.com.appSaberviver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import saberViver.com.appSaberviver.entidades.user.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private UserInfo user;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfo {
        private String login;
        private Role role;
    }
}