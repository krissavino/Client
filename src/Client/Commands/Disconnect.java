package Client.Commands;


import Client.ClientContainer;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;

public class Disconnect extends SimpleCommandModel implements ICommand
{
    public Disconnect() { Name = this.getClass().getSimpleName(); }

    public String getCommandName() { return Name; }

    public void setObjectToSend(Object object) {}

    public Object getReceivedObject() { return null; }

    public void executeOnClient()
    {
        var client = ClientContainer.getClient();
        client.disconnect();
    }

    public void sendToServer() {}
}
