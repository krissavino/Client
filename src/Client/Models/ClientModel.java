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
        Commands.put(CommandEnum.Disconnect,new Disconnect());
        Commands.put(CommandEnum.UpdateInfo,new UpdateInfo());
        Commands.put(CommandEnum.RegisterPokerPlayer,new RegisterPokerPlayer());

        Commands.put(CommandEnum.MoveBet,new MoveBet());
        Commands.put(CommandEnum.MoveCall,new MoveCall());
        Commands.put(CommandEnum.MoveCheck,new MoveCheck());
        Commands.put(CommandEnum.MoveFold,new MoveFold());
        Commands.put(CommandEnum.MoveRaise,new MoveRaise());
    }
}
