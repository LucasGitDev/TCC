package ga.appsmartwallet.smartwallet;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ga.appsmartwallet.smartwallet.Adapters.ViewPagerAdapter;
import ga.appsmartwallet.smartwallet.Listas.BuyFragment;
import ga.appsmartwallet.smartwallet.Listas.ConfigFragment;
import ga.appsmartwallet.smartwallet.Listas.ListFragment;

public class Principal extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        tabLayout = findViewById(R.id.tabLayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //chamada de fragmentos

        adapter.AddFragment(new BuyFragment(), "visão Geral");
        adapter.AddFragment(new ListFragment(), "Lista de Compras");
        adapter.AddFragment(new ConfigFragment(), "Settings");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_wallet);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_playlist_add_check_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_settings_black_24dp);


        //tirar sombra da barra
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        //esconder nome
        actionBar.hide();
    }
}
