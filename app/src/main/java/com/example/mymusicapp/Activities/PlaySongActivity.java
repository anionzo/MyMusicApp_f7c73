package com.example.mymusicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.io.IOException;
import java.util.ArrayList;

public class PlaySongActivity extends AppCompatActivity {

    private ImageView addPlayList,shareSong;
    private ImageView back, help, imageSong;
    private ImageView repeatSong,backSong, playSong, nextSong, randomSong;
    private TextView songTitle, songSinger;
    private SeekBar seekBarTime;
    static MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private int currentIndex = 0;
    private Thread musicThread;
    ArrayList<SongModel> songs = new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer != null)
            mediaPlayer.stop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        initView();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        songs = getSongs();
        Bundle bundle = getIntent().getExtras();
        SongModel song = (SongModel) bundle.getSerializable("itemSong");
        if(song != null)
        {
            String url = "https://mymusicappf7c73s.000webhostapp.com/Music/Sao-Minh-Chua-Nam-Tay-Nhau-Yan-Nguyen.mp3";
            //mediaPlayer= MediaPlayer.create(getApplicationContext(),song.getLinkSong());
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        //Phát nhạc
        playSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBarTime.setMax(mediaPlayer.getDuration());
                if( mediaPlayer != null && mediaPlayer.isPlaying() ){
                    mediaPlayer.pause();
                    playSong.setImageResource(R.drawable.ic_play);
                }
                else
                {
                    mediaPlayer.start();
                    playSong.setImageResource(R.drawable.ic_stop);
                }
                if (currentIndex == -99)
                {
                    getPlaySong(song);
                }else
                    getPlaySong(songs.get(currentIndex));

            }
        });

        // Bài tiếp theo
        nextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null){
                    playSong.setImageResource(R.drawable.ic_stop);
                }
                if (currentIndex == -99)
                {
                    currentIndex =0;
                }
                if (currentIndex < songs.size() -1){
                    currentIndex++;
                }else{
                    currentIndex = 0;
                }

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                mediaPlayer= MediaPlayer.create(getApplicationContext(),songs.get(currentIndex).getLinkSong());
                mediaPlayer.start();
                getPlaySong(songs.get(currentIndex));

            }
        });

        // back song
         backSong.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
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
                 }
                 mediaPlayer= MediaPlayer.create(getApplicationContext(),songs.get(currentIndex).getLinkSong());
                 mediaPlayer.start();
                 getPlaySong(songs.get(currentIndex));
             }
         });

        // Back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null)
                     mediaPlayer.stop();
                finish();
            }
        });

    }

    private  ArrayList<SongModel> getSongs(){
        ArrayList<SongModel> songs = new ArrayList<>();
        songs.add(new SongModel(0,"3","3","Hỉ",getString(R.string.url_img),"Thập Đẳng Ma Quân",R.raw.hi));
        songs.add(new SongModel(1,"3","3","Sao mình chưa nắm tay nhau",getString(R.string.url_img2),"Hạ 2",R.raw.sao_minh_chua_nam_tay_nhau));
        songs.add(new SongModel(2,"3","3","Siêu cô đơn",getString(R.string.url_img1),"Yan Nguyễn",R.raw.sieu_co_don));
        songs.add(new SongModel(3,"3","3","Thập Đẳng Ma Quân",getString(R.string.url_img3),"Thập đẳng Ma Quân",R.raw.thap_dang_ma_quan));
        songs.add(new SongModel(4,"3","3","Thiên Nam Ca",getString(R.string.url_img4),"Thập Đẳng Ma Quân",R.raw.thien_nam_ca));
        return songs;
    }

    private void getPlaySong(SongModel song){
        songSinger.setText(song.getNameSinger());
        songTitle.setText(song.getNameSong());
        //imageSong.setImageResource(song.getLinkImg());

        Glide.with(this)
                .load(song.getLinkImg())
                .centerCrop()
                .into(imageSong);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBarTime.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
            }
        });

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
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition();
                    seekBarTime.setProgress(mCurrentPosition);

                }
                new Handler().postDelayed(this,1000);
            }
        },1000);
    }

    private void initView(){
        addPlayList = findViewById(R.id.add_play_list);
        shareSong = findViewById(R.id.share_song);
        back = findViewById(R.id.back);
        help = findViewById(R.id.help);
        imageSong = findViewById(R.id.image_song);
        repeatSong = findViewById(R.id.repeat_song);
        backSong = findViewById(R.id.back_song);
        playSong = findViewById(R.id.play_song);
        nextSong = findViewById(R.id.next_song);
        randomSong =findViewById(R.id.random_song);
        songTitle = findViewById(R.id.song_title);
        songSinger = findViewById(R.id.song_singer);
        seekBarTime = findViewById(R.id.seek_bar_time);
    }
}