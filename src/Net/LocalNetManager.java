package Net;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;

public class LocalNetManager
{


    public static ArrayList<InetAddress> GetLocalInetAddresses()
    {
        ArrayList<NetworkInterface> networkInterfaces;
        ArrayList<InetAddress> localInetAddresses = new ArrayList();

        try
        {
            networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());

            for(var netInterface : networkInterfaces)
            {
                for(var inetAddress : Collections.list(netInterface.getInetAddresses()))
                {
                    if(inetAddress instanceof Inet4Address)
                        localInetAddresses.add(inetAddress);
                }
            }
        }
        catch (SocketException e)
        {
            throw new RuntimeException(e);
        }

        return localInetAddresses;
    }

    public static Socket tryConnect(InetAddress inetAddress, int port)
    {
        byte[] address_bytesArray = inetAddress.getAddress();
        Socket socket = new Socket();

        for (int counter = 0; counter < 256; counter++)
        {
            try
            {
                address_bytesArray[3] = (byte)counter;
                var server = new InetSocketAddress(InetAddress.getByAddress(address_bytesArray), port);

                socket.connect(server, 10);

                if (socket.isConnected())
                    return socket;
            }
            catch (IOException e){}

            socket = new Socket();
        }

        return socket;
    }
}
