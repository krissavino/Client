package Client.Poker.Window;

import Client.ClientContainer;
import Client.Commands.Interfaces.ICommand;
import Client.Commands.RegisterPokerPlayer;
import Client.Main;
import Client.Poker.Models.PlayerModel;
import Client.Poker.PokerContainer;
import Net.LocalNetManager;
import Net.ServerInformation;

import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.net.InetAddress;

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
    private JComboBox addressesList;

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
        registerCommand.sendToServer();
        AuthorizeWindowContainer.getAuthorizeWindow().connectionSuccess();
        return true;
    }

    public void connectionSuccess() {
        this.setVisible(false);
    }

    public void connectionFailed()
    {
        spam = false;
        label1.setText("Соединение прервалось, повторите попытку");
    }

    boolean spam = false;
    public void enterAction()
    {
        if(spam == true) return;

        spam = true;
        loadingLabel.setVisible(true);
        InetAddress inetAdress = null;

        try
        {
            var string = (String) addressesList.getSelectedItem();
            inetAdress = InetAddress.getByName(string);
        }
        catch (UnknownHostException e)
        {
            throw new RuntimeException(e);
        }

        ServerInformation.ServerInetAddress = inetAdress;

        var connectThread = new Thread(()->fastConnect(nickNameTextField.getText()));

        connectThread.start();
    }
    void resizeComponents()
    {
        double sw = mainPanel.getWidth()/100;
        double sh = mainPanel.getHeight()/100;
        double res = (sw > sh ? sh : sw);

        loadingLabel.setVisible(true);
        loadingLabel.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("Pictures/Interface/loading.gif")).getImage().getScaledInstance((int)(res*15),(int)(res*15),1)));
    }
    public AuthorizeWindow()
    {
        /*if(true) {
            loadingLabel.setVisible(true);
            var connectThread = new Thread(() -> fastConnect(String.valueOf(Calendar.getInstance().getTimeInMillis())));
            connectThread.start();
            return;
        }*/

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
        nickNameTextField.requestFocus();
        var localAddresses = LocalNetManager.GetLocalInetAddresses();
        for(var address : localAddresses)
            addressesList.addItem(address.getHostAddress());

        enterNickNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterAction();
                resizeComponents();
            }
        });
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
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
        nickNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterAction();
            }
        });
    }

    public void closeWindow()
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
