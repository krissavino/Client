package Client.Commands.Interfaces;

public interface ICommand
{
    void execute();

    void send();

    String getName();

    void setObjectToSend(Object object);

    Object getReceivedObject();
}
