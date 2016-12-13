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
import java.util.HashMap;
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
    private CustomRenderer renderer;
    public static boolean isFullscreen = false;
    public static Color headerBgColor = Color.LIGHT_GRAY;
    public static Color headerFgColor = Color.BLACK;
    
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
        changeColor(Color.WHITE, 8);
        
        Settings.FIND_TEAM_RANKINGS = true;
        Settings.GET_EVENT_ALLIANCES = true;
        Settings.GET_EVENT_MATCHES = true;
        Settings.GET_EVENT_TEAMS = true;
        
        tba.setID("team" + teamNumber,"pitDisplay","v1");
        Event e = tba.getEvent(eventKey, year);
        Match[] m = tba.getMatches(eventKey, year);
        Team t = tba.getTeam(teamNumber);
        
        jScrollPane1.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        
        String rookie = "";
        if(Long.toString(t.rookie_year) == String.valueOf(year)){rookie = "Rookie ";}
        teamNumberLabel.setText(rookie + "Team " + Long.toString(t.team_number));
        teamNameLabel.setText(t.name);
        resizeNameLabel();
        teamMottoLabel.setText("\"" + t.motto + "\"");
        teamNicknameLabel.setText(t.nickname);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        
        renderer = new CustomRenderer();
        renderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(3).setCellRenderer( renderer );
        jTable1.setRowSelectionAllowed(false);
        jTable1.setCellSelectionEnabled(false);
        
        getTeamMatches(teamNumber);
        
        jTable1.getTableHeader().setDefaultRenderer(new headerColor());
        
        updateRank();
    }
    
    public static class headerColor extends DefaultTableCellRenderer{
        public headerColor(){
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable mytable, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(mytable, value, selected, focused, row, column);
            setBackground(headerBgColor);
            setForeground(headerFgColor);
            setFont(new java.awt.Font("Tahoma", 1, 16));
            setHorizontalAlignment( JLabel.CENTER );
            return this;
        }
    }
    
    public void changeColor(Color color, int labelNumber){
        switch (labelNumber) {
            case 0:
                teamNumberLabel.setForeground(color);
                break;
            case 1:
                teamNicknameLabel.setForeground(color);
                break;
            case 2:
                teamNameLabel.setForeground(color);
                break;
            case 3:
                teamMottoLabel.setForeground(color);
                break;
            case 4:
                currentRankLabel.setForeground(color);
                break;
            case 5:
                jTable1.setForeground(color);
                break;
            case 6:
                headerBgColor = color;
                jTable1.getTableHeader().setBackground(color);
                break;
            case 7:
                headerFgColor = color;
                jTable1.getTableHeader().setForeground(color);
                break;
            case 8:
                getContentPane().setBackground(color);
            default:
                break;
        }  
    }
    
    public void resizeNameLabel(){
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
            String matchNumber = null;
            
            if(matches.match_number < 10){
                matchNumber = "0" + matches.match_number;
            }else{
                matchNumber = "" + matches.match_number;
            }
            
            String mn;
            //qm makes no sence to me, change it to Q for Qualifying
            if(matches.comp_level.equals("qm")){
                mn = "Q " + matchNumber;
            }else{
                mn = matches.comp_level.toUpperCase() + " " + matchNumber;
            }
            
            if(Arrays.toString(matches.redTeams).contains("frc" + teamNo) || Arrays.toString(matches.blueTeams).contains("frc" + teamNo)){
                row++;
                if(matches.redScore > matches.blueScore){
                    model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a><b>" + matches.redScore + "</b> - " + matches.blueScore + "</a></html>"});
                    renderer.colorModel.put(row, Color.RED);
                }else if(matches.redScore < matches.blueScore){
                    model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a>" + matches.redScore + " - <b>" + matches.blueScore + "</b></a></html>"});
                    renderer.colorModel.put(row, Color.BLUE);
                }else{
                    model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a><b>" + matches.redScore + " - " + matches.blueScore + "</b></a></html>"});
                    renderer.colorModel.put(row, Color.WHITE);
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
    
    public void autoSizeColums(){
        
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
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Match Number", "Red Alliance", "Blue Alliance", "Final Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setFocusable(false);
        jTable1.setRowHeight(50);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        teamMottoLabel.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        teamMottoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamMottoLabel.setText("The G.R.A.Y.T. Leviathons");
        teamMottoLabel.setAlignmentX(0.5F);
        teamMottoLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        teamNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNumberLabel.setText("Team 1322");
        teamNumberLabel.setAlignmentX(0.5F);
        teamNumberLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        teamNameLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNameLabel.setText("The G.R.A.Y.T. Leviathons");
        teamNameLabel.setAlignmentX(0.5F);
        teamNameLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        teamNicknameLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNicknameLabel.setText("The G.R.A.Y.T. Leviathons");
        teamNicknameLabel.setAlignmentX(0.5F);
        teamNicknameLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        currentRankLabel.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        currentRankLabel.setText("Current Rank:");
        currentRankLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(currentRankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 759, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(teamNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1016, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        System.out.println("Key Pressed");
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_F1){
            if(!settings.isVisible()){
                settings.setVisible(true);
            }else{
                settings.toFront();
                settings.repaint();
            }
        }else if(key == KeyEvent.VK_F5 || key == KeyEvent.VK_ESCAPE){
            settings.fullscreenToggle();
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SPZProductionsFRCPitDisplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    
    public static class CustomRenderer extends DefaultTableCellRenderer{

        //Stores what color you want for rows
        public HashMap<Integer, Color> colorModel = new HashMap<Integer, Color>();

        @Override
        public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //Default Rendering
            Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            //Change color of background (If necessary)
            if(colorModel.get(row) != null){
                setBackground(colorModel.get(row));
            } else if(!isSelected){
                setBackground(null);
            }
            return result;
        }

    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel currentRankLabel;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JLabel teamMottoLabel;
    public static javax.swing.JLabel teamNameLabel;
    public static javax.swing.JLabel teamNicknameLabel;
    public static javax.swing.JLabel teamNumberLabel;
    // End of variables declaration//GEN-END:variables
}
