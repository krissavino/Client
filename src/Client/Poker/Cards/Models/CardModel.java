package Client.Poker.Cards.Models;

import Client.Poker.Cards.Enums.CardColor;
import Client.Poker.Cards.Enums.CardName;

public class CardModel
{
    public CardColor Color = CardColor.values()[0];
    public CardName Name = CardName.values()[0];

    public boolean Opened = false;
    public CardModel(CardColor color, CardName name)
    {
        this.Color = color;
        this.Name = name;
    }
    private CardName GetName() {
        return Name;
    }

    private CardColor GetColor() {
        return Color;
    }

    private void setOpened(boolean opened) {
        Opened = opened;
    }

    private boolean isOpened() {
        return Opened;
    }
    @Override
    public boolean equals(Object obj) {
        super.equals(obj);
        if(this.Color == ((CardModel)obj).Color && this.Name == ((CardModel)obj).Name)
            return true;
        else
            return false;
    }
}
