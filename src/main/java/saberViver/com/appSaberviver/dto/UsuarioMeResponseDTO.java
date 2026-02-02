package saberViver.com.appSaberviver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import saberViver.com.appSaberviver.entidades.user.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMeResponseDTO {

        private String login;
        private Role role;
        private Long voluntarioId;
        private Long administradorId;
        private String nome;
}
