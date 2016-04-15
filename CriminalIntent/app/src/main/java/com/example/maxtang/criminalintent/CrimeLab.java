package com.example.maxtang.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by maxtang on 16/4/13.
 */
public class CrimeLab {
    public static CrimeLab getInstance(Context context)
    {
        if (sInstance == null) {
            sInstance = new CrimeLab(context);
            sInstance.Init();
        }
        return sInstance;
    }

    private CrimeLab(Context context)
    {
        mContext = context;
        mCrimes = new ArrayList<>();
    }

    private void Init()
    {
        for (int i = 0; i < 20; ++i) {
            Crime crime = new Crime();
            crime.setTitle("Crime#" + i);
            if (i % 3 == 0)
                crime.setSolved(true);
            mCrimes.add(crime);
        }
    }

    public Crime getCrime(UUID id)
    {
        for (int i = 0; i < mCrimes.size(); ++i) {
            if (mCrimes.get(i).getId().equals(id))
                return mCrimes.get(i);
        }
        return null;
    }

    public ArrayList<Crime> getmCrimes()
    {
        return mCrimes;
    }

    private static CrimeLab sInstance;
    private Context mContext;
    private ArrayList<Crime> mCrimes;

}
