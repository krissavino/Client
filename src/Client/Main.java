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

public class Main
{
    public static void main(String[] args)
    {
        AuthorizeWindowContainer.getAuthorizeWindow();
        /*var client = ClientContainer.getClient();
        boolean isConnected = tryConnectClient(client, 2121);

        if(isConnected == false)
        {
            System.out.println("Клиенту не удалось подключиться к серверу");
            return;
        }

        PlayerModel player = CreatePokerPlayer("Player"+(Calendar.getInstance().getTime().getSeconds()));
        PokerContainer.getPoker().setCurrentPlayer(player);
        ICommand registerCommand = new RegisterPokerPlayer();
        registerCommand.send();*/
    }

    private static PlayerModel CreatePokerPlayer(String nickName)
    {
        var poker = PokerContainer.getPoker();
        var currentPlayer = poker.getCurrentPlayer();
        currentPlayer = new PlayerModel();
        currentPlayer.NickName = nickName;
        return currentPlayer;
    }

    public static boolean tryConnectClient(IClient client, int port)
    {
        var isConnected = client.isConnected();
        var localAddresses = LocalNetManager.GetLocalInetAddresses();

        for (InetAddress address : localAddresses)
        {
            isConnected = client.tryConnect(address, port);

            if (isConnected == false)
                continue;

            ServerInformation.ServerInetAddress = address;
            return isConnected;
        }
        return isConnected;
    }
}
