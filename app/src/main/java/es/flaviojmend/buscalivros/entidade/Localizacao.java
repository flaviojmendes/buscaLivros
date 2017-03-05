package es.flaviojmend.buscalivros.entidade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by flavio on 04/03/17.
 */
public class Localizacao  implements Serializable {

    private String lat;

    @JsonProperty("long")
    private String lon;


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
