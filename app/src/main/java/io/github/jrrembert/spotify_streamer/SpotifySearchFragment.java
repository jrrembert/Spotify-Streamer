package io.github.jrrembert.spotify_streamer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpotifySearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpotifySearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpotifySearchFragment extends Fragment {

    private ArrayAdapter<String> mSpotifyArtistAdapter;

    public SpotifySearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
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
        View rootView = inflater.inflate(R.layout.artist_search, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_artist_results);
        listView.setAdapter(mSpotifyArtistAdapter);

        return rootView;
    }
}
