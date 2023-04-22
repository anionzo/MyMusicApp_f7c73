package com.example.mymusicapp.Fragments;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mymusicapp.Activities.PlaySongActivity;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PlaySongFragment extends Fragment {
    private ImageView addPlayList,shareSong;
    private ImageView back, help, imageSong;
    private ImageView repeatSong,backSong, playSong, nextSong, randomSong;
    private TextView songTitle, songSinger, timePlay, timeEnd;
    private SeekBar seekBarTime;
    private int currentIndex = 0;
    ArrayList<SongModel> songs = new ArrayList<>();
    private MediaPlayer mediaPlayer;

    public PlaySongFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View view = inflater.inflate(R.layout.fragment_play_song, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //songs = getSongs();
        Bundle bundle = getActivity().getIntent().getExtras();
        SongModel song = (SongModel) bundle.getSerializable("itemSong");
        songs = (ArrayList<SongModel>) bundle.getSerializable("Songs");
        if (song != null)
        {
            getPlaySong(song);
            new PlayMP3().execute(song.getLinkSong());
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.play_song: {
                        seekBarTime.setMax(mediaPlayer.getDuration());
                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                            playSong.setImageResource(R.drawable.ic_play);
                        } else {
                            mediaPlayer.start();
                            playSong.setImageResource(R.drawable.ic_stop);
                        }
                    }
                    break;
                    case R.id.next_song: {
                        if (mediaPlayer != null) {
                            playSong.setImageResource(R.drawable.ic_stop);
                        }
                        if (currentIndex < songs.size() - 1) {
                            currentIndex++;
                        } else {
                            currentIndex = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                        }
                        getPlaySong(songs.get(currentIndex));
                        new PlayMP3().execute(songs.get(currentIndex).getLinkSong());
                    }
                    break;

                    case R.id.back_song:
                    {
                        if(mediaPlayer != null ){
                            playSong.setImageResource(R.drawable.ic_stop);
                        }
                        if(currentIndex > 0)
                        {
                            currentIndex --;
                        }
                        else currentIndex = songs.size() -1;
                        if (mediaPlayer.isPlaying())
                        {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                        }
                        getPlaySong(songs.get(currentIndex));
                        new PlayMP3().execute(songs.get(currentIndex).getLinkSong());
                    }break;

                    case R.id.back:
                    {
                        if (mediaPlayer != null)
                            mediaPlayer.stop();
                        mediaPlayer = null;
                        getActivity().finish();

                    }break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + view.getId());
                }

            }
        };
        playSong.setOnClickListener(listener);
        nextSong.setOnClickListener(listener);
        backSong.setOnClickListener(listener);
        back.setOnClickListener(listener);
    }



    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        timeEnd.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarTime.setMax(mediaPlayer.getDuration());
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
                playSong.setImageResource(R.drawable.ic_stop);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition();
                    seekBarTime.setProgress(mCurrentPosition);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    timePlay.setText(simpleDateFormat.format(mCurrentPosition));
                }
                new Handler().postDelayed(this,1000);
            }
        },1000);
    }


    private void getPlaySong(SongModel song){
        songSinger.setText(song.getNameSinger());
        songTitle.setText(song.getNameSong());
        Glide.with(PlaySongFragment.this)
                .load(song.getLinkImg())
                .centerCrop()
                .into(imageSong);
    }
    class PlayMP3 extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }
        @Override
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            try {
                mediaPlayer = new MediaPlayer();
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build();
                mediaPlayer.setAudioAttributes(audioAttributes);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                playSong.setImageResource(R.drawable.ic_stop);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            TimeSong();
        }
    }
    private void initView(View view){
        addPlayList = view.findViewById(R.id.add_play_list);
        shareSong = view.findViewById(R.id.share_song);
        back = view.findViewById(R.id.back);
        help = view.findViewById(R.id.help);
        imageSong = view.findViewById(R.id.image_song);
        repeatSong = view.findViewById(R.id.repeat_song);
        backSong = view.findViewById(R.id.back_song);
        playSong = view.findViewById(R.id.play_song);
        nextSong = view.findViewById(R.id.next_song);
        randomSong =view.findViewById(R.id.random_song);
        songTitle = view.findViewById(R.id.song_title);
        songSinger = view.findViewById(R.id.song_singer);
        seekBarTime = view.findViewById(R.id.seek_bar_time);
        timeEnd = view.findViewById(R.id.time_end);
        timePlay = view.findViewById(R.id.time_play);
    }

}