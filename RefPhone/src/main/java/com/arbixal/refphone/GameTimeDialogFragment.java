package com.arbixal.refphone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by Caleb on 16/09/13.
 */
public class GameTimeDialogFragment extends DialogFragment
{
    public enum TimeType
    {
        GameTime,
        ExtraTime
    };

    public interface GameTimeDialogListener
    {
        public void onDialogPositiveClick(int pTime, TimeType pTimeType);
    }

    GameTimeDialogListener mListener;
    NumberPicker npGameTime;
    TimeType mTimeType;
    int mDefaultValue;

    GameTimeDialogFragment(TimeType pTimeType, int pDefault)
    {
        this.mTimeType = pTimeType;
        this.mDefaultValue = pDefault;
    }

    @Override
    public void onAttach(Activity pActivity)
    {
        super.onAttach(pActivity);

        try
        {
            this.mListener = (GameTimeDialogListener) pActivity;
        }
        catch (ClassCastException ex)
        {
            throw new ClassCastException(pActivity.toString() + " must implement GameTimeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle pSavedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        if (this.mTimeType == TimeType.GameTime)
        {
            builder.setTitle("Period Time");
            builder.setMessage("Set length of regular half:");
        }
        else
        {
            builder.setTitle("Extra Time");
            builder.setMessage("Set length of extra time:");
        }

        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_gametime, null);
        builder.setView(dialogView);

        this.npGameTime = (NumberPicker)dialogView.findViewById(R.id.npTimePicker);
        this.npGameTime.setMaxValue(59);
        this.npGameTime.setMinValue(1);
        this.npGameTime.setWrapSelectorWheel(false);
        this.npGameTime.setValue(this.mDefaultValue);

        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                mListener.onDialogPositiveClick(GameTimeDialogFragment.this.npGameTime.getValue(), GameTimeDialogFragment.this.mTimeType);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                GameTimeDialogFragment.this.getDialog().cancel();
            }
        });

        return (builder.create());
    }
}
