package com.cogoport.drawer;

import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;


public interface DrawerPresenter {
    void navigationItemSelected(MenuItem item, DrawerLayout drawerLayout);
}
