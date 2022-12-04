package Client.Commands;

import Client.ClientContainer;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;
import Client.Poker.Models.PlayerModel;
import Client.Poker.PokerContainer;
import Client.Poker.Window.WaitingWindowContainer;
import Json.JsonConverter;

public final class RegisterPokerPlayer extends SimpleCommandModel implements ICommand
{
    protected PlayerModel Player = new PlayerModel();

    public RegisterPokerPlayer() { Name = this.getClass().getSimpleName(); }

    public String getCommandName() { return Name; }

    public void setObjectToSend(Object object) { Player = (PlayerModel) object; }

    public Object getReceivedObject() { return null; }

    public void executeOnClient()
    {
        var poker = PokerContainer.getPoker();
        poker.setCurrentPlayer(Player);
        WaitingWindowContainer.getWaitingWindow();
    }

    public void sendToServer()
    {
        var poker = PokerContainer.getPoker();
        var currentPlayer = poker.getCurrentPlayer();
        Player = currentPlayer;
        var message = new JsonConverter().toJson(this);
        ClientContainer.getClient().sendMessage(message);
    }
}
