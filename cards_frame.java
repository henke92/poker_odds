package cards;

import cards.Card;
import cards.Deck;
import java.awt.Graphics;
import java.awt.Toolkit;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import cards.Engine;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * @author Henke
 */

public class cards_frame extends javax.swing.JFrame {

    
    final String [] ranking_names = {"High card",
                            "Pair",
                            "Two-pair",
                            "Three of a kind",
                            "Straight",
                            "Flush",
                            "Full house",
                            "Four of a kind",
                            "Straight flush",
                            "Royal Straight flush"
    };
    
    
    
    final int card_width_picture = 66;
    final int card_heigth_picture = 95;
    
    BufferedImage img,sub_image; 
    
    int street = 0;
    int player_value,opponent_value;
    int manual_hand_street;
    
    private double [] sim_values;
    private double [] sim_values_2;
    
    Engine eng;
    
    
    
    
    
    /**
     * Creates new form cards_frame
     */
    
    
    public void reset_images()
    {
        
        
        ImageIcon i_c = new ImageIcon();
        
        //back side of card
        sub_image = img.getSubimage(0,card_heigth_picture*4, card_width_picture , card_heigth_picture);
        ImageIcon i_icon = new ImageIcon(sub_image);
        
        this.jLabel1.setIcon(null);
        this.jLabel2.setIcon(null);
        this.jLabel3.setIcon(i_icon);
        this.jLabel4.setIcon(i_icon);
        this.jLabel5.setIcon(i_icon);
        this.jLabel6.setIcon(i_icon);
        this.jLabel7.setIcon(i_icon);
        this.jLabel9.setIcon(null);
        this.jLabel10.setIcon(null);
        this.jLabel11.setIcon(null);
        
        this.jLabel8.setText("");
        this.jLabel11.setText("");
        this.jLabel12.setText("");
        this.jLabel8.paintImmediately(this.jLabel8.getVisibleRect());
        this.jLabel11.paintImmediately(this.jLabel11.getVisibleRect());
        this.jLabel12.paintImmediately(this.jLabel12.getVisibleRect());
        
        this.jLabel1.update(this.jLabel1.getGraphics());
        this.jLabel2.update(this.jLabel2.getGraphics());
        this.jLabel3.update(this.jLabel3.getGraphics());
        this.jLabel4.update(this.jLabel4.getGraphics());
        this.jLabel5.update(this.jLabel5.getGraphics());
        this.jLabel6.update(this.jLabel6.getGraphics());
        this.jLabel7.update(this.jLabel7.getGraphics());
        this.jLabel9.update(this.jLabel9.getGraphics());
        this.jLabel10.update(this.jLabel10.getGraphics());
        this.jLabel8.update(this.jLabel8.getGraphics());
        this.jLabel11.update(this.jLabel11.getGraphics());
    }
    
    public void update_player_card_images()
    {
        int val0,val1,suit0,suit1;
        val0 = eng.get_player_card_value(0);
        val1 = eng.get_player_card_value(1);
        suit0 = eng.get_player_card_suit(0);
        suit1 = eng.get_player_card_suit(1);
        
        
        // player cards
        sub_image = img.getSubimage(card_width_picture*val0,card_heigth_picture*suit0, card_width_picture , card_heigth_picture);
        ImageIcon i_icon = new ImageIcon(sub_image);
        this.jLabel1.setIcon(i_icon);
        this.jLabel1.update(this.jLabel1.getGraphics());
        
        sub_image = img.getSubimage(card_width_picture*val1,card_heigth_picture*suit1, card_width_picture , card_heigth_picture);
        i_icon = new ImageIcon(sub_image);
        this.jLabel2.setIcon(i_icon);
        this.jLabel2.update(this.jLabel2.getGraphics());
    }
    
