package appfen.kphai.helpinglao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by K'Phai on 10/26/2014.
 */
public class fragmentA extends Fragment {
    private InfoTABLE objInfoTable;
    private CustomAdapter customAdapter;


    List<HashMap<String, String>> topics;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }//onCreate


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ListView listtopic = (ListView) getView().findViewById(R.id.listTopic);
        objInfoTable = new InfoTABLE(this.getActivity());

        topics = objInfoTable.ReadAllTopic();

        customAdapter = new CustomAdapter(this.getActivity(), topics, "A");
        listtopic.setAdapter(customAdapter);

        listtopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(),"Click on " + i , Toast.LENGTH_LONG).show();


                String strinfo = new String(topics.get(i).get("info"));

                Intent intent = new Intent(getActivity(), ShowInfo_activity.class);
                intent.putExtra("info", strinfo);
                startActivity(intent);


            }
        });

    }
}// Main Class
