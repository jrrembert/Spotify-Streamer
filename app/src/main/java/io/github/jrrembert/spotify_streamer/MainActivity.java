/*
 *  Author: J. Ryan Rembert
 *  Project: Spotify-Streamer
 *  Source: https://github.com/jrrembert/Spotify-Streamer
 *
 *  Copyright (C) 2015 J. Ryan Rembert. All rights reserved.
 *
 *  Redistribution of source code perfectly cool as long as the
 *  above copyright notice is provided and you don't sue me if
 *  something (somehow) explodes. Unless it explodes into a
 *  rainbow of mutant dinosaurs made out of cookie batter.
 *  Then I assume complete credit.
 *
 */

package io.github.jrrembert.spotify_streamer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SpotifySearchFragment())
                    .commit();
        }

        SpotifySearchTask searchTask = new SpotifySearchTask();
        searchTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SpotifySearchTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SpotifyApi api = new SpotifyApi();
            SpotifyService spotify = api.getService();

            ArtistsPager artistResults = spotify.searchArtists("Paul");
            List<Artist> artists = artistResults.artists.items;
            for (int i = 0; i < artists.size(); i++) {
                Artist artist = artists.get(i);
                Log.v(LOG_TAG, "Artist name: " + artist.name);
            }

            return null;
        }
    }

}
