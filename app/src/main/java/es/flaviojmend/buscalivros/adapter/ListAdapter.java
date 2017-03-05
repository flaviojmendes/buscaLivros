package es.flaviojmend.buscalivros.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import es.flaviojmend.buscalivros.R;
import es.flaviojmend.buscalivros.entidade.Livro;



/**
 * Created by flavio on 04/03/17.
 */

public class ListAdapter extends ArrayAdapter<Object> {
    private final Context context;
    private final Object[] values;

    public ListAdapter(Context context, Object[] values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        firstLine.setText(((Livro) values[position]).getNomeLivro());
        secondLine.setText(((Livro)values[position]).getNomeBiblioteca());


        return rowView;
    }

    public Object getItem(int position){
        return values[position] ;
    }
}