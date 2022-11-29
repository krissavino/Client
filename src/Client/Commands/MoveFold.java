package Client.Commands;

import Client.ClientContainer;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;
import Client.Poker.Models.PlayerModel;
import Client.Poker.PokerContainer;
import Json.JsonConverter;

public class MoveFold extends SimpleCommandModel implements ICommand
{
    int bet;
    public MoveFold()
    {
        Name = this.getClass().getSimpleName();
    }

    public String getName()
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

    public void execute()
    {

    }

    public void send()
    {
        var message = new JsonConverter().toJson(this);
        ClientContainer.getClient().sendMessage(message);
    }
}
