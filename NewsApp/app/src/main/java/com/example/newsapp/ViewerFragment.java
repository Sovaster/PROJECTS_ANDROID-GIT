package com.example.newsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class ViewerFragment extends Fragment {

    private View _view;

    public ViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _view = view;
        readRecords();
    }
    public void readRecords()
    {
        LinearLayout linearLayoutRecords = _view.findViewById(R.id.linearLayoutRecords);
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