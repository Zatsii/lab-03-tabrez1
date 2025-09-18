package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    interface EditCityDialogListener {
        void editCity(int position, City updatedCity);
    }

    private EditCityDialogListener listener;
    private City city;
    private int position;

    public EditCityFragment(City city, int position) {
        this.city = city;
        this.position = position;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditCityDialogListener) {
            listener = (EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);
        EditText editCityName = view.findViewById(R.id.edit_city_name);
        EditText editProvinceName = view.findViewById(R.id.edit_province_name);

        editCityName.setText(city.getName());
        editProvinceName.setText(city.getProvince());

        return new AlertDialog.Builder(getContext())
                .setView(view)
                .setTitle("Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, which) -> {
                    String newName = editCityName.getText().toString();
                    String newProvince = editProvinceName.getText().toString();
                    City updatedCity = new City(newName, newProvince);
                    listener.editCity(position, updatedCity);
                })
                .create();
    }
}
