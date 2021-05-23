package com.krachilova.bookapp.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.krachilova.bookapp.R;

import static android.content.Intent.getIntent;

public class HomeFragment extends Fragment {

    private FragmentActivity myContext;
    String categories;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StringBuilder s = new StringBuilder();
        String prefix = "";
        for(int i=10; i<36; i++){
            s.append(prefix);
            prefix = ",";
            s.append(i);
        }


        if(getArguments() != null) categories = getArguments().getString("categories");
        else categories = s.toString();
        Log.i("Jane", categories);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Button btn1 = root.findViewById(R.id.btn1);
        Button btn2 = root.findViewById(R.id.btn2);
        Button btn3 = root.findViewById(R.id.btn3);
        btn1.setOnClickListener(this::onClick);
        btn2.setOnClickListener(this::onClick);
        btn3.setOnClickListener(this::onClick);
        BlankFragment blankFragment=BlankFragment.newInstance(0, categories);

        myContext.getSupportFragmentManager().beginTransaction().replace(R.id.fr_container, blankFragment).commit();

        return root;

    }
    public void onClick(View view){

        switch (view.getId()) {
            case R.id.btn1:
                switchFragment("all", 0, categories);
                break;
            case R.id.btn2:
                switchFragment("discount", 2, categories);
                break;
            case R.id.btn3:
                switchFragment("free", 1, categories);
                break;   }

    }
    void switchFragment(String name, int i, String categories) {
         Fragment fragment;
        fragment = myContext.getSupportFragmentManager().findFragmentByTag(name);
        if (fragment == null) {
            fragment = BlankFragment.newInstance(i, categories);
            myContext.getSupportFragmentManager().beginTransaction().replace(R.id.fr_container, fragment, name).commit();
        }
        else {
            //Toast.makeText(this,"Не меняем", Toast.LENGTH_SHORT).show();
            Log.i("Blank","Не меняем");
        }

    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


}