package es.flaviojmend.buscalivros.entidade;

import java.io.Serializable;
import java.util.List;

/**
 * Created by flavio on 04/03/17.
 */

public class SearchResult implements Serializable {

    private String searched_term;
    private List<Livro> result;

    public String getSearched_term() {
        return searched_term;
    }

    public void setSearched_term(String searched_term) {
        this.searched_term = searched_term;
    }

    public List<Livro> getResult() {
        return result;
    }

    public void setResult(List<Livro> result) {
        this.result = result;
    }
}
