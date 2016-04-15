package layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.maxtang.criminalintent.Crime;
import com.example.maxtang.criminalintent.CrimeLab;
import com.example.maxtang.criminalintent.R;

import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance(UUID)} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    public static final int REQUEST_DATE = 0;
    private static String LOG_TAG = "DetailFragment";

    public DetailFragment() {
    }

    public static String EXTRA_KEY_UUID = "com.example.maxtang.criminalIntent.uuid";

    public static DetailFragment newInstance(UUID id) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_KEY_UUID, id);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCrimeId = (UUID)getArguments().getSerializable(EXTRA_KEY_UUID);
            mCrime = CrimeLab.getInstance(getActivity()).getCrime(mCrimeId);
            Log.d(LOG_TAG, mCrime.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_fragment, container, false);
        mCrimeTitleTextView = (EditText)v.findViewById(R.id.crimeTitleTextView);
        mCrimeTitleTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mCrimeTitleTextView.setText(mCrime.getTitle());

        mDateButton = (Button)v.findViewById(R.id.crimeDate);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(DetailFragment.this, REQUEST_DATE);
                dialog.show(fm, "DatePicker");
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.solvedCheckBox);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        Log.d(LOG_TAG, mCrime.getTitle() + " - " + mCrime.getDate() + " - " + mCrime.isSolved());
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode != REQUEST_DATE)
            return;

        Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
        mCrime.setDate(date);
        mDateButton.setText(date.toString());
    }

    private UUID mCrimeId;

    private CheckBox mSolvedCheckBox;
    private Button mDateButton;
    private EditText mCrimeTitleTextView;
    private Crime mCrime;
}
