package clientandroidmp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import android.Manifest;
import android.app.Activity;
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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import app.music;

import static android.app.Activity.RESULT_OK;

public class VoiceFragment extends Fragment {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    private final int REQ_CODE_SPEECH_INPUT = 100;

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
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
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
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    recognitionProgressView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // todo: appel de la fonction webservice
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            try {
                                responseEntity           = clientWebService.startVoice(txtSpeechInput.getText().toString());
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
                            if (!commande.isEmpty() && !titleMusic.isEmpty()) {
                                runMusicVocal(titleMusic);
                            }
                        }
                    }, 50);
                }
                break;
            }
        }
    }

    public  void runMusicVocal(String titleMusic){
//         music selectedMusicMapping = HomeFragment.server.searchDocument("name",txtSpeechInput.getText().toString())[0];
         music selectedMusicMapping = HomeFragment.server.searchDocument("name",titleMusic)[0];
//        music selectedMusicMapping = HomeFragment.musicList.get();

        Intent detailIntent = new Intent(recognitionProgressView.getContext(), MusicDetailActivity.class);
        detailIntent.putExtra("title", selectedMusicMapping.name);
        detailIntent.putExtra("url", selectedMusicMapping.author);

        startActivity(detailIntent);
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(recognitionProgressView.getContext(), "Requires RECORD_AUDIO permission", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[] { Manifest.permission.RECORD_AUDIO },
                    REQUEST_RECORD_AUDIO_PERMISSION_CODE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_voice, container, false);
    }
}











//package clientandroidmp3;
///*
// * Copyright (C) 2016 Evgenii Zagumennyi
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.speech.RecognizerIntent;
//import android.speech.SpeechRecognizer;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.Toast;
//import com.github.zagum.speechrecognitionview.RecognitionProgressView;
//import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;
//import java.util.ArrayList;
//
//public class VoiceFragment extends Fragment {
//    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
//
//    private SpeechRecognizer speechRecognizer;
//    public RecognitionProgressView recognitionProgressView;
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        recognitionProgressView = (RecognitionProgressView) view.findViewById(R.id.recognition_view);
//
//        int[] colors = {
//                ContextCompat.getColor(recognitionProgressView.getContext(), R.color.color1),
//                ContextCompat.getColor(recognitionProgressView.getContext(), R.color.color2),
//                ContextCompat.getColor(recognitionProgressView.getContext(), R.color.color3),
//                ContextCompat.getColor(recognitionProgressView.getContext(), R.color.color4),
//                ContextCompat.getColor(recognitionProgressView.getContext(), R.color.color5)
//        };
//
//        int[] heights = { 20, 24, 18, 23, 16 };
//
//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(recognitionProgressView.getContext());
//
//
//        recognitionProgressView.setSpeechRecognizer(speechRecognizer);
//        recognitionProgressView.setRecognitionListener(new RecognitionListenerAdapter() {
//            @Override
//            public void onResults(Bundle results) {
//                showResults(results);
//            }
//        });
////        recognitionProgressView.setColors(colors);
//        recognitionProgressView.setBarMaxHeightsInDp(heights);
//        recognitionProgressView.setCircleRadiusInDp(2);
//        recognitionProgressView.setSpacingInDp(2);
//        recognitionProgressView.setIdleStateAmplitudeInDp(2);
//        recognitionProgressView.setRotationRadiusInDp(10);
//        recognitionProgressView.play();
//
//        Button listen = (Button) view.findViewById(R.id.listen);
//        Button reset = (Button) view.findViewById(R.id.reset);
//
//        listen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(VoiceFragment.this.getContext(),
//                        Manifest.permission.RECORD_AUDIO)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    requestPermission();
//                } else {
//                    startRecognition();
//                    recognitionProgressView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            startRecognition();
//                        }
//                    }, 50);
//                }
//            }
//        });
//
//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recognitionProgressView.stop();
//                recognitionProgressView.play();
//            }
//        });
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        return inflater.inflate(R.layout.activity_voice, container, false);
//    }
//
//
////    @Override
////    protected void onDestroy() {
////        if (speechRecognizer != null) {
////            speechRecognizer.destroy();
////        }
////        super.onDestroy();
////    }
//
//    private void startRecognition() {
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
////        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr");
//        speechRecognizer.startListening(intent);
//    }
//
//    private void showResults(Bundle results) {
//        ArrayList<String> matches = results
//                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//        Toast.makeText(recognitionProgressView.getContext(), matches.get(0), Toast.LENGTH_LONG).show();
//    }
//
//    private void requestPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
//                Manifest.permission.RECORD_AUDIO)) {
//            Toast.makeText(recognitionProgressView.getContext(), "Requires RECORD_AUDIO permission", Toast.LENGTH_SHORT).show();
//        } else {
//            ActivityCompat.requestPermissions(this.getActivity(),
//                    new String[] { Manifest.permission.RECORD_AUDIO },
//                    REQUEST_RECORD_AUDIO_PERMISSION_CODE);
//        }
//    }
//}