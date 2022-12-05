package Client.Poker.Models;

import Client.Poker.Cards.Models.CardModel;
import Client.Poker.Enums.MoveType;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel
{
    public String NickName = "Unknown";
    public MoveType LastMove = MoveType.None;
    public Client.Poker.Enums.Role Role = Client.Poker.Enums.Role.Player;
    public int Chips = 0;
    public int Place = 0;
    public int Bet = 0;
    public int Score = 0;
    public boolean Disconnected = false;
    public boolean InQueue = true;
    public List<CardModel> Cards = new ArrayList<>();
}
