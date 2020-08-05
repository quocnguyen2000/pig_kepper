package com.example.pig_keeper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pig_keeper.Fragments.ChiFragment;
import com.example.pig_keeper.Fragments.GioiThieuFragment;
import com.example.pig_keeper.Fragments.ThongKeFragment;
import com.example.pig_keeper.Fragments.ThuFragment;
import com.google.android.material.navigation.NavigationView;


public class IndexActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        anhxa();
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ThongKeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_thongKe);
            toolbar.setTitle("Thống Kê");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_khoanThu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ThuFragment()).commit();
                        toolbar.setTitle("Khoản Thu");
                break;
            case R.id.nav_khoanChi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChiFragment()).commit();
                toolbar.setTitle("Khoản Chi");
                break;
            case R.id.nav_thongKe:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ThongKeFragment()).commit();
                toolbar.setTitle("Thống Kê");
                break;
            case R.id.nav_gioiThieu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GioiThieuFragment()).commit();
                toolbar.setTitle("Giới Thiệu");
                break;
            case R.id.nav_dangXuat:
                finish();
                System.exit(0);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void anhxa() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nv_view);
    }
    //Option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opt_caidat:
                Toast.makeText(IndexActivity.this,"Cài Đặt", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opt_dangxuat:
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
