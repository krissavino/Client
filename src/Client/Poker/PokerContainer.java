package Client.Poker;

import Client.Poker.Interfaces.IPoker;

public class PokerContainer
{
    private static IPoker Poker;

    public static IPoker getPoker()
    {
        if(Poker == null)
            Poker = new Poker();

        return Poker;
    }
}
