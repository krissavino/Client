package Client.Poker.Window;

import Client.Poker.Cards.Models.CardModel;
import Client.Poker.Enums.MoveType;
import Client.Poker.Models.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ClientWindow extends JFrame {
    public static void main(String[] args) {
        new ClientWindow();
    }
    private JPanel mainPanel;
    private JButton BETButton;
    private JPanel bottomInterface;
    private JPanel interface1;
    private JPanel interface2;
    private JPanel betsPanel;
    private JPanel tablePanel;
    private JPanel topInterface;
    private JPanel rightPlayers;
    private JPanel leftPlayers;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JPanel player3Panel;
    private JPanel player4Panel;
    private JLabel flopCard1;
    private JLabel flopCard2;
    private JLabel flopCard3;
    private JLabel turnCard;
    private JLabel riverCard;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player3Label;
    private JLabel player4Label;
    private JLabel player1Card1;
    private JLabel player1Card2;
    private JLabel player2Card1;
    private JLabel player2Card2;
    private JLabel player3Card1;
    private JLabel player3Card2;
    private JLabel player4Card2;
    private JLabel player4Card1;
    private JComboBox betComboBox;
    private JButton CHECKButton;
    private JButton FOLDButton;
    private JTextArea textArea1;
    private JTextField textField1;
    private JProgressBar player1ProgressBar;
    private JProgressBar player2ProgressBar;
    private JProgressBar player3ProgressBar;
    private JProgressBar player4ProgressBar;
    private JProgressBar myProgressBar;
    private JLabel[] myCards;
    private JLabel myCard1;
    private JLabel myCard2;
    private JLabel chipsImage;
    private int chips;
    private JLabel potLabel;
    private JLabel potTitle;
    private JButton RAISEButton;
    private JButton CALLButton;
    private JLabel player1BetLabel;
    private JLabel player2BetLabel;
    private JLabel player3BetLabel;
    private JLabel player4BetLabel;
    private JLabel chipsLabel;
    private JLabel myBetLabel;
    private JLabel[] tableCardLabels;
    private JLabel[] playerLabels;
    private JLabel[][] playersCards;
    private JLabel roleLabel;

    private JProgressBar[] playersProgressBars;
    private JLabel[] playersBetLabels;
    private boolean isSpam = false;
    private Timer spamTimer;
    private Timer progressBarTimer;
    private int playerTurnIndex = 0;
    private PlayerModel myPlayerModel;
    private int winnerIndex;
    private int pot = 0;
    private int currentBet = 0;
    private ArrayList<PlayerModel> windowPlayerModels;

    private String InterfacePictures = "Pictures/Interface";
    private String CardsPictures = "Pictures/Cards";

    public ClientWindow()
    {
        tableCardLabels = new JLabel[5];
        tableCardLabels[0] = flopCard1;
        tableCardLabels[1] = flopCard2;
        tableCardLabels[2] = flopCard3;
        tableCardLabels[3] = turnCard;
        tableCardLabels[4] = riverCard;
        playerLabels = new JLabel[4];
        playerLabels[0] = player1Label;
        playerLabels[1] = player2Label;
        playerLabels[2] = player3Label;
        playerLabels[3] = player4Label;
        for(int i = 0; i < 4; i++) {
            playersCards = new JLabel[4][2];
            playersCards[0] = new JLabel[2];
        }
        playersCards[0][0] = player1Card1;
        playersCards[0][1] = player1Card2;
        playersCards[1][0] = player2Card1;
        playersCards[1][1] = player2Card2;
        playersCards[2][0] = player3Card1;
        playersCards[2][1] = player3Card2;
        playersCards[3][0] = player4Card1;
        playersCards[3][1] = player4Card2;
        myCards = new JLabel[2];
        myCards[0] = myCard1;
        myCards[1] = myCard2;
        for(JLabel jl : tableCardLabels)
        {
            jl.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(CardsPictures+"/absense.png")).getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
        }
        for(JLabel jl : playerLabels)
        {
            jl.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(InterfacePictures+"/absense.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
        playersProgressBars = new JProgressBar[4];
        playersProgressBars[0] = player1ProgressBar;
        playersProgressBars[1] = player2ProgressBar;
        playersProgressBars[2] = player3ProgressBar;
        playersProgressBars[3] = player4ProgressBar;
        for(JProgressBar pb : playersProgressBars) {
            pb.setPreferredSize(new Dimension(100, 10));
        }
        progressBarTimer = new Timer();
        chipsImage.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource( InterfacePictures+"/chips.png")).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
        playersBetLabels = new JLabel[4];
        playersBetLabels[0] = player1BetLabel;
        playersBetLabels[1] = player2BetLabel;
        playersBetLabels[2] = player3BetLabel;
        playersBetLabels[3] = player4BetLabel;

        setMoveButtons();

        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        //CommandModel commandModel = new CommandModel();
        spamTimer = new Timer();
        //BETButton.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        if(myPlayerModel.getPlace() != playerTurnIndex) return;
        //        if(isSpam) return;
        //        if(e.getSource() == BETButton) {
        //            int bet = Integer.parseInt(betComboBox.getSelectedItem().toString());
        //            commandModel.set(CommandEnum.SendPlayerMoveToServer.toString(), myPlayerModel.getPlace(), "BET", bet);
        //            myClient.sendMessage(commandModel.getString());
        //            startSpamTimer();
        //        }
        //    }
        //});
        //CHECKButton.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        if(myPlayerModel.getPlace() != playerTurnIndex) return;
        //        if(isSpam) return;
        //        if(e.getSource() == CHECKButton) {
        //            commandModel.set(CommandEnum.SendPlayerMoveToServer.toString(), myPlayerModel.getPlace(), "CHECK", 0);
        //            myClient.sendMessage(commandModel.getString());
        //            startSpamTimer();
        //        }
        //    }
        //});
        //CALLButton.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        if(myPlayerModel.getPlace() != playerTurnIndex) return;
        //        if(isSpam) return;
        //        if(e.getSource() == CALLButton) {
        //            commandModel.set(CommandEnum.SendPlayerMoveToServer.toString(), myPlayerModel.getPlace(), "CALL", 0);
        //            myClient.sendMessage(commandModel.getString());
        //            startSpamTimer();
        //        }
        //    }
        //});
        //RAISEButton.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        if(myPlayerModel.getPlace() != playerTurnIndex) return;
        //        if(isSpam) return;
        //        if(e.getSource() == CHECKButton) {
        //            int bet = Integer.parseInt(betComboBox.getSelectedItem().toString());
        //            commandModel.set(CommandEnum.SendPlayerMoveToServer.toString(), myPlayerModel.getPlace(), "RAISE", bet);
        //            myClient.sendMessage(commandModel.getString());
        //            startSpamTimer();
        //        }
        //    }
        //});
        //FOLDButton.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        if(myPlayerModel.getPlace() != playerTurnIndex) return;
        //        if(isSpam) return;
        //        if(e.getSource() == FOLDButton) {
        //            commandModel.set(CommandEnum.SendPlayerMoveToServer.toString(), myPlayerModel.getPlace(), "FOLD", 0);
        //            myClient.sendMessage(commandModel.getString());
        //            startSpamTimer();
        //        }
        //    }
        //});
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (e.getSource() instanceof JFrame) {
                    JFrame frame = (JFrame)(e.getSource());
                    // frame.repaint();
                }
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

    public void addPlayer(PlayerModel playerModel) {
        if(playerModel == null) return;
        if(playerModel.Role.equals("Player"))
            playerLabels[playerModel.Place].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(InterfacePictures+"/user.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        if(playerModel.Role.equals("Big Blind"))
            playerLabels[playerModel.Place].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(InterfacePictures+"/big_blind_user.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        if(playerModel.Role.equals("Small Blind")) {
            playerLabels[playerModel.Place].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(InterfacePictures+"/small_blind_user.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
        if(playerModel.Role.equals("Dealer")) {
            playerLabels[playerModel.Place].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(InterfacePictures+"/dealer_user.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
        playerLabels[playerModel.Place].setText(playerModel.NickName);
        playerLabels[playerModel.Place].setHorizontalTextPosition(JLabel.CENTER);
        playerLabels[playerModel.Place].setVerticalTextPosition(JLabel.BOTTOM);
    }

    public void removePlayer(int place) {
        playerLabels[place].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(InterfacePictures+"/absense.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        playerLabels[place].setText("");
    }

    public void openPlayersCards() {
        for(PlayerModel p : windowPlayerModels) {
            if(p == null) continue;
            if(p.LastMove == MoveType.Fold) continue;
            for(int i = 0; i < p.Cards.size(); i++)
                playersCards[p.Place][i].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(CardsPictures+ "/" + p.Cards.get(i).Color + "/" + p.Cards.get(i).Name + ".jpg")).getImage().getScaledInstance(75, 100, Image.SCALE_SMOOTH)));
        }
    }

    public void showPlayersCards(ArrayList<PlayerModel> playerModels) {
        windowPlayerModels = playerModels;
        for(PlayerModel p : playerModels) {
            if(p == null) continue;
            if(p.LastMove == MoveType.Fold) continue;
            for(int i = 0; i < p.Cards.size(); i++) {
                if(p.Place != myPlayerModel.Place)
                    playersCards[p.Place][i].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(CardsPictures+"/shirt.png")).getImage().getScaledInstance(75, 100, Image.SCALE_SMOOTH)));
            }
        }
    }

    public void showMyCards(PlayerModel me) {
        myPlayerModel = me;
        if(me == null) return;
        for(int i = 0; i < me.Cards.size(); i++)
            myCards[i].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(CardsPictures+"/" + me.Cards.get(i).Color + "/" + me.Cards.get(i).Name + ".jpg")).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH)));
        playerLabels[me.Place].setVisible(false);
    }

    public void startGame() {
        for(JLabel jl : tableCardLabels) {
            jl.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(CardsPictures+"/shirt.png")).getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
        }
        startProgressBar();
    }

    public void stopProgressBar() {
        progressBarTimer.cancel();
        progressBarTimer.purge();
        for(JProgressBar pb : playersProgressBars) {
            pb.setValue(0);
            pb.setVisible(false);
        }
        myProgressBar.setValue(0);
        myProgressBar.setVisible(false);
    }

    public void startSpamTimer() {
        spamTimer.purge();
        isSpam = true;
        spamTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isSpam = false;
            }
        }, 300);
    }

    public void startProgressBar() {
        stopProgressBar();
        System.out.println("Client window player turn: " + playerTurnIndex);
        for(JProgressBar pb : playersProgressBars) {
            pb.setVisible(false);
        }
        if(myPlayerModel.Place == playerTurnIndex) {
            myProgressBar.setVisible(true);
        }
        else {
            myProgressBar.setVisible(false);
            playersProgressBars[playerTurnIndex].setVisible(true);
        }
        progressBarTimer = new Timer();
        progressBarTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(playerTurnIndex == myPlayerModel.Place) {
                    myProgressBar.setValue(myProgressBar.getValue()+5);
                    if(myProgressBar.getValue() >= 100) {
                        progressBarTimer.cancel();
                        progressBarTimer.purge();
                    }
                }
                else {
                    playersProgressBars[playerTurnIndex].setValue(playersProgressBars[playerTurnIndex].getValue()+5);
                    if(playersProgressBars[playerTurnIndex].getValue() >= 100) {
                        progressBarTimer.cancel();
                        progressBarTimer.purge();
                    }
                }
            }
        }, 1000, 1000);
    }

    public void setPlayerTurnIndex(int place) {
        playerTurnIndex = place;
    }

    public void setPot(int pot) {
        this.pot = pot;
        potLabel.setText(String.valueOf(pot));
    }

    public void setChips(int chips) {
        chipsLabel.setText("CHIPS: " + chips);
    }

    public void setMyBet(int bet) {
        myBetLabel.setText("BET: " + bet);
    }

    public void setMyPlayer(PlayerModel playerModel) {
        System.out.println("WTF" + playerModel.Bet);
        myPlayerModel = playerModel;
        setChips(myPlayerModel.Chips);
        setMyBet(myPlayerModel.Bet);
    }

    public void setPlayersBets(ArrayList<PlayerModel> playerModels) {
        for(PlayerModel p : playerModels) {
            if(p.Place == myPlayerModel.Place)
            {
                if(p.Bet == 0)
                    myBetLabel.setText("CHECK");
                if(p.Bet > 0)
                    myBetLabel.setText("BET: " + p.Bet);
                continue;
            }
            if(p.Bet == 0)
                playersBetLabels[p.Place].setText("CHECK");
            if(p.Bet > 0)
                playersBetLabels[p.Place].setText("BET: " + p.Bet);
        }
    }

    public void setTableCards(ArrayList<CardModel> cardModels) {
        for(int i = 0; i < 5; i++) {
            if(cardModels.get(i).Opened)
                tableCardLabels[i].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(CardsPictures+"/"+ cardModels.get(i).Color+"/"+ cardModels.get(i).Name+".jpg")).getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
            else
                tableCardLabels[i].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(CardsPictures+"/shirt.png")).getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
        }
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public void setPlayerFold(int playerPlace) {
        for(int i = 0; i < 2; i++)
            playersCards[playerPlace][i].setVisible(false);
        playerLabels[playerPlace].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(InterfacePictures+"/fold_user.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

    }

    public void setWinner(int playerPlace) {
        winnerIndex = playerPlace;
    }

    public void setMyChips(int chips) {
        this.chips = chips;
    }

    public void showWinner() {
        stopProgressBar();
        if(winnerIndex < 0) return;
        playerLabels[winnerIndex].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(InterfacePictures+"/winner_user.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    }

    public void setMoveButtons() {
        if((currentBet == 0)||(currentBet == myPlayerModel.Bet)) {
            CALLButton.setVisible(false);
            RAISEButton.setVisible(false);
            BETButton.setVisible(true);
            CHECKButton.setVisible(true);
        } else {
            CALLButton.setVisible(true);
            RAISEButton.setVisible(true);
            BETButton.setVisible(false);
            CHECKButton.setVisible(false);
        }
    }

    public void close()
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
