package Client.Poker.Models;

import Client.Poker.Cards.Models.CardModel;
import Client.Poker.Enums.MoveType;
import Client.Poker.Enums.Role;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel
{
    public String NickName;
    public MoveType LastMove = MoveType.Check;
    public Role Role = Client.Poker.Enums.Role.Player;
    public int Chips;
    public int Bet = -1;
    public int Place = 0;
    public int Score;
    public boolean IsFold = false;
    public boolean IsMoved = false;
    public List<CardModel> Cards = new ArrayList<>();
}
