package es.flaviojmend.buscalivros.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;

import java.io.IOException;

import es.flaviojmend.buscalivros.BookResultActivity;
import es.flaviojmend.buscalivros.R;
import es.flaviojmend.buscalivros.entidade.SearchResult;
import es.flaviojmend.buscalivros.rest.SearchService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by flavio on 04/03/17.
 */

public class SearchTask extends AsyncTask<String, String, SearchResult> {

    Activity mActivity;

    public SearchTask(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected SearchResult doInBackground(String... params) {

        String term = params[0];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://livros.lucasconceicao.com/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        SearchService service = retrofit.create(SearchService.class);

        Call<SearchResult> call = service.searchByTerm(term);

        try {
            SearchResult result = call.execute().body();
            Log.d("buscaLivro", result.getSearched_term());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SearchResult();
    }


    @Override
    protected void onPostExecute(SearchResult searchResult) {

        ((ActionProcessButton)mActivity.findViewById(R.id.btn_buscar)).setProgress(0);

        if(searchResult.getResult().size() > 0) {
            Intent intent = new Intent(mActivity, BookResultActivity.class);
            intent.putExtra("searchResult",searchResult);
            mActivity.startActivity(intent);

        } else {
            Toast.makeText(mActivity, R.string.nenhum_resultado_encontrado, Toast.LENGTH_LONG).show();
        }
    }
}
