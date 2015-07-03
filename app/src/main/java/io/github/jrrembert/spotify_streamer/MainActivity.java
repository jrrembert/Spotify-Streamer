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

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static class SpotifyArtists extends Fragment {
        private ArrayAdapter<String> mSpotifyArtistAdapter;

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            String[] data = {
                    "Coldplay",
                    "And You Will Know Us By the Trail of the Dead",
                    "Veruca Salt",
                    "U2",
                    "Pixies",
                    "Glass Animals",
                    "Sam Cooke"
            };

            List<String> artistResults = new ArrayList<String>(Arrays.asList(data));

            mSpotifyArtistAdapter =
                    new ArrayAdapter<String>(
                            getActivity(),
                            R.layout.list_item_artist_search_item,
                            R.id.textview_artist_name,
                            artistResults
                    );
            View rootView = inflater.inflate(R.layout.artist_search, container);

            ListView listView = (ListView) rootView.findViewById(R.id.listview_artist_results);
            listView.setAdapter(mSpotifyArtistAdapter);

            return rootView;
        }
    }
}