    public void update_opponent_card_images(int show_cards)
    {
        if(show_cards == 0)
        {
            int val0,val1,suit0,suit1;        
            val0 = eng.get_opp_card_value(0);
            val1 = eng.get_opp_card_value(1);
            suit0 = eng.get_opp_card_suit(0);
            suit1 = eng.get_opp_card_suit(1);
            
            sub_image = img.getSubimage(card_width_picture*val0,card_heigth_picture*suit0, card_width_picture , card_heigth_picture);
            ImageIcon  i_icon = new ImageIcon(sub_image);
            this.jLabel9.setIcon(i_icon);
            this.jLabel9.update(this.jLabel9.getGraphics());
        
            sub_image = img.getSubimage(card_width_picture*val1,card_heigth_picture*suit1, card_width_picture , card_heigth_picture);
            i_icon = new ImageIcon(sub_image);
            this.jLabel10.setIcon(i_icon);
            this.jLabel10.update(this.jLabel10.getGraphics());
            
            
        }
        else
        {
            sub_image = img.getSubimage(0,card_heigth_picture*4, card_width_picture , card_heigth_picture);
            ImageIcon i_icon = new ImageIcon(sub_image);
            this.jLabel9.setIcon(i_icon);
            this.jLabel10.setIcon(i_icon);
        }
        
        
    }
    
    public void update_board_images(int n)
    {
        ImageIcon i_icon;
        int val = 0,suit = 0;
       
        
        switch(n)
        {
            case 0:
                //preflop
                update_player_card_images();
                update_opponent_card_images(0);
                break;
            case 1:
                // flop
                
                val = eng.get_board_value(0);
                suit = eng.get_board_suit(0);
                sub_image = img.getSubimage(card_width_picture*val,card_heigth_picture*suit, card_width_picture , card_heigth_picture);
                i_icon = new ImageIcon(sub_image);
                this.jLabel3.setIcon(i_icon);
                
                val = eng.get_board_value(1);
                suit = eng.get_board_suit(1);
                sub_image = img.getSubimage(card_width_picture*val,card_heigth_picture*suit, card_width_picture , card_heigth_picture);
                i_icon = new ImageIcon(sub_image);
                this.jLabel4.setIcon(i_icon);
                
                val = eng.get_board_value(2);
                suit = eng.get_board_suit(2);
                sub_image = img.getSubimage(card_width_picture*val,card_heigth_picture*suit, card_width_picture , card_heigth_picture);
                i_icon = new ImageIcon(sub_image);
                this.jLabel5.setIcon(i_icon);
                break;
            case 2:
                // turn
                val = eng.get_board_value(3);
                suit = eng.get_board_suit(3);
                 sub_image = img.getSubimage(card_width_picture*val,card_heigth_picture*suit, card_width_picture , card_heigth_picture);
                i_icon = new ImageIcon(sub_image);
                this.jLabel6.setIcon(i_icon);
                break;
            case 3:
                val = eng.get_board_value(4);
                suit = eng.get_board_suit(4);
                sub_image = img.getSubimage(card_width_picture*val,card_heigth_picture*suit, card_width_picture , card_heigth_picture);
                i_icon = new ImageIcon(sub_image);
                this.jLabel7.setIcon(i_icon);
                //river
                break;
            default:
                break;
        }
    }
    



    
public void timeDelay(long t) 
    {
    try {
        Thread.sleep(t);
    } catch (InterruptedException e) 
    {}
}
    
    
    public cards_frame() throws IOException {
        initComponents();
        img = ImageIO.read(new File("img_cards.png"));
        eng = new Engine();
        manual_hand_street = 0;
    }
    
   
    public void update_balances_on_screen()
    {
        
        if(eng.get_street() >= 2 && eng.get_street() < 5)
        {
            player_value = 0;
            opponent_value = 0;
            if(eng.get_street() == 2)
            {
                player_value = eng.eval_flop(0);
                opponent_value = eng.eval_flop(1);
            }
            else if(eng.get_street() == 3)
            {
                player_value = eng.eval_turn(0);
                opponent_value = eng.eval_turn(1);
            }
            else if(eng.get_street() == 4)
            {
                update_opponent_card_images(0);
                player_value = eng.eval_player_cards_end();
                opponent_value = eng.eval_opponent_cards_end();
                int winner = eng.get_river_winner();
                
                String s = "";
                
                if(winner == 0)
                {
                    s = "Player 100%\nOpponent0%\nDraw0%";
                    this.jLabel12.setText("Player wins with "+ranking_names[player_value]);
                }
                else if(winner == 1)
                {
                    s = "Player 0%\nOpponent100%\nDraw0%";
                    this.jLabel12.setText("Opponent wins with "+ranking_names[opponent_value]);
                }
                else
                {
                    s = "Player 0%\nOpponent0%\nDraw100%";
                    this.jLabel12.setText("Draw "+ranking_names[player_value]);
                }
                
                this.jTextArea1.setText(s);
                this.jTextArea2.setText(s);
                
            }
        }
        if(eng.get_street() >= 2)
        {
            this.jLabel8.setText("");
            this.jLabel11.setText("");
            this.jLabel8.paintImmediately(this.jLabel8.getVisibleRect());
            this.jLabel11.paintImmediately(this.jLabel8.getVisibleRect());
            this.jLabel8.setText(ranking_names[player_value]);
            this.jLabel11.setText(ranking_names[opponent_value]);
            
        }
        
        this.jLabel8.update(this.jLabel8.getGraphics());
        this.jLabel11.update(this.jLabel11.getGraphics());
        this.jLabel3.update(this.jLabel3.getGraphics());
        this.jLabel4.update(this.jLabel4.getGraphics());
        this.jLabel5.update(this.jLabel5.getGraphics());
        this.jLabel6.update(this.jLabel6.getGraphics());
        this.jLabel7.update(this.jLabel7.getGraphics());
    }
    
