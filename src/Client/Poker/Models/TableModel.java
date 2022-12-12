package Client.Poker.Models;

import Client.Poker.Cards.Models.CardModel;
import Client.Poker.Enums.GameState;
import Client.Poker.Enums.LobbyState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class TableModel
{
    public int Pot = 0;
    public int Bet = 0;
    public int PlayerIndexTurn = 0;
    public int TimerStartTime = 0;
    public int PlayersInQueue = 0;
    public PlayerModel Winner = null;
    public ArrayList<PlayerModel> Winners = null;
    public ArrayList<CardModel> WinnerCombination = null;
    public GameState GameState = Client.Poker.Enums.GameState.Preflop;
    public LobbyState LobbyState = Client.Poker.Enums.LobbyState.Waiting;
    public ArrayList<CardModel> CardsOnTable = new ArrayList();
    public Map<Integer, PlayerModel> PlacePlayerMap = new HashMap(5);
}
