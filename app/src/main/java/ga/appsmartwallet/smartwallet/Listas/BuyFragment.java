package ga.appsmartwallet.smartwallet.Listas;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ga.appsmartwallet.smartwallet.Adapters.AdapterItem;
import ga.appsmartwallet.smartwallet.Modelos.ModeloItem;
import ga.appsmartwallet.smartwallet.R;

public class BuyFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<ModeloItem> modeloItemArrayList = new ArrayList<>();
    AdapterItem adapterFeed;
    View view;

    public BuyFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lista_content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewLista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapterFeed = new AdapterItem(getContext(), modeloItemArrayList);
        recyclerView.setAdapter(adapterFeed);

        populateRecyclerView();

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void populateRecyclerView() {

        double a = 222.55;

        ModeloItem modeloItem= new ModeloItem(1,"Detergente Ype", "Detegente", "", a, R.drawable.ypeneutro500ml);
        modeloItemArrayList.add(modeloItem);

        modeloItem= new ModeloItem(1,"Papel Higienico Neve", "Papel higienico de gente rica", "", a, R.drawable.pneve);
        modeloItemArrayList.add(modeloItem);




        adapterFeed.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("String", "Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        modeloItemArrayList.clear();
        adapterFeed.notifyDataSetChanged();
        Log.i("String", "Pause");
    }
}
