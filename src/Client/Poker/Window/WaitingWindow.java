package Client.Poker.Window;

import Client.Commands.UpdateInfo;
import Client.Poker.PokerContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WaitingWindow extends JFrame {
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel interfacePanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel loadingLabel;

    public void updateInfo() {
        var poker = PokerContainer.getPoker();
        var players = poker.getTable().PlacePlayerMap.size();
        var state = poker.getTable().State;
        label2.setText("Вы успешно подключились к серверу");
        label3.setText("Игроков на сервере: " + players + " / 5. Состояние: " + state);
    }

    public WaitingWindow() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
        new UpdateInfo().send();
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
