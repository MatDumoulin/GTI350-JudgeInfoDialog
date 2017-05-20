package com.healtyhelper.mathieu.gti350_lab1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by Mathieu on 5/20/2017.
 */

public class JudgeInformationDialog extends android.support.v4.app.DialogFragment {
    private static ArrayList<String> countries;
    private SaveJudgeInformationListener judgeInformationWasSavedListener;
    private String judgeID;
    private AutoCompleteTextView TB_Country;
    private EditText TB_Name;

    /**
     * A factory method to create a new JudgeInformationDialog.
     * Use this method instead of the default constructor as it sets the callback interface
     * for the judge information. The default constructor is still available for internal
     * fragment management by android as it is requested.
     */
    public static JudgeInformationDialog create(SaveJudgeInformationListener callbackWhenInfoSaved) {
        JudgeInformationDialog dialog = new JudgeInformationDialog();
        dialog.setJudgeInformationWasSavedListener(callbackWhenInfoSaved);
        return dialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // First, we need to build our dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        builder.setTitle("Juge 1");
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.customalert, null);
        builder.setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // This listener needs to remain empty for the validation on the country
                        // field to work.
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getDialog().dismiss();
                    }
                });


        // Initialization of the Country field.
        if(countries == null) {
            countries = getListOfCountries();
        }

        TB_Country = (AutoCompleteTextView) view.findViewById(R.id.TB_Pays);
        TB_Name = (EditText) view.findViewById(R.id.TB_Name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, countries);
        TB_Country.setThreshold(1);//will start working from first character
        TB_Country.setAdapter(adapter);

        AlertDialog alertDialog = builder.show();

        Button BTN_OK = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        BTN_OK.setOnClickListener(new JudgeInformationValidator(this, countries, judgeInformationWasSavedListener));

        return alertDialog;
    }

    public void setCallBackInterface(SaveJudgeInformationListener listener) {
        judgeInformationWasSavedListener = listener;
    }

    // Returns a list of all the countries in the world (based on the android's available locales).
    private ArrayList<String> getListOfCountries() {
        // Initialization of the Country picker
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;

        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        return countries;
    }

    public EditText getNameField() {
        return TB_Name;
    }

    public EditText getCountryField() {
        return TB_Country;
    }

    public void setJudgeInformationWasSavedListener(SaveJudgeInformationListener listener) {
        judgeInformationWasSavedListener = listener;
    }


}


