package Client.Commands;

import Client.Commands.Interfaces.ICommand;
import Client.Commands.Models.SimpleCommandModel;

public class GiveAwayCards extends SimpleCommandModel implements ICommand
{
    public GiveAwayCards()
    {
        Name = GiveAwayCards.class.getSimpleName();
    }

    public String getName()
    {
        return Name;
    }

    public void setObjectToSend(Object object) {

    }

    public Object getReceivedObject() {
        return null;
    }

    public void execute(){

    }

    public void send() {

    }
}
