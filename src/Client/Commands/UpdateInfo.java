package Client.Commands;

import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;
import Client.Poker.Enums.GameState;
import Client.Poker.Models.TableModel;
import Client.Poker.PokerContainer;
import Client.Poker.Window.GameWindowContainer;
import Client.Poker.Window.WaitingWindow;
import Client.Poker.Window.WaitingWindowContainer;

public class UpdateInfo extends SimpleCommandModel implements ICommand
{
    protected TableModel tableModel;
    public UpdateInfo()
    {
        Name = this.getClass().getSimpleName();
    }

    public String getName()
    {
        return Name;
    }

    public Object getReceivedObject()
    {
        return tableModel;
    }

    public void setObjectToSend(Object object) {
        tableModel = (TableModel) object;
    }

    public void execute()
    {
        PokerContainer.getPoker().setTable(tableModel);

        if(tableModel.State == GameState.Waiting) {
            GameWindowContainer.getGameWindow().setVisible(false);
            WaitingWindowContainer.getWaitingWindow().setVisible(true);
            WaitingWindowContainer.getWaitingWindow().updateInfo();
        } else {
            WaitingWindowContainer.getWaitingWindow().setVisible(false);
            GameWindowContainer.getGameWindow().updateInfo();
        }
    }

    public void send() {

    }
}
