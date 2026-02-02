package saberViver.com.appSaberviver.dto;

import lombok.*;
import saberViver.com.appSaberviver.entidades.user.Role;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMeResponseDTO {

        private String login;
        private Role role;
        private String nome;
        private String email;
        private Long id;
}
