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

import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import app.music;

public class MusicMapping extends music{

//  public static final String TAG = com.raywenderlich.alltherecipes.MusicMapping.class.getSimpleName();

//  public String name;
//  public String genre;
//  public String url;
//  public String author;

  public static ArrayList<MusicMapping> getRecipesFromFile(String filename, Context context){
    final ArrayList<MusicMapping> musicMappingList = new ArrayList<>();

    try {
      // Load data
      String jsonString = loadJsonFromAsset("recipes.json", context);
      JSONObject json = new JSONObject(jsonString);
      JSONArray musics = json.getJSONArray("recipes");

      // Get Recipe objects from data
      for(int i = 0; i < musics.length(); i++){
        MusicMapping musicMapping = new MusicMapping();
        musicMapping.name = musics.getJSONObject(i).getString("name");
        musicMapping.genre = musics.getJSONObject(i).getString("genre");
        musicMapping.url = musics.getJSONObject(i).getString("url");
        musicMapping.author = musics.getJSONObject(i).getString("author");
        musicMappingList.add(musicMapping);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return musicMappingList;
  }

  private static String loadJsonFromAsset(String filename, Context context) {
    String json = null;

    try {
      InputStream is = context.getAssets().open(filename);
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");
    }
    catch (java.io.IOException ex) {
      ex.printStackTrace();
      return null;
    }

    return json;
  }

}
