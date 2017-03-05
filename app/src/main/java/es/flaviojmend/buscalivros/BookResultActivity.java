package es.flaviojmend.buscalivros;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.Manifest;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.flaviojmend.buscalivros.adapter.ListAdapter;
import es.flaviojmend.buscalivros.entidade.Livro;
import es.flaviojmend.buscalivros.entidade.SearchResult;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Created by flavio on 04/03/17.
 */

public class BookResultActivity extends AppCompatActivity implements OnMapReadyCallback  {
    private GoogleMap mMap;


   GoogleMap map;
    SearchResult searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_location);


        searchResult = (SearchResult) getIntent().getSerializableExtra("searchResult");

        String results = getString(R.string.resultados_livro, searchResult.getSearched_term());

        TextView resultadosText = ((TextView)findViewById(R.id.resultados_livro));
        resultadosText.setText(results);


        sortLivros();

        final ListView list = (ListView) findViewById(R.id.list_livros);

        list.setAdapter(new ListAdapter(this, searchResult.getResult().toArray()));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Livro livro = (Livro) parent.getItemAtPosition(position);

                LatLng bookLocation = new LatLng(Double.parseDouble(livro.getLocalizacao().getLat()),
                        Double.parseDouble(livro.getLocalizacao().getLon()));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(bookLocation, 13));


            }

        });


        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
         MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





    }

    private void sortLivros() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        final Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        Collections.sort(searchResult.getResult(), new Comparator<Livro>(){
            public int compare(Livro l1, Livro l2) {
                return DistanceUtil.distance(location.getLatitude(), location.getLongitude(),
                        Double.parseDouble(l1.getLocalizacao().getLat()), Double.parseDouble(l1.getLocalizacao().getLon()),
                        "M") > DistanceUtil.distance(location.getLatitude(), location.getLongitude(),
                        Double.parseDouble(l1.getLocalizacao().getLat()), Double.parseDouble(l1.getLocalizacao().getLon()),
                        "M") ? 1 : 0;


            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        boolean success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.map_style));
        loadPlace();
    }

    private void loadPlace() {

        this.map.setMyLocationEnabled(true);

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());

        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13));

        for(Livro livro : searchResult.getResult()) {
            LatLng bookLocation = new LatLng(Double.parseDouble(livro.getLocalizacao().getLat()),
                    Double.parseDouble(livro.getLocalizacao().getLon()));

            map.addMarker(new MarkerOptions()
                    .title(livro.getNomeLivro())
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                    .snippet(livro.getNomeBiblioteca() + " - " + livro.getEnderecoBiblioteca())
                    .position(bookLocation));
        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
