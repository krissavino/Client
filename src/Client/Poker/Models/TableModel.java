package Client.Poker.Models;

import Client.Poker.Cards.Models.CardModel;
import Client.Poker.Enums.GameStage;
import Client.Poker.Enums.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class TableModel
{
    public int Pot = 0;
    public int Bet = 0;
    public int PlayerIndexTurn = 0;
    public int TimerStartTime = 0;
    public GameStage Stage = GameStage.Preflop;
    public GameState State = GameState.Waiting;
    public PlayerModel Winner;
    public ArrayList<CardModel> CardsOnTable = new ArrayList();
    public Map<Integer,PlayerModel> PlacePlayerMap = new HashMap();
}
