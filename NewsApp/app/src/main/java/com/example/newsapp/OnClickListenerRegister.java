package com.example.newsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OnClickListenerRegister implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.register_user, null, false);

        final EditText editTextLogin = formElementsView.findViewById(R.id.editTextLogin);
        final EditText editTextPassword = formElementsView.findViewById(R.id.editTextPassword);
        final RadioGroup radGrp = formElementsView.findViewById(R.id.radios);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Регистрация")
                .setPositiveButton("Зарегистрироваться",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                UserRole role;
                                String login = editTextLogin.getText().toString();
                                String password = editTextPassword.getText().toString();
                                int checkedRadioButtonId = radGrp.getCheckedRadioButtonId();
                                switch (checkedRadioButtonId)
                                {
                                    case R.id.rbAdmin:
                                        role = UserRole.ADMIN;
                                        break;
                                    case R.id.rbViewer:
                                        role = UserRole.VIEWER;
                                        break;
                                    default:
                                        role = UserRole.VIEWER;
                                        break;
                                }

                                User user = new User();
                                user.Login = login;
                                user.Password = password;
                                user.UserRole = role;

                                boolean createSuccessful = new TableControllerUsers(context).create(user);

                                if(createSuccessful){
                                    Toast.makeText(context, "Спасибо за регистрацию.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Не удалось зарегистрироваться.", Toast.LENGTH_SHORT).show();
                                }

                                dialog.cancel();
                            }

                        }).show();
    }
}
