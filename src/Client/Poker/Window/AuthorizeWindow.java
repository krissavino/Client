package Client.Poker.Window;

import Client.ClientContainer;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.RegisterPokerPlayer;
import Client.Main;
import Client.Poker.Models.PlayerModel;
import Client.Poker.PokerContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Calendar;

public class AuthorizeWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField nickNameTextField;
    private JButton enterNickNameButton;
    private JPanel interfacePanel;
    private JPanel enterPanel;
    private JPanel infoPanel;
    private JPanel titlePanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel loadingLabel;

    public static void main(String[] args) {
        //new AuthorizeWindow();

    }

    private static PlayerModel CreatePokerPlayer(String nickName)
    {
        var poker = PokerContainer.getPoker();
        var currentPlayer = poker.getCurrentPlayer();

        currentPlayer = new PlayerModel();
        currentPlayer.NickName = nickName;

        return currentPlayer;
    }

    public static boolean fastConnect(String nickName)
    {
        var client = ClientContainer.getClient();
        boolean isConnected = Main.tryConnectClient(client, 2121);

        if(isConnected == false)
        {
            System.out.println("Клиенту не удалось подключиться к серверу");
            AuthorizeWindowContainer.getAuthorizeWindow().connectionFailed();
            return false;
        }

        PlayerModel player = CreatePokerPlayer(nickName);
        PokerContainer.getPoker().setCurrentPlayer(player);
        ICommand registerCommand = new RegisterPokerPlayer();
        registerCommand.setObjectToSend(player);
        registerCommand.send();
        AuthorizeWindowContainer.getAuthorizeWindow().connectionSuccess();
        return true;
    }

    public void connectionSuccess() {
        this.setVisible(false);
    }

    public void connectionFailed() {
        label1.setText("Соединение прервалось, повторите попытку");
    }

    public AuthorizeWindow() {
        /*
        if(true) {
            loadingLabel.setVisible(true);
            var connectThread = new Thread(() -> fastConnect(String.valueOf(Calendar.getInstance().getTimeInMillis())));
            connectThread.start();
            return;
        }*/

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);

        loadingLabel.setVisible(false);

        enterNickNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadingLabel.setVisible(true);
                var connectThread = new Thread(()->fastConnect(nickNameTextField.getText()));
                connectThread.start();
            }
        });
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double sw = mainPanel.getWidth()/screenSize.getWidth();
                double sh = mainPanel.getHeight()/screenSize.getHeight();
                double res = (sw > sh ? sh : sw);
                loadingLabel.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("Pictures/Interface/loading.gif")).getImage().getScaledInstance((int)(res*150),(int)(res*150),1)));

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }
}
