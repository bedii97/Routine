package com.example.routine.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.routine.R;

public class AddYearlyReminder extends DialogFragment {

    private EditText editText1, editText2;
    private AddYearlyReminderListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_yearly_reminder, null);
        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            listener = (AddYearlyReminderListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement AddReminderDialogListener");
        }
    }

    public interface AddYearlyReminderListener{
        void getYearlyInfo(String et1, String et2);
    }
}