    public void simulate_win_no_comp(int street)
    {
        //values 0 = player_perc
        //values 1 = opponent_perc
        //values 2 = draw_perc
        
        System.out.println(street);
        
        //street == 0 preflop
        //       == 1 flop
        //       == 2 turn
        
        sim_values_2 = new double [3];
        for(int z = 0; z < 3; z++)
        {
            sim_values_2[z] = 0.0;
        }
        eng.simulate_winner_no_comp(street, sim_values_2);
        for(int z = 0; z < 3; z++)
        {
            // (x/1000)/10 = %
            sim_values_2[z] /= 10;
        }
        
        String s = "With random computer cards \n\n";
        s += "Player: "+String.valueOf(sim_values_2[0]+ "%");
        s += "\n";
        s += "Opponent: "+String.valueOf(sim_values_2[1]+ "%");
        s += "\n";
        s += "Draw: "+String.valueOf(sim_values_2[2]+ "%");
        s += "\n";
        this.jTextArea2.setText(s);
        this.jTextArea2.paintImmediately(this.jTextArea2.getVisibleRect());
        
        
    }
    
    public void simulate_win(int street)
    {
        //values 0 = player_perc
        //values 1 = opponent_perc
        //values 2 = draw_perc
        
        //street == 0 preflop
        //       == 1 flop
        //       == 2 turn
        
        sim_values = new double [3];
        for(int z = 0; z < 3; z++)
        {
            sim_values[z] = 0.0;
        }
        eng.simulate_winner(street, sim_values);
        for(int z = 0; z < 3; z++)
        {
            // (x/1000)/10 = %
            sim_values[z] /= 10;
        }
        
        String s = "With computer cards \n\n";
        s += "Player: "+String.valueOf(sim_values[0]+ "%");
        s += "\n";
        s += "Opponent: "+String.valueOf(sim_values[1]+ "%");
        s += "\n";
        s += "Draw: "+String.valueOf(sim_values[2]+ "%");
        s += "\n";
        this.jTextArea1.setText(s);
        this.jTextArea1.paintImmediately(this.jTextArea1.getVisibleRect());
        
        
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jButton1.setText("New hand");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel2.setText("jLabel1");
        jLabel2.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel3.setText("jLabel1");
        jLabel3.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel4.setText("jLabel1");
        jLabel4.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel5.setText("jLabel1");
        jLabel5.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel6.setText("jLabel1");
        jLabel6.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel7.setText("jLabel1");
        jLabel7.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("...");

        jLabel9.setText("jLabel1");
        jLabel9.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel10.setText("jLabel1");
        jLabel10.setMaximumSize(new java.awt.Dimension(135, 189));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("...");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("...");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Player");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Opponent");

