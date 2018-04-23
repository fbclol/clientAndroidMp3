/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package clientandroidmp3;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.speech.tts.Voice;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import app.ServerPrx;
import app.music;

public class MusicDetailActivity extends AppCompatActivity implements EasyVideoCallback{

  private EasyVideoPlayer player;
  ServerPrx server = IceSingleton.instanceIce();
  private ImageButton btnSpeak;
  ResponseEntity responseEntity = null;
  String titleMusic = "";
  String commande   = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_myplayer);

    // Get music data passed from previous activity
    String title = this.getIntent().getExtras().getString("title");
    String url = this.getIntent().getExtras().getString("url");


    server.LibvlcPlayerStop();
    server.LibvlcPlayerPlay(title+".mp3");
    player = (EasyVideoPlayer) findViewById(R.id.player);
    player.setCallback(this);
    player.setSource(Uri.parse(Config.urlStreamVlc));

    // Set title on action bar of this activity
    setTitle(title);



    btnSpeak = (ImageButton) findViewById(R.id.fab);

    btnSpeak.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if (ContextCompat.checkSelfPermission(v.getContext(),
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
          requestPermission();
        } else {
          promptSpeechInput();
        }
      }
    });
  }

  @Override
  public void onPause()  {
    super.onPause();
    // Make sure the player stops playing if the user presses the home button.

    server.LibvlcPlayerPause();
    if (player.isPlaying() == true) {
      player.pause();
    }
  }

  @Override
  public void onStarted(EasyVideoPlayer player) {

  }

  @Override
  public void onPaused(EasyVideoPlayer player) {

    server.LibvlcPlayerPause();
  }

  @Override
  public void onPreparing(EasyVideoPlayer player) {

  }

  @Override
  public void onPrepared(EasyVideoPlayer player) {

  }

  @Override
  public void onBuffering(int percent) {

  }

  @Override
  public void onError(EasyVideoPlayer player, Exception e) {

  }

  @Override
  public void onCompletion(EasyVideoPlayer player) {

  }

  @Override
  public void onRetry(EasyVideoPlayer player, Uri source) {

  }

  @Override
  public void onSubmit(EasyVideoPlayer player, Uri source) {

  }

  /**
   * Receiving speech input
   * */
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case Config.REQ_CODE_SPEECH_INPUT: {
        if (resultCode == RESULT_OK && null != data) {
          ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//          txtSpeechInput.setText(result.get(0));
          player.postDelayed(new Runnable() {
            @Override
            public void run() {
              // todo: appel de la fonction webservice
              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
              StrictMode.setThreadPolicy(policy);
              try {
                responseEntity           = ClientWebService.startVoice(result.get(0));
                JSONObject jsonObject    = new JSONObject(responseEntity.getBody().toString());
                JSONObject reponseObject = (JSONObject) jsonObject.get("response");
                titleMusic               = reponseObject.getString("NameMusique");
                commande                 = reponseObject.getString("commande");
              } catch (IOException e) {
                e.printStackTrace();
              } catch (JSONException e) {
                e.printStackTrace();
              } catch (HttpClientErrorException e) {
                e.printStackTrace();
              } catch (Throwable t) {
                Log.e("My App", "Could not parse malformed JSON: \"" + responseEntity.getBody().toString() + "\"");
              }

              // aller sur la musique demandé
              if (commande.equals("PLAY") && !titleMusic.isEmpty()) {
                runMusicVocal(titleMusic);
              } else if (commande.equals("PAUSE")) {
                onPaused(player);
              } else if (commande.equals("LIST")) {

                Intent detailIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(detailIntent);

              } else {
                Toast.makeText(getApplicationContext(),getString(R.string.speech_prompt),Toast.LENGTH_SHORT).show();
              }

            }
          }, 50);
        }
        break;
      }
    }
  }

  public  void runMusicVocal(String titleMusic){

    music selectedMusicMapping = IceSingleton.instanceIce().searchDocument("name",titleMusic)[0];

    Intent detailIntent = new Intent(this, MusicDetailActivity.class);
    detailIntent.putExtra("title", selectedMusicMapping.name);
    detailIntent.putExtra("url", selectedMusicMapping.author);

    startActivity(detailIntent);
  }

  /**
   * Showing google speech input dialog
   * */
  private void promptSpeechInput() {
    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,getString(R.string.speech_prompt));
    try {
      startActivityForResult(intent, Config.REQ_CODE_SPEECH_INPUT);
    } catch (ActivityNotFoundException a) {
      Toast.makeText(getApplicationContext(),
              getString(R.string.speech_not_supported),
              Toast.LENGTH_SHORT).show();
    }
  }


  private void requestPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this.getParent(),
            Manifest.permission.RECORD_AUDIO)) {
      Toast.makeText(player.getContext(), "Requires RECORD_AUDIO permission", Toast.LENGTH_SHORT).show();
    } else {
      ActivityCompat.requestPermissions(this.getParent(),
              new String[] { Manifest.permission.RECORD_AUDIO },
              Config.REQUEST_RECORD_AUDIO_PERMISSION_CODE);
    }
  }
}
