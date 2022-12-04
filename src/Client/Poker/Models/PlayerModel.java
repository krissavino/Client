package Client.Poker.Models;

import Client.Client;
import Client.Poker.Cards.Models.CardModel;
import Client.Poker.Enums.MoveType;
import Client.Poker.Enums.Role;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel
{
    public String NickName = "Unknown";
    public MoveType LastMove = MoveType.None;
    public Role Role = Client.Poker.Enums.Role.Player;
    public int Chips = 0;
    public int Place = 0;
    public int Bet = -1;
    public int Score = 0;
    public boolean Disconnected = false;
    public boolean InQueue = true;
    public List<CardModel> Cards = new ArrayList<>();
}
