package saberViver.com.appSaberviver.servicos;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.CadastroVoluntarioDTO;
import saberViver.com.appSaberviver.dto.VoluntarioDTO;
import saberViver.com.appSaberviver.entidades.Administrador;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.entidades.Voluntario;
import saberViver.com.appSaberviver.entidades.user.Role;
import saberViver.com.appSaberviver.entidades.user.User;
import saberViver.com.appSaberviver.repositories.AtividadeRepositorio;
import saberViver.com.appSaberviver.repositories.UserRepositorio;
import saberViver.com.appSaberviver.repositories.VoluntarioRepositorio;
import saberViver.com.appSaberviver.servicos.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoluntarioServico {


    private final VoluntarioRepositorio voluntarioRepositorio;
    private final AtividadeRepositorio atividadeRepositorio;
    private final UserRepositorio userRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public VoluntarioDTO findByCpf(String cpf) {
        Voluntario professor = voluntarioRepositorio.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("CPF não encontrado"));
        return new VoluntarioDTO(professor);
    }

    @Transactional(readOnly = true)
    public List<Voluntario> buscarPorNome(String nome) {
        return voluntarioRepositorio.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public Page<VoluntarioDTO> findALL(Pageable pageable) {

        Page<Voluntario> result = voluntarioRepositorio.findAll(pageable);
        return result.map(x -> new VoluntarioDTO(x));
    }

    @Transactional
    public VoluntarioDTO cadastrarVoluntario(CadastroVoluntarioDTO dto) {

        if (userRepositorio.findBylogin(dto.getLogin()) != null) {
            throw new IllegalArgumentException("Login já existente!");
        }

        User userLogado = SecurityContextHolderHelper.getUser();



        if (userLogado.getRoler() != Role.ADM && userLogado.getRoler() != Role.ADM_MASTER) {
            throw new AccessDeniedException("Não autorizado a criar voluntário");
        }

        User user = new User(dto.getLogin(), passwordEncoder.encode(dto.getSenha()), Role.VOLUNTARIO);
        userRepositorio.save(user);


        Voluntario volunt = new Voluntario();

        volunt.setNome(dto.getNome());
        volunt.setSobreNome(dto.getSobreNome());
        volunt.setCpf(dto.getCpf());
        volunt.setTelefone(dto.getTelefone());
        volunt.setEmail(dto.getEmail());
        volunt.setDataNascimento(dto.getDataNascimento());
        volunt.setAreaAtuacao(dto.getAreaAtuacao());
        volunt.setUser(user);

        if (dto.getAtividade() != null && !dto.getAtividade().isEmpty()) {
            List<Atividade> atividades = atividadeRepositorio.findAllById(dto.getAtividade());
            volunt.getAtividades().addAll(atividades);
        }

        voluntarioRepositorio.save(volunt);

        return new VoluntarioDTO(volunt);
    }


    @Transactional
    public VoluntarioDTO atualizar(long id, VoluntarioDTO dto) {
        try {


            Voluntario entidade = voluntarioRepositorio.getReferenceById(id);

            copiarDtoparaEntidade(dto, entidade);

            entidade = voluntarioRepositorio.save(entidade);
            return new VoluntarioDTO(entidade);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Voluntario não encontrado");
        }
    }

    public void deletar(Long id) {
        Voluntario professor = voluntarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Voluntario não encontrado"));

        voluntarioRepositorio.delete(professor);
    }

    private void copiarDtoparaEntidade(VoluntarioDTO dto, Voluntario entidade) {
        entidade.setNome(dto.getNome());
        entidade.setSobreNome(dto.getSobreNome());
        entidade.setCpf(dto.getCpf());
        entidade.setEmail(dto.getEmail());
        entidade.setDataNascimento(dto.getDataNascimento());
        entidade.setAreaAtuacao(dto.getAreaAtuacao());
        entidade.setTelefone(dto.getTelefone());


        if (dto.getAtividade() != null && !dto.getAtividade().isEmpty()) {
            List<Atividade> atividades = atividadeRepositorio.findAllById(dto.getAtividade());
            entidade.getAtividades().addAll(atividades);
        }

    }


}