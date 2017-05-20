package com.healtyhelper.mathieu.gti350_lab1;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements SaveJudgeInformationListener {
    private JudgeInformationDialog judgeInformationDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation du dialog pour entrer les informations d'un juge.
        judgeInformationDialog = JudgeInformationDialog.create(this);
    }

    public void onClick(View view) {
        judgeInformationDialog.show(getSupportFragmentManager(), "Juge1");
    }

    @Override
    public void informationsWereSaved(String judgeId, String name, String country) {
       Toast.makeText(this, "Judge ID: " + judgeId + ", Name: " + name + ", Country: " + country, Toast.LENGTH_LONG).show();
    }
}
