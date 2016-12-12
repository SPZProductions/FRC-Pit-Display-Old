/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPZProductions.FRC.pit.display;

import com.cpjd.main.TBA;
import com.cpjd.models.Match;
import com.cpjd.models.Team;
import com.cpjd.models.Event;
import com.cpjd.main.Settings;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;



/**
 *
 * @author SPZ Productions
 */
public class SPZProductionsFRCPitDisplayUI extends javax.swing.JFrame {
    
    public static int teamNumber = 1322;
    public static int year = 2016;
    public static String eventKey = "miket";
    public static SettingsUI settings;
    public static SPZProductionsFRCPitDisplayUI mainDisp;
    
    public static TBA tba = new TBA();
    public static Event e;
    public static Match[] m;
    public static Team t;
    /**
     * Creates new form Team1322PitDisplayUI
     */
    public SPZProductionsFRCPitDisplayUI() {
        initComponents();
        moreInit();
    }
    
    public void moreInit(){
        Settings.FIND_TEAM_RANKINGS = true;
        Settings.GET_EVENT_ALLIANCES = true;
        Settings.GET_EVENT_MATCHES = true;
        Settings.GET_EVENT_TEAMS = true;
        
        tba.setID("team" + teamNumber,"pitDisplay","v1");
        Event e = tba.getEvent(eventKey, year);
        Match[] m = tba.getMatches(eventKey, year);
        Team t = tba.getTeam(teamNumber);
        
        jScrollPane1.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        changeBG(Color.WHITE);
        
        String rookie = "";
        if(Long.toString(t.rookie_year) == String.valueOf(year)){rookie = "Rookie ";}
        teamNumberLabel.setText(rookie + "Team " + Long.toString(t.team_number));
        teamNameLabel.setText(t.name);
        resizeNameLabel();
        teamMottoLabel.setText("\"" + t.motto + "\"");
        teamNicknameLabel.setText(t.nickname);
        getTeamMatches(teamNumber);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(4).setWidth(0);
        
        DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer();
        colorRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        updateRank();
        
        
    }
    
    public void changeTextColor(Color color, int labelNumber){
         if (labelNumber == 0){
             teamNumberLabel.setForeground(color);
         }else if(labelNumber == 1){
             teamNameLabel.setForeground(color);
         }else if(labelNumber == 2){
             teamNicknameLabel.setForeground(color);
         }else if(labelNumber == 3){
             teamMottoLabel.setForeground(color);
         }else if(labelNumber == 4){
             currentRankLabel.setForeground(color);
         }else if(labelNumber == 5){
             jTable1.setForeground(color);
         }
         
         
         
    }
    
    public static void resizeNameLabel(){
        Font labelFont = teamNameLabel.getFont();
        String labelText = teamNameLabel.getText();

        int stringWidth = teamNameLabel.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = teamNameLabel.getWidth();

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = teamNameLabel.getHeight();

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        teamNameLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }
    
    public void removeAllRows(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
        
    public void getTeamMatches(int teamNo){
        Event e = tba.getEvent(eventKey, year);
        Match[] m = tba.getMatches(eventKey, year);
        Team t = tba.getTeam(teamNumber);
        int row = -1;

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        removeAllRows();
        //for each match
        for (Match matches : e.matches){
            String blueTeams = "";
            String redTeams = "";
            
            int i = 0;
            for (String teams : matches.blueTeams){
                String substring = teams.substring(3);
                if(i <= 1){substring += ", "; i++;}
                blueTeams += substring;
            }
           
            int ii = 0;
            for (String teams : matches.redTeams){
                String substring = teams.substring(3);
                if(ii <= 1){substring += ", "; ii++;}
                redTeams += substring;
            }
            String mn;
            //qm makes no sence to me, change it to Q for Qualifying
            if(matches.comp_level.equals("qm")){
                mn = "Q " + matches.match_number;
            }else{
                mn = matches.comp_level.toUpperCase() + " " + matches.match_number;
            }
            
            if(Arrays.toString(matches.redTeams).contains("frc" + teamNo) || Arrays.toString(matches.blueTeams).contains("frc" + teamNo)){
                
                if(matches.redScore > matches.blueScore){
                    model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a><b>" + matches.redScore + "</b> - " + matches.blueScore + "</a></html>", "red"});
                    row ++;
                }else if(matches.redScore < matches.blueScore){
                    model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a>" + matches.redScore + " - <b>" + matches.blueScore + "</b></a></html>", "blue"});
                    row ++;
                }else{
                    model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a><b>" + matches.redScore + " - " + matches.blueScore + "</b></a></html>", "tie"});
                    row ++;
                }
            } 
        }
        autoSizeColums();
    }
    
    public void updateRank(){
        Event e = tba.getEvent(eventKey, year);
        int i = 0;
        for(Team team : e.teams){
            if(team.team_number == teamNumber){
                i++;
                break;
            }
            i++;
        }
        currentRankLabel.setText("Current Rank: " +  i);
    }
    
    public static void autoSizeColums(){
        
        jTable1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        for (int column = 0; column < jTable1.getColumnCount(); column++){
            TableColumn tableColumn = jTable1.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < jTable1.getRowCount(); row++){
                TableCellRenderer cellRenderer = jTable1.getCellRenderer(row, column);
                Component c = jTable1.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + jTable1.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows

                if (preferredWidth >= maxWidth){
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth( preferredWidth + 80 );
        }
    }
    
    public void updateDisplays(){
        
    }
    
    public void changeBG(Color color){
        this.getContentPane().setBackground(color);
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        teamMottoLabel = new javax.swing.JLabel();
        teamNumberLabel = new javax.swing.JLabel();
        teamNameLabel = new javax.swing.JLabel();
        teamNicknameLabel = new javax.swing.JLabel();
        currentRankLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setWheelScrollingEnabled(false);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Match Number", "Red Alliance", "Blue Alliance", "Final Score", "whoWon"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setOpaque(false);
        jTable1.setRowHeight(50);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        teamMottoLabel.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        teamMottoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamMottoLabel.setText("The G.R.A.Y.T. Leviathons");
        teamMottoLabel.setAlignmentX(0.5F);

        teamNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNumberLabel.setText("Team 1322");
        teamNumberLabel.setAlignmentX(0.5F);

        teamNameLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNameLabel.setText("The G.R.A.Y.T. Leviathons");
        teamNameLabel.setAlignmentX(0.5F);

        teamNicknameLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNicknameLabel.setText("The G.R.A.Y.T. Leviathons");
        teamNicknameLabel.setAlignmentX(0.5F);

        currentRankLabel.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        currentRankLabel.setText("Current Rank:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentRankLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1187, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(teamNicknameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(teamNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(teamMottoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(teamNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(teamNicknameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(teamNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(teamMottoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentRankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(130, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(teamNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(986, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_F1){
            if(!settings.isVisible()){
                settings.setVisible(true);
            }
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(SPZProductionsFRCPitDisplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SPZProductionsFRCPitDisplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SPZProductionsFRCPitDisplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SPZProductionsFRCPitDisplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainDisp = new SPZProductionsFRCPitDisplayUI();
                mainDisp.setVisible(true);
                settings = new SettingsUI();
                settings.setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentRankLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable1;
    public static javax.swing.JLabel teamMottoLabel;
    public static javax.swing.JLabel teamNameLabel;
    public static javax.swing.JLabel teamNicknameLabel;
    private javax.swing.JLabel teamNumberLabel;
    // End of variables declaration//GEN-END:variables
}
