package com.example.mymosque.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymosque.MainActivity;
import com.example.mymosque.R;
import com.example.mymosque.RV_FindMasajid;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;

public class FragmentHome extends Fragment {


    //Declaring varriables
    private View homeView;
    private ImageView humbburger;
    private EditText search_masjid;
    private DrawerLayout mDrawerLayout;
    private Button mosqueBtn;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RV_FindMasajid adapter;
    private ArrayList<String> MasajidName = new ArrayList<>();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //intializing view and inflate the layout xml file
        homeView = inflater.inflate(R.layout.fragmenthome, container, false);

        // initializing decalared components
        initComponents();

        //listeners for components
        listeners();

        //fetching data from server and show it in recycler view
        fetchDataFromServerAndPopulateView();









        return homeView;
    }//End onCreateView Method


    private void initComponents(){

        //<For Toolbar>
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        //</For Toolbar>

        recyclerView = (RecyclerView) homeView.findViewById(R.id.RV_masajidList);
        mosqueBtn = (Button) homeView.findViewById(R.id.MosqueBTN);
        search_masjid = (EditText) homeView.findViewById(R.id.edit_txt_masjid);
        humbburger = (ImageView) homeView.findViewById(R.id.humburgerIcon);
        mDrawerLayout = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new RV_FindMasajid(getActivity(), MasajidName);

    }

    private void listeners(){

        humbburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        search_masjid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }});

        mosqueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) homeView.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.Screen_Area,new FragmentMyMasjid()).commit();
            }
        });

    }

    public void fetchDataFromServerAndPopulateView() {











        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }





}
