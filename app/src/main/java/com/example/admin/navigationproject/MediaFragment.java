package com.example.admin.navigationproject;

import android.support.v4.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by Admin on 4/1/2018.
 */

public class MediaFragment extends Fragment {
    MediaPlayer mediaPlayer;
    Button btnStart;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.media_view, container, false);
        mediaPlayer = new MediaPlayer();
        btnStart = view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(mButtonClickListener);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        mediaPlayer.release();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    private View.OnClickListener mButtonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            String url = "http://66.90.93.122/ost/mass-effect-2/wpbbvlkw/25%20%20Suicide%20Mission.mp3"; // your URL here
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try
            {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            mediaPlayer.start();
        }
    };
}
