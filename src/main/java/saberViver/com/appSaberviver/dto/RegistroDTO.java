package saberViver.com.appSaberviver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import saberViver.com.appSaberviver.entidades.user.Papeis;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {
private String login;
private String senha;
private Papeis papel;

}