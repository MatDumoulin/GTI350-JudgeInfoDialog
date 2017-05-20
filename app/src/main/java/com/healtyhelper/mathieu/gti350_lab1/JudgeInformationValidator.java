package com.healtyhelper.mathieu.gti350_lab1;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Mathieu on 5/20/2017.
 */

public class JudgeInformationValidator implements View.OnClickListener {
    private final JudgeInformationDialog dialog;
    private ArrayList<String> countries;
    private SaveJudgeInformationListener successListener;

    public JudgeInformationValidator(JudgeInformationDialog dialog, ArrayList<String> countries,
                                     SaveJudgeInformationListener successListener) {
        this.dialog = dialog;
        this.countries = countries;
        this.successListener = successListener;
    }

    @Override
    public void onClick(View v) {

        String countryEnteredByUser = dialog.getCountryField().getText().toString();
        // If the text entered by the user is a country.
        if(countries.indexOf(countryEnteredByUser) != -1 ){
            String judgeName = dialog.getNameField().getText().toString();

            successListener.informationsWereSaved(dialog.getTag(), judgeName, countryEnteredByUser);
            dialog.dismiss();
        }else{
            dialog.getCountryField().setError("Vous devez entrer un pays valide.");
        }
    }
}