        jButton2.setText("New hand quick");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jButton3.setText("Manual deal ( Enter/Right)");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Deal count all rankings");
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea2.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(218, 218, 218))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    public void next(int quick_state)
    {
        eng.next_street();
        if(eng.get_street() == 1 && quick_state == 0)
        {
            //simulate pre flop
            simulate_win(0);
            simulate_win_no_comp(0);
            
        }
        else if(eng.get_street() == 2 && quick_state == 0)
        {
            //simulate flop
            simulate_win(1);
            simulate_win_no_comp(1);
            
        }
        else if(eng.get_street() == 3 && quick_state == 0)
        {
           //simulate turn
            simulate_win(2);
            simulate_win_no_comp(2);
        }
        update_board_images(eng.get_street()-1);
        update_balances_on_screen();
    }
   
    
    public void new_deal(int quick_state)
    {
        reset_images();
        eng.new_hand();
        for(int ix = 0; ix < 4; ix++)
        {
            next(quick_state);
            if(ix < 3)
            {
                if(quick_state == 0)
                {
                    timeDelay(3000);
                }       
            }
        }
    }
    
    public void next_manual_street()
    {
        eng.next_street();
        if(eng.get_street() == 1)
        {
            //simulate pre flop
            simulate_win(0);
            simulate_win_no_comp(0);
            
        }
        else if(eng.get_street() == 2)
        {
            //simulate flop
            simulate_win(1);
            simulate_win_no_comp(1);
            
        }
        else if(eng.get_street() == 3)
        {
           //simulate turn
            simulate_win(2);
            simulate_win_no_comp(2);
        }
        update_board_images(eng.get_street()-1);
        update_balances_on_screen();
    }
    
    public void manual_deal()
    {
        if(manual_hand_street == 0)
        {
            reset_images();
            eng.new_hand();
            this.jButton1.setEnabled(false);
            this.jButton2.setEnabled(false);
        }
        if(manual_hand_street == 3)
        {
            this.jButton1.setEnabled(true);
            this.jButton2.setEnabled(true);
            manual_hand_street = -1;
        }
        next_manual_street();
        manual_hand_street++; 
        
        
    }
    
    
    public void deal_without_images()
    {
        eng.new_hand();
        for(int ix = 0; ix < 4; ix++)
        {
            eng.next_street();
        }
    }
    
            
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        new_deal(0);
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        

// TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new_deal(1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        manual_deal();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        System.out.println(evt.getKeyCode());
        if(evt.getKeyCode() == 10 || evt.getKeyCode() == 39)
        {
            manual_deal();
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        boolean status = false;
        int count = 0;
        boolean [] found = new boolean[10];
        int [] count_stats = new int[10];
        
        for(int ix = 0; ix < 10; ix++)
        {
            found[ix] = false;
            count_stats[ix] = -1;
        }
        while(!status)
        {
            System.out.println(count);
            deal_without_images();
            int x0 = eng.eval_player_cards_end();
            int x1 = eng.eval_opponent_cards_end();
            if(!found[x0])
            {
                update_board_images(0);
                update_board_images(1);
                update_board_images(2);
                update_board_images(3);
                update_balances_on_screen();
                timeDelay(3000);
                found[x0] = true;
                count_stats[x0] = count;
            }
            if(!found[x1])
            {
                update_board_images(0);
                update_board_images(1);
                update_board_images(2);
                update_board_images(3);
                update_balances_on_screen();
                timeDelay(3000);
                found[x1] = true;
                count_stats[x1] = count;
            }
           
            boolean all = true;
            for(int ix = 0; ix < 10; ix++)
            {
                if(!found[ix])
                {
                    all = false;
                }
            }
            if(all)
            {
                status = true;
            }
            count++;
            this.jTextArea1.setText("count "+count);
            this.jTextArea1.paintImmediately(this.jTextArea1.getVisibleRect());
        }
        String s = "";
        for(int ix = 0; ix < 10; ix++)
        {
            s += ranking_names[ix] + ": "+count_stats[ix]+"\n";
        }
        this.jTextArea1.setText(s);
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cards_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cards_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cards_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cards_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new cards_frame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(cards_frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
