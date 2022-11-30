package Client;

import Client.Commands.*;
import Client.Commands.Enums.CommandEnum;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.Interfaces.ISimpleCommand;
import Client.Commands.Models.SimpleCommandModel;
import Client.Interfaces.IClient;
import Client.Models.ClientModel;
import Client.Poker.Models.PlayerModel;
import Client.Poker.PokerContainer;
import Net.LocalNetManager;
import Net.ServerInformation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

public final class Client implements IClient
{
    private ClientModel Client = new ClientModel();

    public boolean disconnect()
    {
        try
        {
            this.Client.BufferedReader.close();
            this.Client.BufferedWriter.close();
            this.Client.Socket.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean tryConnect(InetAddress inetAddress, int port)
    {
        Client.Port = port;
        try
        {
            Client.Socket = LocalNetManager.tryConnect(inetAddress, Client.Port);

            if(Client.Socket.isConnected() == false)
                return false;

            Client.BufferedReader = new BufferedReader(new InputStreamReader(Client.Socket.getInputStream()));
            Client.BufferedWriter = new PrintWriter(this.Client.Socket.getOutputStream(), true);

            new Thread(()-> listenForMessages()).start();
        }
        catch (Exception exception)
        {
            return false;
        }
        return true;
    }

    public boolean isConnected()
    {
        return this.Client.Socket.isConnected();
    }

    private void listenForMessages()
    {
        String message = null;

        while (true)
        {
            try
            {
                message = this.Client.BufferedReader.readLine();
                var commandEnum = tryGetCommand(message);
                executeCommand(commandEnum);
            }
            catch (IOException e)
            {
                if(e.getMessage() == "Connection reset" == true)
                {
                    ServerInformation.ServerInetAddress = null;
                    System.out.println("Сервер принудительно отключил связ");
                    return;
                }
                throw new RuntimeException(e);
            }
        }
    }

    public boolean sendMessage(String message)
    {
        if(Client.Socket.isConnected() == false)
            return false;

        Client.BufferedWriter.println(message);

        return true;
    }

    private ICommand tryGetCommand(String jsonText)
    {
        Gson gson = new Gson();
        ISimpleCommand jCommand = gson.fromJson(jsonText, new TypeToken<SimpleCommandModel>(){}.getType());
        ICommand command = new Empty();

        for(var commandEnum : CommandEnum.values())
        {
            if (jCommand.getName().equals(commandEnum.toString()) == false)
                continue;

            if (Client.Commands.get(commandEnum) == null)
                continue;

            command = gson.fromJson(jsonText, Client.Commands.get(commandEnum).getClass());
            break;
        }
        return command;
    }

    public void executeCommand(ICommand command)
    {
        var commandName = command.getName();
        System.out.println(commandName);
        command.execute();
    }

    public void executeCommand(CommandEnum commandEnum)
    {
        var command = Client.Commands.get(commandEnum);
        var commandName = command.getName();
        System.out.println(commandName);
        command.execute();
    }
}
