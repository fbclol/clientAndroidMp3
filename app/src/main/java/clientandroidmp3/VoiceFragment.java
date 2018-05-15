package clientandroidmp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;


import app.music;

import static android.app.Activity.RESULT_OK;

public class VoiceFragment extends Fragment {

    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    public View recognitionProgressView;

    ResponseEntity responseEntity = null;
    String titleMusic = "";
    String commande   = "";


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        recognitionProgressView = (View) view.findViewById(R.id.recognition_view);
        txtSpeechInput          = (TextView) view.findViewById(R.id.txtSpeechInput);
        btnSpeak                = (ImageButton) view.findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(VoiceFragment.this.getContext(),
                        Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                } else {
                    promptSpeechInput();
                }
            }
        });

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
            Toast.makeText(recognitionProgressView.getContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
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
                    txtSpeechInput.setText(result.get(0));
                    recognitionProgressView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // appel de la fonction webservice
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            try {
                                responseEntity           = ClientWebService.startVoice(txtSpeechInput.getText().toString());
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

                            // todo : aller sur la musique demandé
                            if (commande.equals("PLAY") && !titleMusic.isEmpty()) {
                                runMusicVocal(titleMusic);
                            } else if (commande.equals("PAUSE")) {

                            } else if (commande.equals("LIST")) {
                                Intent detailIntent = new Intent(getContext(), MainActivity.class);
                                startActivity(detailIntent);
                            } else {
                                Toast.makeText(getContext(),getString(R.string.speech_prompt),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 50);
                }
                break;
            }
        }
    }

    public  void runMusicVocal(String titleMusic){
        System.out.print(titleMusic);
        try {
            music selectedMusicMapping = IceSingleton.instanceIce().searchDocument("name",titleMusic)[0];
            Intent detailIntent = new Intent(recognitionProgressView.getContext(), MusicDetailActivity.class);
            detailIntent.putExtra("title", selectedMusicMapping.name);
            detailIntent.putExtra("url", selectedMusicMapping.author);

            startActivity(detailIntent);
        } catch  (Throwable t) {
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(recognitionProgressView.getContext(), "Requires RECORD_AUDIO permission", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[] { Manifest.permission.RECORD_AUDIO },
                    Config.REQUEST_RECORD_AUDIO_PERMISSION_CODE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_voice, container, false);
    }
}