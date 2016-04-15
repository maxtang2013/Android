package com.example.maxtang.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import layout.DetailFragment;


public class CrimeDetailsActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);

        mCrimes = CrimeLab.getInstance(this).getmCrimes();

        ViewPager viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);

        setContentView(viewPager);


        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                DetailFragment fragment = DetailFragment.newInstance(crime.getId());
                return fragment;
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });


        UUID uuid = (UUID) getIntent().getSerializableExtra(DetailFragment.EXTRA_KEY_UUID);
        for (int i = 0; i < mCrimes.size(); ++i) {
            Crime c = mCrimes.get(i);
            if (c.getId().equals(uuid))
            {
                Log.d("Hi", "lo");
                viewPager.setCurrentItem(i);
                break;
            }
        }
        Log.d("Hi", uuid.toString());
    }

    private ArrayList<Crime> mCrimes;
}
