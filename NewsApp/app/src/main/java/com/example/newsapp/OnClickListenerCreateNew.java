package com.example.newsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

public class OnClickListenerCreateNew implements View.OnClickListener
{
    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.create_new_form, null, false);

        final EditText editTextTitle = (EditText) formElementsView.findViewById(R.id.editTextNewsTitle);
        final EditText editTextDescription = (EditText) formElementsView.findViewById(R.id.editTextNewsDescription);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Добавление новости")
                .setPositiveButton("Добавить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String title = editTextTitle.getText().toString();
                                String description = editTextDescription.getText().toString();

                                New newBlock = new New();
                                newBlock.Title = title;
                                newBlock.Description = description;

                                boolean createSuccessful = new TableControllerNews(context).create(newBlock);

                                if(createSuccessful){
                                    Toast.makeText(context, "Новость успешно создана.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Не удалось создать новость.", Toast.LENGTH_SHORT).show();
                                }

                                AdminFragment fragment = (AdminFragment) ((MainActivity)context).CurrentFragment;
                                fragment.readRecords();

                                dialog.cancel();
                            }

                        }).show();
    }
}
