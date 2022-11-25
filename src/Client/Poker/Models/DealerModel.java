package Client.Poker.Models;

import Client.Poker.Cards.Models.CardModel;

import java.util.ArrayList;

public final class DealerModel
{
    public int IndexTurn = 0;
    public ArrayList<CardModel> Cards = new ArrayList();
}
