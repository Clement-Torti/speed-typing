package com.example.speed_typing;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDetails extends Fragment {

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment, container, false);
        //Instancier les composants graphique ici (faîtes vos findViewById)
        return view;
    }

}
