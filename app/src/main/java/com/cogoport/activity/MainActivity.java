package com.cogoport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cogoport.Dagger.module.DependentClass;
import com.cogoport.R;
import com.cogoport.drawer.DrawerPresenterImpl;
import com.cogoport.fragments.Dagger2;
import com.cogoport.fragments.RxjavaRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements DrawerPresenterImpl.DrawerView, NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private DrawerPresenterImpl drawerPresenter;
    private DependentClass dependentClass;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViews();
        drawerPresenter = new DrawerPresenterImpl(this);
        RxjavaRetrofit rxjavaRetrofit=new RxjavaRetrofit();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,rxjavaRetrofit);
        fragmentTransaction.commit();
        //Dagger2 starts here.
//        DagComponent component = DaggerDagComponent.builder().daggerModule(new DaggerModule()).build();

//        dependentClass = component.provideVehicle();
//        String t=dependentClass.getString();
//        if(t!=null)
//        {
//
//            String[] p=t.split(" ");
//            final Deque<String> words=new LinkedList<String>();
//            for(int i=0;i<p.length;i++)
//            {
//                words.addLast(p[i]);
//            }
//            shownext(words);
//
//        }
//                else
//        {
//            Toast.makeText(MainActivity.this, "Sring doesn't exist", Toast.LENGTH_SHORT).show();
//        }
//        //Dagger2 closes here
//    }
//
//
////Display string Words by Words
//
//    public void shownext(final Deque<String> w)
//    {
//        Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append(w.pollFirst()+" ");
//                if(w.size()>0)
//                {
//                    shownext(w);
//                }
//            }
//        },1000);
//
    }
    @Override
    public void onBackPressed() {
        setBackPressed();
    }



//its a method of DrawerView interface
    @Override public void navigateUsingTo(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment).commit();
    }

    // setup views
    private void setupViews() {
        setSupportActionBar(toolbar);
        setUpActionBarDrawerToggle();
        navigationView.setNavigationItemSelectedListener(this);
    }

    // create a ActionBarDrawerToggle
    private void setUpActionBarDrawerToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    // setting for BackPressed
    private void setBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     switch (item.getItemId()){
         case R.id.base:
             startActivity(new Intent(MainActivity.this,Playstoreview.class));
             break;
         case R.id.base2:
             startActivity(new Intent(MainActivity.this,HomeActivity.class));
             break;
      }
        return true;    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.i1) {
            RxjavaRetrofit fragment = new RxjavaRetrofit();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.commit();            // Handle the camera action
        }
        else if (id == R.id.i2) {
            Dagger2 fragment = new Dagger2();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.commit();            // Handle the camera action
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem menuItem=menu.findItem(R.id.base);
        return true;
    }
}
