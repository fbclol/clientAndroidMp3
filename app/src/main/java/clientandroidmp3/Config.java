package clientandroidmp3;

/**
 * Created by franck on 23/04/2018.
 */

public class Config {
    //    static final String ipServeur = "192.168.42.210"; // moi
    //    static final String ipServeur = "192.168.0.17"; //momo parent
    //    static final String ipServeur = "192.168.0.17"; //momo appart
//        static final String ipServeur = "10.126.3.125"; // ecole
    //    static final String ipServeur = "192.168.42.84"; // ipTelephone
        static final String ipServeur = "fbclol.fr"; // ip du domaine fbclol.fr (production)
//    static final String ipServeur = "192.168.1.18"; // moi
//    static final String ipServeur = "192.168.42.210"; // moi

    static final String ipIce           = ipServeur;
    static final int portIce            = 10000;
    static final String MessageSizeMax  = "100024";

    static final String ipStreamVlc          = ipServeur;
    static final int portStreamVlc           = 8090;
    static final String nameDefaultMusic     = "sample";
    static final String urlStreamVlc = "http://"+ipStreamVlc+":"+portStreamVlc+"/"+nameDefaultMusic+".mp3";

    static final String ipWebService         = ipServeur;
    static final int portWebService          = 8080;
    static final String pathApiVoice         = "/api/voice";
    static final String urlWebServiceApiVoice = "http://"+ipWebService+":"+portWebService+pathApiVoice;

    static final String urlGitHub = "https://github.com/fbclol/mp3Ice";


    static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    static final int REQ_CODE_SPEECH_INPUT = 100;

}
