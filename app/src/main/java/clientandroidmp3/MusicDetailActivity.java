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

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;

import app.ServerPrx;

public class MusicDetailActivity extends AppCompatActivity implements EasyVideoCallback{

  public static final String TAG = MusicDetailActivity.class.getSimpleName();
  private EasyVideoPlayer player;
  private static final String URL = "http://"+IceSingleton.ipServeur+":"+IceSingleton.portServeur+"/sample.mp3";
  private WebView mWebView;
  ServerPrx server = IceSingleton.instanceIce();
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
    player.setSource(Uri.parse(URL));

    // Set title on action bar of this activity
    setTitle(title);
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
}
