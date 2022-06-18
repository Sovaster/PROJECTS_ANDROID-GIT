package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class AdminFragment extends Fragment {

    private View _view;

    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _view = view;
        readRecords();

        Button buttonCreateNew = view.findViewById(R.id.buttonCreateNew);
        buttonCreateNew.setOnClickListener(new OnClickListenerCreateNew());
    }

    public void readRecords()
    {
        LinearLayout linearLayoutRecords = (LinearLayout) _view.findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<New> news = new TableControllerNews(_view.getContext()).read();

        if (news.size() > 0) {

            for (New obj : news) {

                int id = obj.ID;
                String title = obj.Title;
                String description = obj.Description;

                String textViewContents = "Заголовок: " + title + "\nОписание: "
                        + description;

                TextView textViewStudentItem= new TextView(_view.getContext());
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerNewsRecord());


                linearLayoutRecords.addView(textViewStudentItem);
            }

        }

        else {

            TextView locationItem = new TextView(_view.getContext());
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Нет записей");

            linearLayoutRecords.addView(locationItem);
        }
    }
}