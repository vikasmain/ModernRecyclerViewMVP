package com.cogoport.drawer;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.cogoport.R;


public class DrawerInteractorImpl implements DrawerInteractor {

    @Override
    public void navigateTo(MenuItem item, DrawerLayout drawerLayout, DrawerListener listener) {

        switch (item.getItemId()) {

            case R.id.i4:
             //   listener.fragmentReplace(Fragment1.newInstance());
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
