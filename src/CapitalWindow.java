/**
 * Created by caleb on 5/11/17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CapitalWindow extends AdvWindow implements ActionListener {

    private AdvPanel capitalLocations = new AdvPanel();
    private AdvPanel capitalSites = new AdvPanel();

    Player p;
    private JButton[] locationButtons = {new JButton("Jex"), new JButton("Lana"), new JButton("Back to Town"), new JButton("Exit Town Interior")};
    private JButton[] optionButtons = {new JButton("Stats"), new JButton("Gear Info"), new JButton("Map"), new JButton("History"), new JButton("Backpack"), new JButton("Explore"), new JButton("Leave")};


    public CapitalWindow(Player p) {
        this.p = p;
        cityView.currentWindow = 1;
        p.locationHistory.add(p.city);


        actions.addBorder(actions, "Actions");
        components();

        mainFrame.add(actions, BorderLayout.AFTER_LAST_LINE);
        cityView.setBackground(Color.GRAY);
        sidebar.setBackground(Color.WHITE);
        mainFrame.add(sidebar, BorderLayout.EAST);
        sidebar.addBorder(sidebar, "Options");
        //mainFrame.add(cityTitle, BorderLayout.CENTER);

        mainFrame.add(cityView, BorderLayout.CENTER);
        //

        //setting viewable

        sidebar.setVisible(true);
        actions.setVisible(true);
        mainFrame.setVisible(true);
    }

    void cityComponents() {
        GridLayout cityGrid = new GridLayout(0, 6);
        cityGrid.setHgap(10);
        cityGrid.setVgap(2);
        cityView.addBorder(cityView, "Capital");
        cityView.setLayout(cityGrid);
        cityView.add(new JLabel(""));
        cityView.add(new JLabel(""));
        addImage(cityView, "house.png");
        cityView.add(new JLabel(""));
        cityView.add(new JLabel(""));
        cityView.add(new JLabel(""));
        cityView.add(new JLabel(""));
        addImage(cityView, "bank.png");
        cityView.add(new JLabel(""));
        cityView.add(new JLabel(""));
        addImage(cityView, "house(1).png");
        cityView.add(new JLabel(""));
        cityView.add(new JLabel(""));
        cityView.add(new JLabel(""));
        cityView.add(new JLabel(""));
        addImage(cityView, "house(2).png");
        cityGrid.layoutContainer(cityView);
        showInnerCityOptions();
    }

    void addImage(AdvPanel panel, String imagePath) {
        JLabel label = new JLabel();
        label.setIcon(panel.createImage(imagePath));
        label.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (imagePath.equals("bank.png")) {
//                    mainFrame.remove(sidebar);
//                    mainFrame.remove(actions);
//                    mainFrame.remove(cityView);
//                    mainFrame.remove(capitalLocations);
//                    mainFrame.remove(capitalSites);
//                    cityView.setVisible(false);
//                    sidebar.setVisible(false);
//                    actions.setVisible(false);
//                    capitalLocations.setVisible(false);
//                    capitalSites.setVisible(false);
                    //MonsterWindow monster = new MonsterWindow(2, p.m.monsterHealth, p.m.MONSTER_NAMES, 10, 30, p);
                } else if (imagePath.equals("house.png")) {

                } else if (imagePath.equals("house(1).png")) {

                } else if (imagePath.equals("house(2).png")) {

                }
                System.out.println("Yay you clicked me");
            }
        });
        panel.add(label);
    }



    void components() {
        GridLayout grid = new GridLayout(8, 8);
        sidebar.setLayout(grid);
//        BoxLayout layout = new BoxLayout(actions, BoxLayout.Y_AXIS);
//        actions.setLayout(layout);

        for (int b = 0; b <= optionButtons.length - 1; b++) {
            if (b <= 4) {

                addSidebarElement(b);


            } else {
                actions.add(optionButtons[b]);
                optionButtons[b].setAlignmentX(Component.CENTER_ALIGNMENT);
                optionButtons[b].addActionListener(this);
            }
        }
    }

    void addSidebarElement(int rep) {
        sidebar.add(optionButtons[rep]);
        optionButtons[rep].setAlignmentX(Component.CENTER_ALIGNMENT);
        optionButtons[rep].setPreferredSize(new Dimension(100, 100));
        optionButtons[rep].addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == optionButtons[0]) { // "Stats"
            JOptionPane.showMessageDialog(null, p.Stats(), "Stats", JOptionPane.INFORMATION_MESSAGE, statsIcon);
        } else if (e.getSource() == optionButtons[1]) { // "Gear"
            JOptionPane.showMessageDialog(null, p.gear.gearInfo(), "Gear", JOptionPane.INFORMATION_MESSAGE, gearIcon);
        } else if (e.getSource() == optionButtons[2]) { // "Map"
            JOptionPane.showMessageDialog(null, p.map(), "Map", JOptionPane.INFORMATION_MESSAGE, mapIcon);
        } else if (e.getSource() == optionButtons[3]) { // "History
            JOptionPane.showMessageDialog(null, p.history(), "Location History", JOptionPane.INFORMATION_MESSAGE, historyIcon);
        } else if (e.getSource() == optionButtons[4]) { // "Inventory"
            cityView.setVisible(false);
            capitalSites.setVisible(false);
            sidebar.setVisible(false);
            actions.setVisible(false);
            p.tempDistance = 0;
            InventoryWindow backpack = new InventoryWindow(p);
        } else if (e.getSource() == optionButtons[5]) { // "Explore"
            p.playerStats[2]++;
            p.city = "inner_capital";
            AdvPanel innerCity = new AdvPanel();
            for (int i = 0; i < optionButtons.length; i++) {
                actions.remove(optionButtons[i]);
            }
            mainFrame.remove(actions);
            actions.setVisible(false);
            cityComponents();


        } else if (e.getSource() == optionButtons[6]) { // "Leave"
            for (int i = 0; i < optionButtons.length; i++) {
                actions.remove(optionButtons[i]);
            }
            mainFrame.remove(sidebar);
            mainFrame.remove(actions);
            mainFrame.remove(cityView);
            cityView.setVisible(false);
            sidebar.setVisible(false);
            actions.setVisible(false);
            showLocationPanel();
        } else if (e.getSource() == locationButtons[0]) { // "Jex"
            mainFrame.remove(sidebar);
            mainFrame.remove(actions);
            mainFrame.remove(cityView);
            mainFrame.remove(capitalLocations);
            cityView.setVisible(false);
            sidebar.setVisible(false);
            actions.setVisible(false);
            capitalLocations.setVisible(false);
            p.city = "jex";
            GameplayWindow game = new GameplayWindow(p);
        } else if (e.getSource() == locationButtons[1]) { // "Lana"
            mainFrame.remove(sidebar);
            mainFrame.remove(actions);
            mainFrame.remove(cityView);
            mainFrame.remove(capitalLocations);
            cityView.setVisible(false);
            sidebar.setVisible(false);
            actions.setVisible(false);
            capitalLocations.setVisible(false);
            p.city = "lana";
            GameplayWindow game = new GameplayWindow(p);


        } else if (e.getSource() == locationButtons[2] || e.getSource() == locationButtons[3]) { // "Back to Town"
            p.city = "capital";
            mainFrame.remove(sidebar);
            mainFrame.remove(actions);
            mainFrame.remove(cityView);
            mainFrame.remove(capitalLocations);
            mainFrame.remove(capitalSites);
            capitalSites.setVisible(false);
            cityView.setVisible(false);
            sidebar.setVisible(false);
            actions.setVisible(false);
            capitalLocations.setVisible(false);
            GameplayWindow game = new GameplayWindow(p);
        }
    }

    void showInnerCityOptions() {
        mainFrame.add(capitalSites, BorderLayout.AFTER_LAST_LINE);
        capitalSites.addBorder(capitalSites, "City Actions:");
        capitalSites.add(locationButtons[3]);
        locationButtons[3].setAlignmentX(Component.CENTER_ALIGNMENT);
        locationButtons[3].addActionListener(this);

    }

    void showLocationPanel() {
        mainFrame.add(capitalLocations, BorderLayout.AFTER_LAST_LINE);
        capitalLocations.addBorder(capitalLocations, "Locations");
        for (int i = 0; i < locationButtons.length - 1; i++) {
            capitalLocations.add(locationButtons[i]);
            locationButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            locationButtons[i].addActionListener(this);

        }
        capitalLocations.setVisible(true);

    }


}
