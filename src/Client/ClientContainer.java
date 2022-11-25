package Client;


import Client.Interfaces.IClient;
import Client.Interfaces.IClientContainer;

public class ClientContainer implements IClientContainer
{
    private static IClient client;

    public static IClient getClient()
    {
        if(client == null)
            client = new Client();

        return client;
    }
}
