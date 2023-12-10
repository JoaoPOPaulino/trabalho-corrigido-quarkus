package br.unitins.topicos1.model;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.service.hash.HashService;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Usuario extends DefaultEntity {

    private String nome;
    private String login;
    private String senha;
    private Perfil perfil;

    @NotEmpty(message = "A lista de telefone não pode estar vazia")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_telefone", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_telefone"))
    private List<Telefone> listaTelefone;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_endereco", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_endereco"))
    private Endereco endereco;

    public Usuario() {
    }

    public Usuario(UsuarioDTO dto, HashService hashService) {
        this.nome = dto.nome();
        this.login = dto.login();
        this.senha = hashService.getHashSenha(dto.senha());
        this.perfil = Perfil.valueOf(dto.idPerfil());

        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            this.listaTelefone = dto.listaTelefone().stream()
                    .map(Telefone::new)
                    .collect(Collectors.toList());
        }

        if (dto.endereco() != null) {
            this.endereco = new Endereco(dto.endereco());
        }
    }

    public void atualizarComDTO(UsuarioDTO dto, HashService hashService) {
        this.nome = dto.nome();
        this.login = dto.login();

        if (dto.senha() != null && !dto.senha().isEmpty()) {
            this.senha = hashService.getHashSenha(dto.senha());
        }
        this.perfil = Perfil.valueOf(dto.idPerfil());

        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            this.listaTelefone = dto.listaTelefone().stream()
                    .map(Telefone::new)
                    .collect(Collectors.toList());
        }

        if (dto.endereco() != null)

        {
            this.endereco = new Endereco(dto.endereco());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Telefone> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<Telefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
