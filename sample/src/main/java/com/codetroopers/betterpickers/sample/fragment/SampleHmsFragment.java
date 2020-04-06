package com.codetroopers.betterpickers.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.codetroopers.betterpickers.hmspicker.HmsPickerBuilder;
import com.codetroopers.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.codetroopers.betterpickers.sample.R;

/**
 * User: derek Date: 4/30/13 Time: 7:43 PM
 */
public class SampleHmsFragment extends Fragment implements HmsPickerDialogFragment.HmsPickerDialogHandlerV2 {

    private AppCompatTextView mResultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_and_button, container, false);

        mResultTextView = (AppCompatTextView) view.findViewById(R.id.text);
        Button button = (Button) view.findViewById(R.id.button);

        mResultTextView.setText(R.string.no_value);
        button.setText(R.string.hms_picker_set);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmsPickerBuilder hpb = new HmsPickerBuilder()
                        .setFragmentManager(getChildFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(SampleHmsFragment.this);
                hpb.show();
            }
        });

        return view;
    }

    @Override
    public void onDialogHmsSet(int reference, boolean isNegative, int hours, int minutes, int seconds) {
        mResultTextView.setText(getString(R.string.hms_picker_result_value_multiline, hours, minutes, seconds, isNegative));
    }
}
