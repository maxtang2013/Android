package com.example.maxtang.audioexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment {

    public PlayerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPlayer = AudioPlayer.getInstance();

        View v = getActivity().getLayoutInflater().inflate(R.layout.player_fragment, null);
        mPlayButton = (Button) v.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.play(getActivity());
            }
        });

        mStopButton = (Button) v.findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
            }
        });
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceData) {
        super.onSaveInstanceState(savedInstanceData);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private AudioPlayer mPlayer;
    private Button mPlayButton;
    private Button mStopButton;
}
