package Client;

import Client.Commands.*;
import Client.Commands.Enums.CommandEnum;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.Interfaces.ISimpleCommand;
import Client.Commands.Models.SimpleCommandModel;
import Client.Interfaces.IClient;
import Client.Models.ClientModel;
import Client.Poker.Window.AuthorizeWindowContainer;
import Client.Poker.Window.GameWindowContainer;
import Client.Poker.Window.WaitingWindowContainer;
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
    private static ClientModel Client = new ClientModel();
    private static Thread clientTread = null;
    private ThreadController ThreadController = new ThreadController();

    public boolean disconnect()
    {
        try
        {
            ThreadController.Cancel();
            this.Client.BufferedReader.close();
            this.Client.BufferedWriter.close();
            this.Client.Socket.close();
            clientTread.interrupt();

            AuthorizeWindowContainer.closeWindow();
            GameWindowContainer.closeWindow();
            WaitingWindowContainer.closeWindow();

            var text = String.format("Клиент отключился от сервера");
            System.out.println(text);

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

            clientTread = new Thread(()-> listenForMessages());
            clientTread.start();
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

        while (ThreadController.isCancellationRequested() == false)
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
                if(e.getMessage() == "Stream closed" == true)
                {
                    ServerInformation.ServerInetAddress = null;
                    System.out.println("Связь с сервером прервалась");
                    return;
                }
                return;
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

        if(jCommand == null)
            return command;

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
        var commandName = command.getCommandName();
        var text = String.format("Комманда от сервера: %s", commandName);
        System.out.println(text);
        command.executeOnClient();
    }

    public void executeCommand(CommandEnum commandEnum)
    {
        var command = Client.Commands.get(commandEnum);
        var commandName = command.getCommandName();
        System.out.println(commandName);
        command.executeOnClient();
    }
}
