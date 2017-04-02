package appfen.kphai.helpinglao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by K'Phai on 10/26/2014.
 */
public class fragmentB extends Fragment {

    private CallTABLE callTABLE;
    private CustomAdapter customAdapter;


    List<HashMap<String, String>> call;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//
//        ListView listView = (ListView) getView().findViewById(R.id.listCall);
//        callTABLE = new CallTABLE(this.getActivity());
//
//        call = callTABLE.ReadAllCall();
//
//        customAdapter = new CustomAdapter(this.getActivity(), call, "B");
//        listView.setAdapter(customAdapter);


    }


}//Main Class
