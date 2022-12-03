package Client.Commands;

import Client.ClientContainer;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;
import Client.Poker.Models.PlayerModel;
import Client.Poker.PokerContainer;
import Json.JsonConverter;

public class MoveBet extends SimpleCommandModel implements ICommand
{
    int bet;
    public MoveBet()
    {
        Name = this.getClass().getSimpleName();
    }

    public String getCommandName()
    {
        return Name;
    }

    public Object getReceivedObject()
    {
        return null;
    }

    public void setObjectToSend(Object object) {
        bet = (int) object;
    }

    public void executeOnClient()
    {

    }

    public void sendToServer()
    {
        var message = new JsonConverter().toJson(this);
        ClientContainer.getClient().sendMessage(message);
    }
}
