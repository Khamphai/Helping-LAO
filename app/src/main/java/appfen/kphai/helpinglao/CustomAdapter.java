package appfen.kphai.helpinglao;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by K'Phai on 11/2/2014.
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    List<HashMap<String, String>> data;
    String nofrag;

    public CustomAdapter(Context context, List<HashMap<String, String>> datas, String nofrag) {
        this.context = context;
        this.data = datas;
        this.nofrag = nofrag;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view1 = null;


        if (nofrag.equals("A")) {
            view1 = inflater.inflate(R.layout.custom_listview, viewGroup, false);
            TextView txttopic = (TextView) view1.findViewById(R.id.txtShowTopic);


            txttopic.setText(data.get(i).get("topic"));
        }

        if (nofrag.equals("B")) {
            view1 = inflater.inflate(R.layout.call_listview, viewGroup, false);
            TextView txtname = (TextView) view1.findViewById(R.id.txtShowname);
            TextView txtnumber = (TextView) view1.findViewById(R.id.txtShownumber);

            txtname.setText(data.get(i).get("name_call"));
            txtnumber.setText(data.get(i).get("number_call"));
        }





        return view1;
    }
}
