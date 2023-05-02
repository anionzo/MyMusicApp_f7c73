package com.example.mymusicapp.Fragments;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mymusicapp.Models.MediaPlayerSingleton;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PlaySongFragment extends Fragment {
    private ImageView addPlayList, shareSong;
    private ImageView back, help, imageSong;
    private ImageView repeatSong, backSong, playSong, nextSong, randomSong;
    private TextView songTitle, songSinger, timePlay, timeEnd;
    private SeekBar seekBarTime;
    private int currentIndex = 0;
    ArrayList<SongModel> songs = new ArrayList<>();
    private MediaPlayer mediaPlayer = new MediaPlayer();

    public PlaySongFragment() {
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

        mediaPlayer = MediaPlayerSingleton.getInstance().getMediaPlayer();

        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (song != null) {
            getPlaySong(song);
            new PlayMP3().execute(song.getLinkSong());
        }

        playSong.setOnClickListener(listener);
        nextSong.setOnClickListener(listener);
        backSong.setOnClickListener(listener);
        back.setOnClickListener(listener);
        repeatSong.setOnClickListener(listener);
        MediaPlayerSingleton.getInstance().setMediaPlayer(mediaPlayer);
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
                    if (mediaPlayer != null)
                        playSong.setImageResource(R.drawable.ic_stop);
                    if (currentIndex < songs.size() - 1)
                        currentIndex++;
                    else
                        currentIndex = 0;

                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                    getPlaySong(songs.get(currentIndex));
                    new PlayMP3().execute(songs.get(currentIndex).getLinkSong());
                }
                break;

                case R.id.back_song: {
                    if (mediaPlayer != null)
                        playSong.setImageResource(R.drawable.ic_stop);
                    if (currentIndex > 0)
                        currentIndex--;
                    else currentIndex = songs.size() - 1;
                    assert mediaPlayer != null;
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                    getPlaySong(songs.get(currentIndex));
                    new PlayMP3().execute(songs.get(currentIndex).getLinkSong());
                }
                break;

                case R.id.back: {
                    requireActivity().finish();
                }
                break;
                case R.id.repeat_song: {
                    Drawable drawable = repeatSong.getDrawable();
                    if (mediaPlayer == null)
                        return;
                    if (mediaPlayer.isLooping()) {
                        mediaPlayer.setLooping(false);
                        drawable.setColorFilter(ContextCompat.getColor(getContext(), R.color.whiteSong), PorterDuff.Mode.SRC_IN);
                        repeatSong.setImageDrawable(drawable);
                    } else {
                        mediaPlayer.setLooping(true);
                        drawable.setColorFilter(ContextCompat.getColor(getContext(), R.color.starColor), PorterDuff.Mode.SRC_IN);
                        repeatSong.setImageDrawable(drawable);
                    }
                }
                break;
                case R.id.random_song: {

                }
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        timeEnd.setText(simpleDateFormat.format(mediaPlayer.getDuration()));

        seekBarTime.setMax(mediaPlayer.getDuration());
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
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
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition();
                    seekBarTime.setProgress(mCurrentPosition);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    timePlay.setText(simpleDateFormat.format(mCurrentPosition));
                }
                new Handler().postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void getPlaySong(SongModel song) {
        songSinger.setText(song.getNameSinger());
        songTitle.setText(song.getNameSong());
        Glide.with(requireContext())
                .load(song.getLinkImg())
                .centerCrop()
                .into(imageSong);
        senNotificatinoMedia(song);
    }

    class PlayMP3 extends AsyncTask<String, Void, String> {
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
                        nextSong.performClick();
                    }
                });

                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                playSong.setImageResource(R.drawable.ic_stop);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            MediaPlayerSingleton.getInstance().setMediaPlayer(mediaPlayer);
            TimeSong();
        }
    }

    private void senNotificatinoMedia(SongModel song) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(song.getLinkImg());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
            // Sử dụng biến bitmap tại đây
        } catch (IOException e) {
            e.printStackTrace();
        }

        MediaSessionCompat mediaSession = new MediaSessionCompat(getContext(), "tag");
        PendingIntent previousPendingIntent = null;
        PendingIntent pausePendingIntent = null;
        PendingIntent nextPendingIntent = null;
        Notification notification = new NotificationCompat.Builder(getContext(), com.example.mymusicapp.Services.Notification.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_app_icon)
                .setSubText("Mi Miêu")
                .setContentTitle("Phát: "+song.getNameSong())
                .setContentText(song.getNameSinger())
                .setLargeIcon(bitmap)
                .setPriority(NotificationCompat.PRIORITY_MAX) // Đặt độ ưu tiên cao nhất
                .setOngoing(true) // Đặt thông báo là "ongoing" để ngăn người dùng có thể xóa thông báo
                .addAction(R.drawable.ic_back_song, "Previous", previousPendingIntent) // #0
                .addAction(R.drawable.ic_stop, "Pause", pausePendingIntent)  // #1
                .addAction(R.drawable.ic_next_song, "Next", nextPendingIntent)     // #2
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mediaSession.getSessionToken())
                        .setShowActionsInCompactView(0, 1, 2))
                .build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("SSP","Chưa có quyền");
            return;
        }
        managerCompat.notify(1, notification);
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