package es.flaviojmend.buscalivros.rest;

import es.flaviojmend.buscalivros.entidade.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface SearchService {

    @GET(" search/{term}")
    Call<SearchResult> searchByTerm(@Path("term") String term);

}
