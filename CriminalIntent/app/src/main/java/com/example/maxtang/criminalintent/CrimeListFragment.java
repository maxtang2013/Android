package com.example.maxtang.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import layout.DetailFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class CrimeListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInsanceData) {
        super.onCreate(savedInsanceData);
        getActivity().setTitle(R.string.crimes_title);

        mCrimes = CrimeLab.getInstance(getActivity()).getmCrimes();

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), CrimeDetailsActivity.class);
        Crime crime = mCrimes.get(position);
        intent.putExtra(DetailFragment.EXTRA_KEY_UUID, crime.getId());
        getActivity().startActivity(intent);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.crime_list_item, null);
            }

            Crime crime = getItem(position);
            TextView titleText = (TextView) convertView.findViewById(R.id.item_title);
            TextView dateText = (TextView) convertView.findViewById(R.id.item_date);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.item_checkbox_solved);

            titleText.setText(crime.getTitle());
            dateText.setText(crime.getDate().toString());
            checkBox.setChecked(crime.isSolved());

            return convertView;
        }
    }

    private ArrayList<Crime> mCrimes;
}
