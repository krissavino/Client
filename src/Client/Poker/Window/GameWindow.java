package Client.Poker.Window;

import Client.Commands.*;
import Client.Poker.Enums.LobbyState;
import Client.Poker.Enums.MoveType;
import Client.Poker.Models.PlayerModel;
import Client.Poker.Models.TableModel;
import Client.Poker.Poker;
import Client.Poker.PokerContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private JButton raiseButton;
    private JButton foldButton;
    private JComboBox betComboBox;
    private JLabel tablePotLabel;
    private JLabel player1BetLabel;
    private JLabel player2BetLabel;
    private JLabel player3BetLabel;
    private JLabel player4BetLabel;
    private JLabel label1;
    private JLabel myAvatarLabel;
    private JPanel player5Panel;
    private JLabel player5Avatar;
    private JProgressBar player5ProgressBar;
    private JLabel player5Nickname;
    private JLabel player5Card1;
    private JLabel player5Card2;
    private JLabel player5BetLabel;
    private JLabel[] playersBetLabels;

    private JPanel[] playersPanels;
    private JLabel[] playersAvatarLabels;
    private JLabel[] playersNicknameLabels;
    private JProgressBar[] playersProgressBars;
    private JLabel[][] playersCardsLabels;

    private JLabel[] tableCardsLabels;

    private Timer moveTimer;
    public void createElementsArrays() {
        playersPanels = new JPanel[5];
        playersPanels[0] = player1Panel;
        playersPanels[1] = player2Panel;
        playersPanels[2] = player3Panel;
        playersPanels[3] = player4Panel;
        playersPanels[4] = player5Panel;
        playersAvatarLabels = new JLabel[5];
        playersAvatarLabels[0] = player1Avatar;
        playersAvatarLabels[1] = player2Avatar;
        playersAvatarLabels[2] = player3Avatar;
        playersAvatarLabels[3] = player4Avatar;
        playersAvatarLabels[4] = player5Avatar;
        playersProgressBars = new JProgressBar[5];
        playersProgressBars[0] = player1ProgressBar;
        playersProgressBars[1] = player2ProgressBar;
        playersProgressBars[2] = player3ProgressBar;
        playersProgressBars[3] = player4ProgressBar;
        playersProgressBars[4] = player5ProgressBar;
        playersNicknameLabels = new JLabel[5];
        playersNicknameLabels[0] = player1Nickname;
        playersNicknameLabels[1] = player2Nickname;
        playersNicknameLabels[2] = player3Nickname;
        playersNicknameLabels[3] = player4Nickname;
        playersNicknameLabels[4] = player5Nickname;
        playersCardsLabels = new JLabel[5][2];
        playersCardsLabels[0][0] = player1Card1;
        playersCardsLabels[0][1] = player1Card2;
        playersCardsLabels[1][0] = player2Card1;
        playersCardsLabels[1][1] = player2Card2;
        playersCardsLabels[2][0] = player3Card1;
        playersCardsLabels[2][1] = player3Card2;
        playersCardsLabels[3][0] = player4Card1;
        playersCardsLabels[3][1] = player4Card2;
        playersCardsLabels[4][0] = player5Card1;
        playersCardsLabels[4][1] = player5Card2;
        tableCardsLabels = new JLabel[5];
        tableCardsLabels[0] = tableCard1;
        tableCardsLabels[1] = tableCard2;
        tableCardsLabels[2] = tableCard3;
        tableCardsLabels[3] = tableCard4;
        tableCardsLabels[4] = tableCard5;
        playersBetLabels = new JLabel[5];
        playersBetLabels[0] = player1BetLabel;
        playersBetLabels[1] = player2BetLabel;
        playersBetLabels[2] = player3BetLabel;
        playersBetLabels[3] = player4BetLabel;
        playersBetLabels[4] = player5BetLabel;


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double sw = mainPanel.getWidth()/screenSize.getWidth();
        double sh = mainPanel.getHeight()/screenSize.getHeight();
        double res;

        res = (sw > sh ? sh : sw);

        if(res < 1) res = 1;

        for(int i = 0; i < playersAvatarLabels.length; i++) {
            playersAvatarLabels[i].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("Pictures/Interface/Player.png")).getImage().getScaledInstance((int)(res*150),(int)(res*150),1)));
            tableCardsLabels[i].setVisible(false);
        }


        betComboBox.addItem("10");
        betComboBox.addItem("25");
        betComboBox.addItem("50");
        betComboBox.addItem("100");

        tablePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        myCardsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        myInfoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.setBackground(new Color(0,0,0,0));
        for(var playerPanel : playersPanels)
            playerPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        for(int i = 0; i < 5; i++)
            tableCardsLabels[i].setLayout(new FlowLayout(FlowLayout.CENTER, FlowLayout.CENTER, FlowLayout.CENTER));
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 2; j++)
                playersCardsLabels[i][j].setLayout(new FlowLayout(FlowLayout.CENTER, FlowLayout.CENTER, FlowLayout.CENTER));
        myCard1.setLayout(new FlowLayout(FlowLayout.CENTER, FlowLayout.CENTER, FlowLayout.CENTER));
        myCard2.setLayout(new FlowLayout(FlowLayout.CENTER, FlowLayout.CENTER, FlowLayout.CENTER));

    }
    public void updateInfo()
    {
        setVisible(true);
        setPlayersInfo();
        checkMoveTimer();
    }

    public void checkMoveTimer()
    {

        if(moveTimer != null)
            moveTimer.cancel();

        moveTimer = new Timer();
        myProgressBar.setValue(0);

        for(int i = 0; i < 4; i++)
            playersProgressBars[i].setValue(0);

        //repaint();

        if(!(PokerContainer.getPoker().getTable().LobbyState == LobbyState.Started)) return;

        moveTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                var poker = (Poker)PokerContainer.getPoker();
                var table = PokerContainer.getPoker().getTable();

                if(table.LobbyState != LobbyState.Started)
                    return;

                if(table.PlayerIndexTurn == poker.getCurrentPlayer().Place && !poker.getCurrentPlayer().InQueue)
                    UpdatePlayerProgressBar(myProgressBar);
                else
                {
                    var playerProgressBar = playersProgressBars[table.PlayerIndexTurn];
                    UpdatePlayerProgressBar(playerProgressBar);
                }
            }
        }, 0, 1000);
    }

    private void UpdatePlayerProgressBar(JProgressBar progressBar)
    {
        var serverTimerSeconds = PokerContainer.getPoker().getTable().TimerStartTime/1000;
        var serverTimerTicks = 100/serverTimerSeconds;
        var currentProgressBarValue = progressBar.getValue();
        var nextProgressBarValue = currentProgressBarValue + serverTimerTicks;

        progressBar.setValue(nextProgressBarValue);

        if(progressBar.getValue() >= 100)
        {
            progressBar.setValue(0);
            //repaint();
            moveTimer.cancel();
        }
    }

    public void setPlayersInfo()
    {
        resizeComponents();
    }

    boolean isFirstWinner = true;
    void resizeComponents()
    {
        Poker poker = (Poker)PokerContainer.getPoker();
        TableModel table = PokerContainer.getPoker().getTable();
        var winnerCombination = table.WinnerCombination;
        Component[] components = tableCardsPanel.getComponents();
        for(var comp : components) {
            ((JLabel)comp).removeAll();
        }

        PlayerModel me = poker.getCurrentPlayer();

        double sw = mainPanel.getWidth()/100;
        double sh = mainPanel.getHeight()/100;
        double res;
        res = (sw > sh ? sh : sw);

        JLabel test = new JLabel();
        test.setIcon(
                new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/cardLight.png")))
                        .getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 1)));

        if(me != null) {
            int myBet = (me.Bet < 0 ? 0 : me.Bet);
            myBetLabel.setText("Моя ставка: " + myBet);
            myChipsLabel.setText("Мои фишки: " + me.Chips);
            myNicknameLabel.setText(me.NickName);
            if(me.LastMove == MoveType.Fold) {
                myAvatarLabel.setIcon(
                        new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/Fold.png")))
                                .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
            } else {
                if(table.Winner != null) {
                    if(table.Winner.NickName.equals(me.NickName)) {
                        myAvatarLabel.setIcon(
                                new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/Winner.png")))
                                        .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                    }
                    if(table.Winners != null) {
                        for(var winner : table.Winners) {
                            if(winner.NickName.equals(me.NickName)) {
                                myAvatarLabel.setIcon(
                                        new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/Winner.png")))
                                                .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                            }
                        }
                    }
                } else {
                    if(me.LastMove == MoveType.Fold || me.LastMove == MoveType.AllIn) {
                        myAvatarLabel.setIcon(
                                new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/" + me.LastMove.name() + ".png")))
                                        .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                    } else {
                        myAvatarLabel.setIcon(
                                new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/" + me.Role + ".png")))
                                        .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                    }
                }
            }

        }

        for(int i = 0; i < playersPanels.length; i++)
            playersPanels[i].setVisible(false);

        for(int i = 0; i < table.PlacePlayerMap.size(); i++) {
            PlayerModel player = table.PlacePlayerMap.get(i);
            if(player == null)
                return;
            playersPanels[i].setVisible(true);
            if (player.NickName.equals(poker.getCurrentPlayer().NickName))
                playersPanels[i].setVisible(false);


            playersNicknameLabels[i].setText(player.NickName);
            tablePotLabel.setText(""+table.Pot);
            tablePotLabel.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/chips.png")))
                    .getImage().getScaledInstance((int) (res * 5), (int) (res * 5), 1)));

            if(playersPanels[i].isVisible())
            {
                for(int j = 0; j < 2; j++)
                {
                    if(player.Cards.get(j).Opened == true)
                        playersCardsLabels[i][j].setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Cards/" + player.Cards.get(j).Color + "/" + player.Cards.get(j).Name + ".jpg")))
                                .getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
                    else
                        playersCardsLabels[i][j].setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Cards/shirt.png")))
                                .getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 1)));

                    int finalI = i;
                    int finalJ = j;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            playersCardsLabels[finalI][finalJ].removeAll();
                            playersCardsLabels[finalI][finalJ].validate();
                            playersCardsLabels[finalI][finalJ].repaint();
                            if(winnerCombination != null) {
                                for(var card : winnerCombination) {
                                    if(card.equals(player.Cards.get(finalJ))) {
                                        playersCardsLabels[finalI][finalJ].add(test);
                                        playersCardsLabels[finalI][finalJ].validate();
                                        playersCardsLabels[finalI][finalJ].repaint();
                                    }
                                }
                            }
                        }
                    }, i*100);

                }

                if(player.Bet > 0) {
                    playersBetLabels[i].setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/chips.png")))
                            .getImage().getScaledInstance((int) (res * 3), (int) (res * 3), 1)));
                    playersBetLabels[i].setText(""+player.Bet);
                    playersBetLabels[i].setVisible(true);
                } else {
                    playersBetLabels[i].setVisible(false);
                    playersBetLabels[i].setText("");
                }

                if(table.Winner != null)
                {
                    if(table.Winner.NickName.equals(table.PlacePlayerMap.get(i).NickName))
                        playersAvatarLabels[i].setIcon(
                                new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/Winner.png")))
                                        .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                    if(table.Winners != null) {
                        for(var winner : table.Winners) {
                            if(winner.NickName.equals(table.PlacePlayerMap.get(i).NickName)) {
                                playersAvatarLabels[i].setIcon(
                                        new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/Winner.png")))
                                                .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                            }
                        }
                    }
                    continue;
                }
                if(player.LastMove == MoveType.Fold || player.LastMove == MoveType.AllIn) {
                    playersAvatarLabels[i].setIcon(
                            new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/" + player.LastMove.name() + ".png")))
                                    .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                } else {
                    playersAvatarLabels[i].setIcon(
                            new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Interface/" + table.PlacePlayerMap.get(i).Role + ".png")))
                                    .getImage().getScaledInstance((int) (res * 15), (int) (res * 15), 1)));
                }

            }
        }

        if(table.CardsOnTable == null) return;
        if(table.CardsOnTable.size() == 0) return;
        for(int i = 0; i < 5; i++) {
            tableCardsLabels[i].setVisible(true);
            if(table.CardsOnTable.get(i).Opened == true)
                tableCardsLabels[i].setIcon(new ImageIcon((
                        new ImageIcon(this.getClass().getResource("Pictures/Cards/" + table.CardsOnTable.get(i).Color + "/" + table.CardsOnTable.get(i).Name + ".jpg"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
            else
                tableCardsLabels[i].setIcon(new ImageIcon((
                        new ImageIcon(this.getClass().getResource("Pictures/Cards/shirt.png"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));

            int finalI = i;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if(winnerCombination != null) {
                        for(var card : winnerCombination) {
                            if(card.equals(table.CardsOnTable.get(finalI))) {
                                tableCardsLabels[finalI].add(test);
                                tableCardsLabels[finalI].validate();
                                tableCardsLabels[finalI].repaint();
                            }
                        }
                    }
                }
            }, 100*i);
        }

        if(me.InQueue) {
            playerInterfacePanel.setVisible(false);
        } else {
            playerInterfacePanel.setVisible(true);
        }

        if(me == null) return;
        if(me.Cards.size() == 0) return;

        myCard1.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Cards/" + me.Cards.get(0).Color + "/" + me.Cards.get(0).Name + ".jpg"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
        myCard2.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("Pictures/Cards/" + me.Cards.get(1).Color + "/" + me.Cards.get(1).Name + ".jpg"))).getImage().getScaledInstance((int) (res * 13), (int) (res * 22), 5)));
        myCard1.removeAll();
        myCard1.validate();
        myCard1.repaint();
        myCard2.removeAll();
        myCard2.validate();
        myCard2.repaint();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(winnerCombination != null) {
                    for(var card : winnerCombination) {
                        if(card.equals(me.Cards.get(0))) {
                            myCard1.add(test);
                            myCard1.validate();
                            myCard1.repaint();
                        }
                    }
                }
            }
        }, 100);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(winnerCombination != null) {
                    for(var card : winnerCombination) {
                        if(card.equals(me.Cards.get(1))) {
                            myCard2.add(test);
                            myCard2.validate();
                            myCard2.repaint();
                        }
                    }
                }
            }
        }, 200);

        checkButton.setVisible(table.Bet == me.Bet);
        callButton.setVisible(table.Bet > me.Bet);
        raiseButton.setVisible(table.Bet > me.Bet);
        betButton.setVisible(table.Bet == me.Bet);
    }

    public GameWindow() {
        createElementsArrays();
        setContentPane(mainPanel);

        setSize(500,600);
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
                MoveCheck move = new MoveCheck();
                move.setObjectToSend(0);
                move.sendToServer();
            }
        });
        callButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoveCall move = new MoveCall();
                move.setObjectToSend(0);
                move.sendToServer();
            }
        });
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoveBet move = new MoveBet();
                int bet = Integer.parseInt(betComboBox.getSelectedItem().toString());
                move.setObjectToSend(bet);
                move.sendToServer();
            }
        });
        raiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoveRaise move = new MoveRaise();
                int bet = Integer.parseInt(betComboBox.getSelectedItem().toString());
                move.setObjectToSend(bet);
                move.sendToServer();
            }
        });
        foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoveFold move = new MoveFold();
                move.setObjectToSend(0);
                move.sendToServer();

            }
        });
    }

    public void closeWindow()
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
