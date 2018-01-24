package com.cogoport.drawer;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.cogoport.R;
import com.cogoport.fragments.GridHorizontalFragment;



public class DrawerInteractorImpl implements DrawerInteractor {

    @Override
    public void navigateTo(MenuItem item, DrawerLayout drawerLayout, DrawerListener listener) {

        switch (item.getItemId()) {

            case R.id.nav_grid_h:
                listener.fragmentReplace(GridHorizontalFragment.newInstance());
                break;

//
//            case R.id.nav_item_types:
//                listener.fragmentReplace(ItemTypesVerticalFragment.newInstance());
//                break;
//            case R.id.nav_item_responsive:
//                listener.fragmentReplace(ResponsiveLinearVerticalFragment.newInstance());
//                break;
//            case R.id.nav_item_qualifiers:
//                listener.fragmentReplace(GridQualifiersVerticalFragment.newInstance());
//                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
