package Client;

import Client.Interfaces.IClient;
import Client.Poker.Window.AuthorizeWindowContainer;
import Net.ServerInformation;

public class Main
{
    public static void main(String[] args)
    {
        AuthorizeWindowContainer.getAuthorizeWindow();
    }

    public static boolean tryConnectClient(IClient client, int port)
    {
        var isConnected = client.isConnected();
        var localAddresses = ServerInformation.ServerInetAddress;

        isConnected = client.tryConnect(localAddresses, port);

        return isConnected;
    }
}
