package Client.Commands;

import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;
import Client.Poker.Enums.LobbyState;
import Client.Poker.Models.TableModel;
import Client.Poker.PokerContainer;
import Client.Poker.Window.GameWindowContainer;
import Client.Poker.Window.WaitingWindowContainer;

public class UpdateInfo extends SimpleCommandModel implements ICommand
{
    protected TableModel Table;
    public UpdateInfo() { Name = this.getClass().getSimpleName(); }

    public String getCommandName() { return Name; }

    public Object getReceivedObject() { return Table; }

    public void setObjectToSend(Object object) { Table = (TableModel) object; }

    public void executeOnClient()
    {
        if(Table == null)
            return;

        var poker = PokerContainer.getPoker();
        poker.setTable(Table);
        setNewCurrentPlayer();

        if(Table.LobbyState == LobbyState.Waiting)
        {
            GameWindowContainer.getGameWindow().setVisible(false);
            WaitingWindowContainer.getWaitingWindow().setVisible(true);
            WaitingWindowContainer.getWaitingWindow().updateInfo();
        }
        else
        {
            WaitingWindowContainer.getWaitingWindow().setVisible(false);
            GameWindowContainer.getGameWindow().updateInfo();
        }
    }

    public void sendToServer() {}

    private void setNewCurrentPlayer()
    {
        var poker = PokerContainer.getPoker();
        var currentPlayer = poker.getCurrentPlayer();
        var newCurrentPlayer = poker.getPlayer(currentPlayer.NickName);

        if(newCurrentPlayer == null)
            return;

        poker.setCurrentPlayer(newCurrentPlayer);
    }
}
