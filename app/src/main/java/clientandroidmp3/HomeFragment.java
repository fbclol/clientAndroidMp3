package clientandroidmp3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.easyvideoplayer.EasyVideoPlayer;

import app.*;

import java.util.ArrayList;

import app.ServerPrx;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ListView mListView;
    final ServerPrx server = IceSingleton.instanceIce();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Context context = this.getContext();

        final ArrayList<MusicMapping> musicList = new ArrayList<>();

        for (music music : server.displayListMusic()) {
            MusicMapping musicMapping = new MusicMapping();

            musicMapping.name = music.name;
            musicMapping.genre = music.genre;
            musicMapping.url = music.url;
            musicMapping.author = music.author;
            musicList.add(musicMapping);
            System.out.println(music.name + "         " + music.genre + "      " + music.author + "           " + music.url);
        }

        // Create adapter
        MusicAdapter adapter = new MusicAdapter(context, musicList);

        // Create list view
        mListView = (ListView) view.findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);

        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicMapping selectedMusicMapping = musicList.get(position);

                Intent detailIntent = new Intent(context, RecipeDetailActivity.class);
                detailIntent.putExtra("title", selectedMusicMapping.name);
                detailIntent.putExtra("url", selectedMusicMapping.author);


                startActivity(detailIntent);
            }

        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_list_musics, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}
