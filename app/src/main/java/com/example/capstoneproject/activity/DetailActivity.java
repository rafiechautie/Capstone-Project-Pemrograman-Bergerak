package com.example.capstoneproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.capstoneproject.R;
import com.example.capstoneproject.model.Result;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "string_extra";
    String imageDetail, imageBackgroundDetail, rilisFilm, title, story;
    ImageView imgFilmDetail;
    KenBurnsView imgFilmBackground;
    TextView judulFilm, nilaiFilm, tanggalRilis, popularitasFilm, filmStory;
    RatingBar nilaiFilmBar;
    Result result;
    double nilai, populer;
    Integer vote;
    Boolean adult, vidio;
    String language;
    List<Integer> genre;

    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //ambil layoutnya
        tanggalRilis = findViewById(R.id.tanggalRilis);
        popularitasFilm = findViewById(R.id.popularitasFilm);
        filmStory = findViewById(R.id.filmStory);
        imgFilmDetail = findViewById(R.id.imgFilmDetail);
        judulFilm = findViewById(R.id.judulFilm);
        nilaiFilm = findViewById(R.id.nilaiFilm);
        nilaiFilmBar = findViewById(R.id.nilaiFilmBar);
        imgFilmBackground = findViewById(R.id.imgFilmBackground);


        result = getIntent().getParcelableExtra(EXTRA_MOVIE);

        //ambil data dari API
        nilai = result.getVoteAverage();
        title = result.getOriginalTitle();
        imageDetail = result.getPosterPath();
        imageBackgroundDetail = result.getPosterPath();
        rilisFilm = result.getReleaseDate();
        story = result.getOverview();
        //populer = result.getPopularity();
        vote = result.getVoteCount();
        adult = result.getAdult();
        vidio = result.getVideo();
        language = result.getOriginalLanguage();
        genre = result.getGenreIds();


        //tarok data api di aplikasi
        nilaiFilm.setText(nilai + "/10");
        judulFilm.setText(title);
        filmStory.setText(story);
        tanggalRilis.setText(rilisFilm);


        //rating film dalam bentuk bintang
        float newValue = (float)nilai;
        nilaiFilmBar.setNumStars(5);
        nilaiFilmBar.setStepSize((float) 0.5);
        nilaiFilmBar.setRating(newValue / 2);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w780/" + imageDetail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgFilmDetail);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w780/" + imageBackgroundDetail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgFilmBackground);


        // Membuat Title pada detail
//        if(getSupportActionBar() != null){
//            getSupportActionBar().setTitle(title);
//        }

        // Tombol Arah panah balik
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}