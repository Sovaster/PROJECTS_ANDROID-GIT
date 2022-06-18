package com.example.newsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OnLongClickListenerNewsRecord implements View.OnLongClickListener {
    private Context context;
    private String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] items = { "Изменить", "Удалить" };

        new AlertDialog.Builder(context).setTitle("Новость")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerNews(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Новость была удалена.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Не удалось удалить новость.", Toast.LENGTH_SHORT).show();
                            }

                            AdminFragment fragment = (AdminFragment) ((MainActivity)context).CurrentFragment;
                            fragment.readRecords();

                        }
                        dialog.dismiss();

                    }
                }).show();

        return false;
    }

    public void editRecord(final int newId) {
        final TableControllerNews tableControllerNews = new TableControllerNews(context);
        New newBlock = tableControllerNews.readSingleRecord(newId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.create_new_form, null, false);

        final EditText editTextTitle = (EditText) formElementsView.findViewById(R.id.editTextNewsTitle);
        final EditText editTextDescription = (EditText) formElementsView.findViewById(R.id.editTextNewsDescription);

        editTextTitle.setText(newBlock.Title);
        editTextDescription.setText(newBlock.Description);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Изменение новости")
                .setPositiveButton("Сохранить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                New newBlock = new New();
                                newBlock.ID = newId;
                                newBlock.Title= editTextTitle.getText().toString();
                                newBlock.Description = editTextDescription.getText().toString();

                                boolean updateSuccessful = tableControllerNews.update(newBlock);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Новость успешно изменена.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Не удалось изменить новость.", Toast.LENGTH_SHORT).show();
                                }

                                AdminFragment fragment = (AdminFragment) ((MainActivity)context).CurrentFragment;
                                fragment.readRecords();

                                dialog.cancel();
                            }

                        }).show();
    }
}
