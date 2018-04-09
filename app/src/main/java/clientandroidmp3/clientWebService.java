package clientandroidmp3;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class clientWebService {
    public static ResponseEntity startVoice(String phrase) throws IOException, JSONException,HttpClientErrorException {

        String walletBalanceUrl = "http://fbclol.fr:8080/api/voice";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set("phrase",phrase);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Content-Type", "application/json");
//
//        JSONObject json = new JSONObject();
//        json.put("phrase", phrase);
//
//        HttpEntity <String> httpEntity = new HttpEntity <String> (json.toString(), httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.postForEntity(walletBalanceUrl, params, String.class);
        return response;
        // en GET
//        // The connection URL
//        String url = "http://fbclol.fr:8080/api/voice?phrase={phrase}";
//
//        // Create a new RestTemplate instance
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Add the String message converter
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//
//        // Make the HTTP GET request, marshaling the response to a String
//        String result = restTemplate.getForObject(url, String.class, phrase);
//        return result;
    }
}


