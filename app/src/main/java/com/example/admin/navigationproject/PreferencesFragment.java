package com.example.admin.navigationproject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Admin on 4/2/2018.
 */

public class PreferencesFragment extends Fragment {

    ToggleButton sendData;
    TextView tvTheme;
    ToggleButton btnTheme;
    ToggleButton btnSegestions;
    EditText teDefaultEngine;

    public PreferencesFragment()
    {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preferences_view, container, false);

        sendData = view.findViewById(R.id.btnSendData);
        tvTheme = view.findViewById(R.id.tvTheme);
        btnTheme = view.findViewById(R.id.btnTheme);
        btnSegestions = view.findViewById(R.id.btnSugestions);
        teDefaultEngine = view.findViewById(R.id.teDefaultEngine);

        sendData.setOnCheckedChangeListener(sendDataCheckedListener);
        btnTheme.setOnCheckedChangeListener(themeCheckedListener);
        btnSegestions.setOnCheckedChangeListener(segestionsCheckedListener);
        teDefaultEngine.addTextChangedListener(watcher);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("mySharedPref", MODE_PRIVATE);
        sendData.setChecked(sharedPreferences.getBoolean("send", false));
        btnTheme.setChecked(sharedPreferences.getBoolean("theme", false));
        btnSegestions.setChecked(sharedPreferences.getBoolean("segestions", false));
        teDefaultEngine.setText(sharedPreferences.getString("defaultEngine", "https://google.com"));

        return view;
    }

    CompoundButton.OnCheckedChangeListener sendDataCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            AsyncPreferenceSaver saverTask = new AsyncPreferenceSaver();
            saverTask.doInBackground();
        }
    };
    CompoundButton.OnCheckedChangeListener themeCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b)
                tvTheme.setText("Dark Theme");
            else
                tvTheme.setText("Light Theme");
            AsyncPreferenceSaver saverTask = new AsyncPreferenceSaver();
            saverTask.doInBackground();
        }
    };
    CompoundButton.OnCheckedChangeListener segestionsCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            AsyncPreferenceSaver saverTask = new AsyncPreferenceSaver();
            saverTask.doInBackground();
        }
    };

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            AsyncPreferenceSaver saverTask = new AsyncPreferenceSaver();
            saverTask.doInBackground();
        }
    };

    private void SavePreferences()
    {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("mySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("send", sendData.isChecked());
        editor.putBoolean("theme", btnTheme.isChecked());
        editor.putBoolean("sugesstions", btnSegestions.isChecked());
        editor.putString("defaultEngine", teDefaultEngine.getText().toString());
        editor.commit();
    }

    class AsyncPreferenceSaver extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            SavePreferences();
            return "";
        }
    }
}
