package Client.Poker.Window;

import Client.Poker.Enums.GameState;
import Client.Poker.Enums.MoveType;
import Client.Poker.Models.PlayerModel;
import Client.Poker.Models.TableModel;
import Client.Poker.Poker;
import Client.Poker.PokerContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends JFrame {
    private JPanel tablePanel;
    private JPanel tableCardsPanel;
    private JPanel tablePotPanel;
    private JPanel playerInterfacePanel;
    private JPanel otherPlayersPanel;
    private JPanel mainPanel;
    private JLabel tableCard1;
    private JLabel tableCard2;
    private JLabel tableCard3;
    private JLabel tableCard4;
    private JLabel tableCard5;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JPanel player3Panel;
    private JPanel player4Panel;
    private JLabel player4Avatar;
    private JLabel player1Avatar;
    private JLabel player2Avatar;
    private JLabel player3Avatar;
    private JProgressBar player1ProgressBar;
    private JProgressBar player2ProgressBar;
    private JProgressBar player3ProgressBar;
    private JProgressBar player4ProgressBar;
    private JLabel player1Nickname;
    private JLabel player2Nickname;
    private JLabel player3Nickname;
    private JLabel player4Nickname;
    private JLabel player1Card1;
    private JLabel player1Card2;
    private JPanel player1CardsPanel;
    private JPanel player2CardsPanel;
    private JLabel player2Card1;
    private JLabel player2Card2;
    private JLabel player3Card1;
    private JLabel player3Card2;
    private JLabel player4Card1;
    private JLabel player4Card2;
    private JPanel myCardsPanel;
    private JLabel myCard1;
    private JLabel myCard2;
    private JPanel myInfoPanel;
    private JLabel myNicknameLabel;
    private JLabel myBetLabel;
    private JLabel myChipsLabel;
    private JProgressBar myProgressBar;
    private JButton checkButton;
    private JButton callButton;
    private JButton betButton;
    private JPanel buttonsPanel;
    private JButton raiseBtn;
    private JButton foldButton;

    private JPanel[] playersPanels;
    private JLabel[] playersAvatarLabels;
    private JLabel[] playersNicknameLabels;
    private JProgressBar[] playersProgressBars;
    private JLabel[][] playersCardsLabels;

    private JLabel[] tableCardsLabels;

    private Timer moveTimer;
    public void createElementsArrays() {
        playersPanels = new JPanel[4];
        playersPanels[0] = player1Panel;
        playersPanels[1] = player2Panel;
        playersPanels[2] = player3Panel;
        playersPanels[3] = player4Panel;
        playersPanels[3] = player4Panel;
        playersAvatarLabels = new JLabel[4];
        playersAvatarLabels[0] = player1Avatar;
        playersAvatarLabels[1] = player2Avatar;
        playersAvatarLabels[2] = player3Avatar;
        playersAvatarLabels[3] = player4Avatar;
        playersProgressBars = new JProgressBar[4];
        playersProgressBars[0] = player1ProgressBar;
        playersProgressBars[1] = player2ProgressBar;
        playersProgressBars[2] = player3ProgressBar;
        playersProgressBars[3] = player4ProgressBar;
        playersNicknameLabels = new JLabel[4];
        playersNicknameLabels[0] = player1Nickname;
        playersNicknameLabels[1] = player2Nickname;
        playersNicknameLabels[2] = player3Nickname;
        playersNicknameLabels[3] = player4Nickname;
        playersCardsLabels = new JLabel[4][2];
        playersCardsLabels[0][0] = player1Card1;
        playersCardsLabels[0][1] = player1Card2;
        playersCardsLabels[1][0] = player2Card1;
        playersCardsLabels[1][1] = player2Card2;
        playersCardsLabels[2][0] = player3Card1;
        playersCardsLabels[2][1] = player3Card2;
        playersCardsLabels[3][0] = player4Card1;
        playersCardsLabels[3][1] = player4Card2;
        tableCardsLabels = new JLabel[5];
        tableCardsLabels[0] = tableCard1;
        tableCardsLabels[1] = tableCard2;
        tableCardsLabels[2] = tableCard3;
        tableCardsLabels[3] = tableCard4;
        tableCardsLabels[4] = tableCard5;

    }
    public void updateInfo() {
        setVisible(true);
        setPlayersInfo();
        checkMoveTimer();
    }
    public void checkMoveTimer() {
        if(moveTimer != null)
            moveTimer.cancel();
        moveTimer = new Timer();
        myProgressBar.setValue(0);
        for(int i = 0; i < 4; i++)
            playersProgressBars[i].setValue(0);
        repaint();
        if(!(PokerContainer.getPoker().getTable().State == GameState.Started)) return;
        moveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Poker poker = (Poker)PokerContainer.getPoker();
                TableModel table = PokerContainer.getPoker().getTable();
                if(table.State == GameState.Started) {
                    if(table.PlayerIndexTurn == poker.getCurrentPlayer().Place)
                    {
                        myProgressBar.setValue(myProgressBar.getValue()+5);
                        if(myProgressBar.getValue() >= 100) {
                            myProgressBar.setValue(0);
                            repaint();
                            moveTimer.cancel();
                        }
                    } else {
                        int windowIndexTurn = table.PlayerIndexTurn;
                        playersProgressBars[windowIndexTurn].setValue(playersProgressBars[windowIndexTurn].getValue()+5);
                        if(playersProgressBars[windowIndexTurn].getValue() >= 100) {
                            playersProgressBars[windowIndexTurn].setValue(0);
                            repaint();
                            moveTimer.cancel();
                        }
                    }
                }
            }
        }, 0, 1000);
    }
    public void setPlayersInfo() {
        Poker poker = (Poker)PokerContainer.getPoker();
        TableModel table = PokerContainer.getPoker().getTable();
        PlayerModel me = poker.getCurrentPlayer();
        if(me == null) return;

        resizeComponents();

        int myBet = (me.Bet < 0 ? 0 : me.Bet);
        myBetLabel.setText("Моя ставка: " + myBet);
        myNicknameLabel.setText("Никнейм: " + me.NickName);
        myChipsLabel.setText("Мои фишки: " + me.Chips);
    }
    void resizeComponents() {
        Poker poker = (Poker)PokerContainer.getPoker();
        TableModel table = PokerContainer.getPoker().getTable();
        for(var p : table.Players.values()) {
            if(p.NickName.equals(poker.getCurrentPlayer().NickName))
                poker.setCurrentPlayer(p);
        }
        PlayerModel me = poker.getCurrentPlayer();
        if(me == null) return;
        double sw = mainPanel.getWidth()/100;
        double sh = mainPanel.getHeight()/100;
        double res;
        res = (sw > sh ? sh : sw);

        for(int i = 0; i < playersPanels.length; i++) {
            playersPanels[i].setVisible(false);
        }

        for(int i = 0; i < table.Players.size(); i++) {
            int windowPlayerPlace = i;
            if(windowPlayerPlace == me.Place) {
                continue;
            }
            PlayerModel player = table.Players.get(windowPlayerPlace);
            playersNicknameLabels[i].setText(player.NickName);
            if(player.Place != me.Place)
                playersPanels[i].setVisible(true);
            if(playersPanels[i].isVisible()) {

                for(int j = 0; j < 2; j++) {
                    if(player.Cards.get(j).IsOpened == true)
                        playersCardsLabels[i][j].setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Cards/" + player.Cards.get(j).Color + "/" + player.Cards.get(j).Name + ".jpg"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
                    else
                        playersCardsLabels[i][j].setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Cards/shirt.png"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 1)));
                }

                if(table.Winner != null) {
                    if(table.Winner.NickName.equals(table.Players.get(i).NickName))
                        playersAvatarLabels[i].setIcon(
                                new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/Winner.png")))
                                        .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                    continue;
                }
                if(player.LastMove == MoveType.Fold) {
                    playersAvatarLabels[i].setIcon(
                            new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/Fold.png")))
                                    .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                } else {
                    playersAvatarLabels[i].setIcon(
                            new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/" + table.Players.get(windowPlayerPlace).Role + ".png")))
                                    .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                }

            }
        }

        if(table.CardsOnTable == null) return;
        if(table.CardsOnTable.size() == 0) return;
        for(int i = 0; i < 5; i++) {
            if(table.CardsOnTable.get(i).IsOpened == true)
                tableCardsLabels[i].setIcon(new ImageIcon((
                        new ImageIcon(this.getClass().getResource("Pictures/Cards/" + table.CardsOnTable.get(i).Color + "/" + table.CardsOnTable.get(i).Name + ".jpg"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
            else
                tableCardsLabels[i].setIcon(new ImageIcon((
                        new ImageIcon(this.getClass().getResource("Pictures/Cards/shirt.png"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
        }

        if(me.Cards == null) return;
        if(me.Cards.get(0) == null) return;
        if(me.Cards.get(1) == null) return;
        for(int i = 0; i < 2; i++) {
            myCard1.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Cards/" + me.Cards.get(0).Color + "/" + me.Cards.get(0).Name + ".jpg"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
            myCard2.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Cards/" + me.Cards.get(1).Color + "/" + me.Cards.get(1).Name + ".jpg"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
        }
    }
    public GameWindow() {
        createElementsArrays();
        setContentPane(mainPanel);
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        callButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        raiseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
