package Client.Poker.Interfaces;

import Client.Poker.Models.PlayerModel;
import Client.Poker.Models.TableModel;

public interface IPoker
{
    PlayerModel getCurrentPlayer();

    PlayerModel getPlayer(String nickName);

    void setCurrentPlayer(PlayerModel player);

    void setTable(TableModel table);

    TableModel getTable();
}
