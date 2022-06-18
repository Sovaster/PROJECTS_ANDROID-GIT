package com.example.newsapp;

import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricPrompt;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AuthFragment extends Fragment {

    private View _view;

    public AuthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _view = view;
        EditText login = view.findViewById(R.id.editTextLogin);
        EditText password = view.findViewById(R.id.editTextPassword);

        Button btnRegister = view.findViewById(R.id.btnRegister);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new OnClickListenerRegister());

        btnLogin.setOnClickListener(v ->
        {
            startBiometricAuth(new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(
                            _view.getContext(),
                            errString,
                            Toast.LENGTH_SHORT
                    ).show();
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);

                    User user = new User();
                    user.Login = login.getText().toString();
                    user.Password = password.getText().toString();
                    validateUser(user);
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(
                            _view.getContext(),
                            "Аутентификация провалена",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });



        });
    }

    private void validateUser(User user)
    {
        User validatedUser = new TableControllerUsers(_view.getContext()).validateUser(user);

        if(validatedUser != null)
        {
            MainActivity mainActivity = (MainActivity) _view.getContext();
            if(validatedUser.UserRole == UserRole.ADMIN)
            {
                mainActivity.ReplaceFragment(new AdminFragment());
            }
            else
            {
                mainActivity.ReplaceFragment(new ViewerFragment());
            }
        }
        else
        {
            Toast.makeText(_view.getContext(), "Не верно введен" +
                    " логин или пароль.", Toast.LENGTH_SHORT).show();
        }
    }

    private void startBiometricAuth(BiometricPrompt.AuthenticationCallback callback) {
        BiometricPrompt prompt = new BiometricPrompt(
                AuthFragment.this,
                ContextCompat.getMainExecutor(_view.getContext()),
                callback
        );
        BiometricPrompt.PromptInfo info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Авторизация")
                .setSubtitle("Приложите палец")
                .setNegativeButtonText("Отмена")
                .build();

        prompt.authenticate(info);
    }
}