package Client.Commands.Interfaces;

public interface ICommand
{
    String getCommandName();

    void executeOnClient();

    void sendToServer();

    void setObjectToSend(Object object);

    Object getReceivedObject();
}
