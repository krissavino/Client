package Client.Interfaces;

import Client.Commands.Enums.CommandEnum;
import Client.Commands.Interfaces.ICommand;

import java.net.InetAddress;

public interface IClient
{
    /**
     * @param message отправка сообщения в виде текста серверу
     */
    boolean sendMessage(String message);

    void executeCommand(ICommand command);

    void executeCommand(CommandEnum commandEnum);

    boolean disconnect();

    /**
     * @param inetAddress адрес сервера
     * @param port порт, по которому будет стучаться и общаться клиент с сервером
     */
    boolean tryConnect(InetAddress inetAddress, int port);

    boolean isConnected();
}
