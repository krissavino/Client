package Client;

import Client.Commands.Interfaces.ICommand;
import Client.Commands.RegisterPokerPlayer;
import Client.Interfaces.IClient;
import Client.Poker.Models.PlayerModel;
import Client.Poker.PokerContainer;
import Client.Poker.Window.AuthorizeWindow;
import Client.Poker.Window.AuthorizeWindowContainer;
import Client.Poker.Window.GameWindow;
import Net.LocalNetManager;
import Net.ServerInformation;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

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
