package Client.Poker.Window;


public class AuthorizeWindowContainer
{
    private static AuthorizeWindow authorizeWindow;

    public static AuthorizeWindow getAuthorizeWindow()
    {
        if(authorizeWindow == null)
            authorizeWindow = new AuthorizeWindow();

        return authorizeWindow;
    }
}
