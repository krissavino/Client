package Client.Commands;

import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;

public class PlaceOnTable extends SimpleCommandModel implements ICommand
{
    public PlaceOnTable()
    {
        Name = this.getClass().getSimpleName();
    }

    public String getName() {
        return Name;
    }

    public void setObjectToSend(Object object) {

    }

    public Object getReceivedObject() {
        return null;
    }

    public void execute() {

    }

    public void send() {

    }
}
