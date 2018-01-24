package com.cogoport.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cogoport.R;
import com.cogoport.adapter.AdapterExample;
import com.cogoport.model.Picture;

import java.util.ArrayList;

/**
 * Created by Jhordan on 13/10/15.
 */
public class GridHorizontalFragment extends MainFragment {

    public static GridHorizontalFragment newInstance() {
        return new GridHorizontalFragment();
    }

    @Override protected int getLayout() {
        return R.layout.fragment_base;
    }

    @Override protected RecyclerView.LayoutManager getLayoutManager() {
        return getGridLayoutManager();
    }

    @Override protected RecyclerView.Adapter getAdapter(ArrayList<Picture> pictureList) {
        return new AdapterExample(pictureList,R.layout.item_type_two);
    }

    private GridLayoutManager getGridLayoutManager() {
        return new GridLayoutManager(
                getActivity(),
                2,
                GridLayoutManager.HORIZONTAL,
                false);
    }
}

