package com.example.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.RecyclerViewAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Model> days = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GridLayoutManager glm = new GridLayoutManager(this,1);
       glm.setOrientation(RecyclerView.VERTICAL);



        setData();

        RecyclerView recyclerView = findViewById(R.id.recycleView);

        recyclerView.setLayoutManager(glm);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, days);
        recyclerView.setAdapter(adapter);
    }

    private void setData()
    {
        days.add(new Model("ПОНЕДЕЛЬНИК Нахимовский", "", "Разработка программных\nмодулей", "Технология разработки и\nзащиты баз данных",
                            "Разработка программных\nмодулей\n\nТехнология разработки\nпрограммного\nобеспечения", "Разработка мобильных\nприложений",
                            "","А.А.Шимбирёв", "А.Д.Горбунов", "А.Ю.Бушин\n\n\nЛ.А.Соколова", "А.О.Лясников"));
        days.add(new Model("ВТОРНИК", "ПРАКТИКА", "ПРАКТИКА", "ПРАКТИКА",
                            "ПРАКТИКА", "ПРАКТИКА",
                            "","", "", "", ""));
        days.add(new Model("СРЕДА", "ПРАКТИКА", "ПРАКТИКА", "ПРАКТИКА",
                            "ПРАКТИКА", "ПРАКТИКА",
                            "","", "", "", ""));
        days.add(new Model("ЧЕТВЕРГ Нежинская", "","Системное\nпрограммирование", "Технология разработки\nпрограммного\nобеспечения", "Инструментальные\nсредства разработки\nПО",
                            "Разработка\nпрограммных модулей", "","А.Д.Нилов", "Л.А.Соколова", "Ю.В.Севастьянов", "А.Ю.Бушин"));
        days.add(new Model("ПЯТНИЦА Нахимовский", "Иностранный язык в\nпрофессиональной\nдеятельности", "Физическая культура", "Инструментальные\nсредства разработки\nПО\n\nТехнология разработки\nи защиты баз данных",
                            "Разработка мобильных\nприложений\n\nСистемное\nпрограммирование", "",
                            "А.Д.Завьялова,\nА.Ю.Дымская","А.В.Андрюков", "Ю.В.Севастьянов\n\n\n\nА.Д.Горбунов", "А.О.Лясников\n\n\nА.Д.Нилов", ""));
    }
}