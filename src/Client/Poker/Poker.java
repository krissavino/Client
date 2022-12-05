package Client.Poker;

import Client.ClientContainer;
import Client.Poker.Models.PlayerModel;
import Client.Poker.Models.PokerModel;
import Client.Poker.Interfaces.IPoker;
import Client.Poker.Models.TableModel;

import java.util.Timer;

public class Poker implements IPoker
{
    private PokerModel Poker = new PokerModel();
    private Timer GameTimer = new Timer();

    public PlayerModel getCurrentPlayer()
    {
        return Poker.CurrentPlayer;
    }

    public void setCurrentPlayer(PlayerModel player)
    {
        Poker.CurrentPlayer = player;
    }

    public PlayerModel getPlayer(String nickName)
    {
        var playersFromPlacePlayerMap = Poker.Table.PlacePlayerMap.values();

        for (var player : playersFromPlacePlayerMap)
            if(player.NickName.equals(nickName))
                return player;

        return null;
    }

    public void setTable(TableModel table)
    {
        Poker.Table = table;
    }
    public TableModel getTable() { return Poker.Table; }

}
