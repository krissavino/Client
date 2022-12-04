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

        PokerContainer.getPoker().setTable(Table);

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
}
