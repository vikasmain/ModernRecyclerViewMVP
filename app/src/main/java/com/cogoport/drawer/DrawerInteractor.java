package com.cogoport.drawer;

import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;


public interface DrawerInteractor {
    void navigateTo(MenuItem item, DrawerLayout drawerLayout, DrawerListener listener);
}
