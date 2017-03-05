package es.flaviojmend.buscalivros.entidade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by flavio on 04/03/17.
 */

public class Livro  implements Serializable {

    @JsonProperty("nome_livro")
    private String nomeLivro;

    @JsonProperty("autor")
    private String autor;

    @JsonProperty("nome_biblioteca")
    private String nomeBiblioteca;

    @JsonProperty("endereco_biblioteca")
    private String enderecoBiblioteca;

    @JsonProperty("telefone_biblioteca")
    private String telefoneBiblioteca;

    @JsonProperty("site_biblioteca")
    private String siteBiblioteca;

    private Localizacao localizacao;


    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNomeBiblioteca() {
        return nomeBiblioteca;
    }

    public void setNomeBiblioteca(String nomeBiblioteca) {
        this.nomeBiblioteca = nomeBiblioteca;
    }

    public String getEnderecoBiblioteca() {
        return enderecoBiblioteca;
    }

    public void setEnderecoBiblioteca(String enderecoBiblioteca) {
        this.enderecoBiblioteca = enderecoBiblioteca;
    }

    public String getTelefoneBiblioteca() {
        return telefoneBiblioteca;
    }

    public void setTelefoneBiblioteca(String telefoneBiblioteca) {
        this.telefoneBiblioteca = telefoneBiblioteca;
    }

    public String getSiteBiblioteca() {
        return siteBiblioteca;
    }

    public void setSiteBiblioteca(String siteBiblioteca) {
        this.siteBiblioteca = siteBiblioteca;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
