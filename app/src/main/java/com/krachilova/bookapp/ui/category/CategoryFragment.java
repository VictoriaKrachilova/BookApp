package com.krachilova.bookapp.ui.category;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.krachilova.bookapp.MyRecyclerViewAdapter;
import com.krachilova.bookapp.R;
import com.krachilova.bookapp.ui.home.BlankFragment;
import com.krachilova.bookapp.ui.home.HomeFragment;

public class CategoryFragment extends Fragment {
    private AppBarConfiguration mAppBarConfiguration;
    private CheckBox check;
    //String TAG_1 = "FRAGMENT_1";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                StringBuilder categories = new StringBuilder("");
                String prefix = "";
                for(int i=1; i<=26; i++) {
                    String checkID = "checkBox" + i;
                    int resID = getResources().getIdentifier(checkID, "id", getActivity().getPackageName());
                    check = root.findViewById(resID);
                    boolean f = check.isChecked();
                    if(f) {

                        categories.append(prefix);
                        prefix = ",";
                        categories.append(i+9);
                    }
                }//Log.i("Book", categories.toString());
                Bundle bundle = new Bundle();
                bundle.putString("categories", categories.toString());
                Navigation.findNavController(root).navigate(R.id.nav_home, bundle);
            }
        });
        return root;
    }

}