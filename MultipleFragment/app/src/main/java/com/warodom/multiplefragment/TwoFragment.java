package com.warodom.multiplefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {


    public TwoFragment() {
        // Required empty public constructor
    }

    public static TwoFragment newInstance() {
        return new TwoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_two, container, false);

        Button btn_close = (Button)rootView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Check", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Check", "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Check", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Check", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Check", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Check", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Check", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Check", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Check", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Check", "onDetach");
    }
}
