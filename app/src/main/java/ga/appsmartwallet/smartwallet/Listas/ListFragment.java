package ga.appsmartwallet.smartwallet.Listas;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;

import ga.appsmartwallet.smartwallet.Adapters.AdapterWishes;
import ga.appsmartwallet.smartwallet.AddLista;
import ga.appsmartwallet.smartwallet.Modelos.ModelItemWish;
import ga.appsmartwallet.smartwallet.R;

import static ga.appsmartwallet.smartwallet.R.*;


public class ListFragment extends Fragment {

    View view;
    FloatingActionButton fbAdd;
    RecyclerView recyclerView;
    FrameLayout lladd;
    Button btnAdd;
    EditText etNome, etQtd;
    ArrayList<ModelItemWish> modelItemWishesArray = new ArrayList<>();
    Serializable modelItemWish;
    AdapterWishes adapterWishes;


    public ListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(layout.fragment_list_, container, false);
        recyclerView = view.findViewById(id.rvLista);

//        lladd.setVisibility(View.INVISIBLE);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapterWishes = new AdapterWishes(getContext(), modelItemWishesArray);
        recyclerView.setAdapter(adapterWishes);
        btnAdd = view.findViewById(id.btnAdd2);
// troca fragment
        fbAdd = view.findViewById(id.floatingActionButton);
        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = inflater.inflate(layout.fragment_list_, container, false);
                Log.i("String", "Button Click");

            }
        });

//troca fragment
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = inflater.inflate(layout.fragment_list2, container, false);
                Log.i("String", "Button Click");
                onDestroy();
            }
        });



//        modelItemWishesArray.add((ModelItemWish) intent.getSerializableExtra("item"));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getActivity().getIntent();
        modelItemWish = intent.getSerializableExtra("item");
    }
}
