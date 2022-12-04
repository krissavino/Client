package Client.Poker.Window;


public class GameWindowContainer
{
    private static GameWindow gameWindow;

    public static GameWindow getGameWindow()
    {
        if(gameWindow == null)
            gameWindow = new GameWindow();

        return gameWindow;
    }

    public static void closeWindow()
    {
        if(gameWindow == null)
            return;

        gameWindow.closeWindow();
    }
}
