package clientandroidmp3;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.afollestad.materialdialogs.MaterialDialog;
import app.*;

public class PlayerFragment extends Fragment implements EasyVideoCallback,AdapterView.OnItemSelectedListener  {
    //    private static final String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private EasyVideoPlayer player;
    static final String ipServeur = IceSingleton.ipServeur; // moi
    static final int portServeur = IceSingleton.portServeur;
//    private static final String TEST_URL = "http://"+ipServeur+":"+portServeur+"/MINIONS.mp3";
    private static final String URL = "http://"+ipServeur+":"+portServeur+"/sample.mp3";

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_myplayer);
////        // Grabs a reference to the player view
////        player = (EasyVideoPlayer) findViewById(R.id.player);
////
////        // Sets the callback to this Activity, since it inherits EasyVideoCallback
////        player.setCallback(this);
////        ServerPrx server = instanceIce();
////        //                music oneMusic = new music("rger", "freg", "fre", "fref");
//////        System.out.println(server.addDocument(oneMusic));
////        for (music music : server.displayListMusic()) {
////            System.out.println(music.name + "         " + music.genre + "      " + music.author + "           " + music.url);
////        }
//        server.LibvlcPlayerPlay();
//        // Sets the source to the HTTP URL held in the TEST_URL variable.
//        // To play files, you can use Uri.fromFile(new File("..."))
//        player.setSource(Uri.parse(TEST_URL));
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Grabs a reference to the player view
        player = (EasyVideoPlayer) view.findViewById(R.id.player);

        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        player.setCallback(this);
        ServerPrx server = IceSingleton.instanceIce();
        //                music oneMusic = new music("rger", "freg", "fre", "fref");
//        System.out.println(server.addDocument(oneMusic));
        for (music music : server.displayListMusic()) {
            System.out.println(music.name + "         " + music.genre + "      " + music.author + "           " + music.url);
        }

        //server.LibvlcPlayerPlay();
        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        player.setSource(Uri.parse(URL));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_myplayer, container, false);

    }

    @Override
    public void onPause()  {
        super.onPause();
        // Make sure the player stops playing if the user presses the home button.
        if (player.isPlaying() == true) {
            player.pause();
        }
    }

    // Methods for the implemented EasyVideoCallback
    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        Log.d("EVP-Sample", "onError(): " + e.getMessage());
//        new MaterialDialog.Builder(this).title(R.string.error).content(e.getMessage()).positiveText(android.R.string.ok).show();
    }


    @Override
    public void onStarted(EasyVideoPlayer player) {
        // TODO handle if needed
//        ServerPrx server = instanceIce();
////        server.LibvlcPlayerStop();
////        server.LibvlcPlayerPlay();
//        // Grabs a reference to the player view
//        player = (EasyVideoPlayer) findViewById(R.id.player);
//
//        // Sets the callback to this Activity, since it inherits EasyVideoCallback
//        player.setCallback(this);
//
//        // Sets the source to the HTTP URL held in the TEST_URL variable.
//        // To play files, you can use Uri.fromFile(new File("..."))
//        player.setSource(Uri.parse(TEST_URL));
    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
        // TODO handle if needed
    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        Log.d("EVP-Sample", "onCompletion()");
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
//        Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {
//        Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();
    }


    public void onClickVideoFrame(EasyVideoPlayer player) {
//        Toast.makeText(this, "Click video frame.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {
        Log.d("EVP-Sample", "onPreparing()");
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
        Log.d("EVP-Sample", "onPrepared()");
    }

    @Override
    public void onBuffering(int percent) {
        Log.d("EVP-Sample", "onBuffering(): " + percent + "%");
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
