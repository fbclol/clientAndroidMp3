package clientandroidmp3;

import app.ServerPrx;

/**
 * Created by franck on 04/03/2018.
 */

public class IceSingleton {
    static final String ipServeur = "192.168.1.18"; // moi
    static final int portServeur = 8090;
    public static ServerPrx instanceIce() {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize()) {
            com.zeroc.Ice.Properties datasize = com.zeroc.Ice.Util.createProperties();
            datasize.setProperty("Ice.MessageSizeMax", "100024");
            com.zeroc.Ice.InitializationData initData = new com.zeroc.Ice.InitializationData();
            initData.properties = datasize;
            com.zeroc.Ice.Communicator ic = com.zeroc.Ice.Util.initialize(initData);

            com.zeroc.Ice.ObjectPrx base = ic.stringToProxy("Server:tcp -h " + ipServeur + " -p 10000");
            ServerPrx server = ServerPrx.checkedCast(base);
            if (server == null) {
                throw new Error("Invalid proxy");
            }
            else {
                return server;
            }
        }
    }
}
