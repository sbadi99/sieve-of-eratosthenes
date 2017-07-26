package com.primesieve.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.primesieve.R;

/**
 * The YouTubeVideoFragment class plays a given video using the YouTube Android API
 */
public class YouTubeVideoFragment extends Fragment {

    private String YOUTUBE_API_KEY;
    private String VIDEO_ID;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Assign it here to ensure context is !=null
        //(since fragment is already attached to context here)
        YOUTUBE_API_KEY = context.getString(R.string.youtube_key);
        VIDEO_ID = getString(R.string.sieve_video);

    }

    @Override
    public void onResume() {
        super.onResume();

        launchYouTubePlayer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragments_youtube, container, false);
    }

    /**
     * Launch YouTube Player
     * (Utilizing YouTube Android Player API)
     */
    private void launchYouTubePlayer() {

        final YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.youtube_fragment, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    player.loadVideo(VIDEO_ID);
                    player.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                // YouTube error
                final String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });
    }
}
