package Client.Commands;

import Client.ClientContainer;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;
import Client.Poker.Models.TableModel;
import Client.Poker.PokerContainer;
import Json.JsonConverter;

public class PlayerMove extends SimpleCommandModel implements ICommand
{
    protected int bet = -1;
    public PlayerMove()
    {
        Name = this.getClass().getSimpleName();
    }

    public String getName()
    {
        return Name;
    }

    public Object getReceivedObject()
    {
        return bet;
    }

    public void setObjectToSend(Object object) {
    }

    public void execute()
    {

    }

    public void send() {
        var message = new JsonConverter().toJson(this);
        ClientContainer.getClient().sendMessage(message);
    }
}
