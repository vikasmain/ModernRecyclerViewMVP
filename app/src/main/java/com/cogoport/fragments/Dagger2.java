package com.cogoport.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cogoport.Dagger.DagComponent;
import com.cogoport.Dagger.DaggerDagComponent;
import com.cogoport.Dagger.DaggerModule;
import com.cogoport.Dagger.module.DependentClass;
import com.cogoport.R;

import java.util.Deque;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dagger2 extends Fragment {


    public Dagger2() {
        // Required empty public constructor
    }
    @BindView(R.id.t1)
    TextView textView;
    View v;
    private DependentClass dependentClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_dagger2, container, false);
        ButterKnife.bind(this,v);
        DagComponent component = DaggerDagComponent.builder().daggerModule(new DaggerModule()).build();
        dependentClass = component.provideVehicle();
        String t=dependentClass.getString();
        if(t!=null)
        {

            String[] p=t.split(" ");
            final Deque<String> words=new LinkedList<String>();
            for(int i=0;i<p.length;i++)
            {
                words.addLast(p[i]);
            }
            shownext(words);

        }
        else
        {
            Toast.makeText(getContext(), "Sring doesn't exist", Toast.LENGTH_SHORT).show();
        }
        //Dagger2 closes here



//Display string Words by Words


        return v;
    }
    public void shownext(final Deque<String> w)
    {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.append(w.pollFirst()+" ");
                if(w.size()>0)
                {
                    shownext(w);
                }
            }
        },1000);

    }
}
