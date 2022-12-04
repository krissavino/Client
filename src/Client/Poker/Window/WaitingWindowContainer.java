package Client.Poker.Window;


public class WaitingWindowContainer
{
    private static WaitingWindow waitingWindow;

    public static WaitingWindow getWaitingWindow()
    {
        if(waitingWindow == null)
            waitingWindow = new WaitingWindow();

        return waitingWindow;
    }

    public static void closeWindow()
    {
        if(waitingWindow == null)
            return;

        waitingWindow.closeWindow();
    }
}
