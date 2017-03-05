package es.flaviojmend.buscalivros;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;

import es.flaviojmend.buscalivros.task.SearchTask;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int RC_LOCATION_PERM = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] perms = {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.permissao_geolocalizacao),
                    RC_LOCATION_PERM, perms);
        }

        final ActionProcessButton searchButton = (ActionProcessButton) findViewById(R.id.btn_buscar);
        searchButton.setOnClickListener(this);

        final EditText inputSearch = (EditText) findViewById(R.id.input_searched_term);

        inputSearch.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    searchButton.performClick();
                    return true;
                }
                return false;
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_buscar) {

            ((ActionProcessButton)v).setMode(ActionProcessButton.Mode.ENDLESS);
            ((ActionProcessButton)v).setProgress(1);

            EditText searchTermInput = (EditText) findViewById(R.id.input_searched_term);
            if(searchTermInput.getText() == null || searchTermInput.getText().toString().trim().equals("")) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.obrigatorio), Toast.LENGTH_SHORT).show();
                return;
            }
            EditText editText = (EditText) findViewById(R.id.input_searched_term);

            SearchTask searchTask = new SearchTask(this);
            searchTask.execute(editText.getText().toString());

        }
    }
}
