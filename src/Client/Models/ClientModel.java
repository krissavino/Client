package Client.Models;

import Client.Commands.*;
import Client.Commands.Enums.CommandEnum;
import Client.Commands.Interfaces.ICommand;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public final class ClientModel
{
    public Socket Socket = new Socket();
    public BufferedReader BufferedReader;
    public PrintWriter BufferedWriter;
    public Map<CommandEnum, ICommand> Commands = new HashMap();
    public int Port = 2121;

    public ClientModel()
    {
        Commands.put(CommandEnum.Empty,new Empty());
        Commands.put(CommandEnum.PlaceOnTable,new PlaceOnTable());
        Commands.put(CommandEnum.RegisterPokerPlayer,new RegisterPokerPlayer());
        Commands.put(CommandEnum.GiveAwayCards,new GiveAwayCards());
        Commands.put(CommandEnum.PlayerMove,new PlayerMove());
        Commands.put(CommandEnum.UpdateInfo,new UpdateInfo());
    }
}
