package ga.appsmartwallet.smartwallet;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import ga.appsmartwallet.smartwallet.Listas.ListFragment;
import ga.appsmartwallet.smartwallet.Modelos.ModelItemWish;

public class AddLista extends AppCompatActivity {

    FrameLayout lladd;
    Button btnAdd;
    EditText etNome, etQtd;
    ModelItemWish modelItemWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lista);
        lladd = findViewById(R.id.llAdd);
        btnAdd = findViewById(R.id.btnAdd);
        etNome = findViewById(R.id.etNome);
        etQtd = findViewById(R.id.etQtd);
        modelItemWish = new ModelItemWish(etNome.toString(), Integer.parseInt(etQtd.getText().toString()));

        Log.i("String", modelItemWish.getNome());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        //esconder nome
        actionBar.hide();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("String", "Button Click");
                Intent intent = new Intent(AddLista.this, ListFragment.class);
                intent.putExtra("item", modelItemWish);
//                startActivity(intent);
                onDestroy();
            }
        });

    }

}
