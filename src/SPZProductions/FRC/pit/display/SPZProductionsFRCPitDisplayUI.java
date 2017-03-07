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
import com.cpjd.models.other.DistrictRanking;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;



/**
 * @author Soren Zaiser, FRC Team 1322
 * @author SPZ Productions
 */
public class SPZProductionsFRCPitDisplayUI extends javax.swing.JFrame {
    public static SPZProductionsFRCPitDisplayUI mainDisp;
    public static SettingsUI settings;
    public static updateLoop update;
            
    private CustomRenderer renderer;
    public boolean isFullscreen = false;
    public Color headerBgColor = Color.LIGHT_GRAY;
    public Color headerFgColor = Color.BLACK;
    
    public int teamNumber = 1322;
    public int year = 2017;
    public String eventKey = "miket";
    public TBA tba = new TBA();
    public Event e;
    public Match m;
    public Team[] teams;
    public Team t;
    public DistrictRanking[] rankings;
    public int matchCount = 0;
    
    /**
     * Creates new form Team1322PitDisplayUI
     */
    public SPZProductionsFRCPitDisplayUI() {
        initComponents();
        moreInit();
    }
    
    public void moreInit(){
        //Still Setting up display, not loading info yet
        
        //Set Background color white and create/remove borders
        changeColor(Color.WHITE, 8);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setBorder(null);
        jTable1.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //Disable Scrollbar
        jScrollPane1.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        //Center Text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        //Apply Custom color renderer to 4th(3) coloum for match winner coloring
        renderer = new CustomRenderer();
        renderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(3).setCellRenderer( renderer );
        //Disable selection
        jTable1.setRowSelectionAllowed(false);
        //Disable cell selection
        jTable1.setCellSelectionEnabled(false);
        //Apply Custom Header Renderer
        
        
       
        tba.setID("team" + teamNumber,"pitDisplay","v2");
        Event e = tba.getEvent(eventKey, year);
        rankings = tba.getDistrictRankings(eventKey, year);
        Team t = tba.getTeam(teamNumber);
        
        //Start TBA Data Initilization
        Settings.FIND_TEAM_RANKINGS = true;
        Settings.GET_EVENT_ALLIANCES = true;
        Settings.GET_EVENT_MATCHES = true;
        Settings.GET_EVENT_TEAMS = true;
        Settings.useAPIV3(false);
        
        String rookie = "";
        if(Long.toString(t.rookie_year).contains(String.valueOf(year))){rookie = "Rookie ";}
        teamNumberLabel.setText(rookie + "Team " + Long.toString(t.team_number));
        teamNameLabel.setText(t.name);
        resizeNameLabel();
        teamMottoLabel.setText("\"" + t.motto + "\"");
        teamNicknameLabel.setText(t.nickname);
        
        getTeamMatches();
        
        updateRank();
        
        getNextMatch();
    }
    
    public void update(){
        tba.setID("team" + teamNumber,"spzProductionsPitDisplay","v2");
        Event e = tba.getEvent(eventKey, year);
        Team t = tba.getTeam(teamNumber);
        
        String rookie = "";
        if(Long.toString(t.rookie_year) == String.valueOf(year)){rookie = "Rookie ";}
        teamNumberLabel.setText(rookie + "Team " + Long.toString(t.team_number));
        teamNameLabel.setText(t.name);
        resizeNameLabel();
        teamMottoLabel.setText("\"" + t.motto + "\"");
        teamNicknameLabel.setText(t.nickname);
        
        getTeamMatches();
        
        updateRank();
        
        getNextMatch();       
        
    }
    
    public class headerColor extends DefaultTableCellRenderer{
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
                break;
            case 9:
                totalRPLabel.setForeground(color);
                totalMatchLabel.setForeground(color);
                totalFuelLabel.setForeground(color);
                totalAutonLabel.setForeground(color);
                totalPlayedLabel.setForeground(color);
                totalRecordLabel.setForeground(color);
                totalRotorsLabel.setForeground(color);
                totalPointsLabel.setForeground(color);
                break;
            case 10:
                jPanel1.setBackground(color);
                break;
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
        
    public void getTeamMatches(){
        Event e = tba.getEvent(eventKey, year);
        int row = -1;
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        removeAllRows();
        //for each match
        
        for (Match matches : e.matches){
            
            String blueTeams = "";
            String redTeams = "";
            boolean nullMatch = false;
            
            if(Arrays.toString(matches.scorableItems).equals("null")){
                nullMatch = true;
            }
                
                     
            int i = 0;
            for (String teamsL : matches.blueTeams){
                String substring = teamsL.substring(3);
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
            
           // if(matches.match_number < 10){
                //matchNumber = "0" + matches.match_number;
            //}else{
                matchNumber = "" + matches.match_number;
            //}
            
            String mn;
            //qm makes no sence to me, change it to Q for Qualifying
            if(matches.comp_level.equals("qm")){
                mn = "Q " + matchNumber;
            }else{
                mn = matches.comp_level.toUpperCase() + " " + matchNumber;
            }
            boolean blue = true;
            if(!nullMatch){
                if(Arrays.toString(matches.redTeams).contains("frc" + teamNumber) || Arrays.toString(matches.blueTeams).contains("frc" + teamNumber)){
                    if(Arrays.toString(matches.redTeams).contains("frc" + teamNumber)){blue = false;}
                    row++;
                    if(matches.redScore > matches.blueScore){
                        String W = " (L)";
                        if(!blue){W = " (W)";}
                        model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a><b>" + matches.redScore + "</b> - " + matches.blueScore + W + "</a></html>"});
                        renderer.colorModel.put(row, Color.RED);
                    }else if(matches.redScore < matches.blueScore){
                        String W = " (L)";
                        if(blue){W = " (W)";}
                        model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a>" + matches.redScore + " - <b>" + matches.blueScore + "</b>" + W + "</a></html>"});
                        renderer.colorModel.put(row, Color.BLUE);
                    }else{
                        model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a><b>" + matches.redScore + " - " + matches.blueScore + " (T) </b></a></html>"});
                        renderer.colorModel.put(row, Color.BLACK);
                    }
                    matchCount++;
                } 
            }else{
                
                if(Arrays.toString(matches.redTeams).contains("frc" + teamNumber) || Arrays.toString(matches.blueTeams).contains("frc" + teamNumber)){
                    row++;
                    model.addRow(new Object[]{mn, redTeams, blueTeams, "<html><a><b>Unplayed</b></a></html>"});
                    renderer.colorModel.put(row, Color.BLACK);
                    
                }
            }
        }
        autoSizeColums();
    }
    
    public void updateRank(){
        Event e = tba.getEvent(eventKey, year);
        long rank = 0;
        double rankScore = 0;
        double matchPoints = 0;
        double auto = 0;
        double rotor = 0;
        double touchpad = 0;
        double pressure = 0;
        String record = "N/A";
        
        
        
        for(Team team : e.teams){
            if(teamNumber == (int) team.team_number){
                rank = team.rank;
                rankScore = team.rankingScore;
                matchPoints = team.matchPoints;
                auto = team.auto;
                rotor = team.rotor;
                touchpad = team.touchpad;
                pressure = team.pressure;
                record = team.record;
                break;
            }
        }
        /*TODO: Fix Match Counter */
        currentRankLabel.setText("<html><b>Current Rank: " + rank + "</b></html>");
        totalRPLabel.setText("<html><b>Ranking Score: </b>" + rankScore + "</html>");
        totalPlayedLabel.setText("<html><b>Total Played Matches: </b>" + matchCount + "</html>");
        totalRecordLabel.setText("<html><b>Record (W-L-T): </b>" + record + "</html>");
        
        totalAutonLabel.setText("<html><b>Auton: </b>" + auto + "</html>");
        totalFuelLabel.setText("<html><b>Fuel: </b>" + pressure + "</html>");
        totalHangsLabel.setText("<html><b>Hangs: </b>" + touchpad + "</html>");
        totalMatchLabel.setText("<html><b>Match: </b>" + matchPoints + "</html>");
        totalRotorsLabel.setText("<html><b>Rotors: </b>" + rotor + "</html>");

    }
    
    public void getNextMatch(){
        Event e = tba.getEvent(eventKey, year);
        Team t = tba.getTeam(teamNumber);
        
        
        
        for(Match matches : e.matches){
            String blueTeams = "";
            String redTeams = "";
            boolean nullMatch = false;
            
            if(Arrays.toString(matches.scorableItems).equals("null")){
                nullMatch = true;
            }
            
            if(nullMatch){
                int i = 0;
                for (String teamsL : matches.blueTeams){
                    String substring = teamsL.substring(3);
                    if(i <= 1){substring += ", "; i++;}
                    blueTeams += substring;
                }

                int ii = 0;
                for (String teams : matches.redTeams){
                    String substring = teams.substring(3);
                    if(ii <= 1){substring += ", "; ii++;}
                    redTeams += substring;
                }
                
                //Check if the team is in that null match
                if(Arrays.toString(matches.redTeams).contains("frc" + teamNumber)){
                    nextMatchLabel.setText("Next Match: " + matches.match_number);
                    nextMatchPartners.setText("Partners: " + redTeams);
                    nextMatchOpponents.setText("Opponents: " + blueTeams);
                    jPanel2.setBackground(Color.RED);
                    break;
                }else if(Arrays.toString(matches.blueTeams).contains("frc" + teamNumber)){
                    nextMatchLabel.setText("Next Match: " + matches.match_number);
                    nextMatchPartners.setText("Partners: " + blueTeams);
                    nextMatchOpponents.setText("Opponents: " + redTeams);
                    jPanel2.setBackground(Color.BLUE);
                    break;
                }
                               
            }else{
                nextMatchLabel.setText("No More Scheduled");
                nextMatchPartners.setText("Matches");
                nextMatchOpponents.setText("");
                jPanel2.setBackground(Color.BLACK);
            }
        }
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
        totalRPLabel = new javax.swing.JLabel();
        totalRecordLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        totalPointsLabel = new javax.swing.JLabel();
        totalMatchLabel = new javax.swing.JLabel();
        totalAutonLabel = new javax.swing.JLabel();
        totalRotorsLabel = new javax.swing.JLabel();
        totalFuelLabel = new javax.swing.JLabel();
        totalHangsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        totalPlayedLabel = new javax.swing.JLabel();
        customSponsors = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        nextMatchOpponents = new javax.swing.JLabel();
        nextMatchPartners = new javax.swing.JLabel();
        nextMatchLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(null);

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setWheelScrollingEnabled(false);
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(690, 274, 1220, 908);

        teamMottoLabel.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        teamMottoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamMottoLabel.setText("The G.R.A.Y.T. Leviathons");
        teamMottoLabel.setAlignmentX(0.5F);
        teamMottoLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().add(teamMottoLabel);
        teamMottoLabel.setBounds(0, 219, 1920, 44);

        teamNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNumberLabel.setText("Team 1322");
        teamNumberLabel.setAlignmentX(0.5F);
        teamNumberLabel.setPreferredSize(new java.awt.Dimension(1920, 91));
        teamNumberLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().add(teamNumberLabel);
        teamNumberLabel.setBounds(0, 6, 1910, 83);

        teamNameLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNameLabel.setText("The G.R.A.Y.T. Leviathons");
        teamNameLabel.setAlignmentX(0.5F);
        teamNameLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().add(teamNameLabel);
        teamNameLabel.setBounds(0, 174, 1910, 39);

        teamNicknameLabel.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        teamNicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teamNicknameLabel.setText("The G.R.A.Y.T. Leviathons");
        teamNicknameLabel.setAlignmentX(0.5F);
        teamNicknameLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().add(teamNicknameLabel);
        teamNicknameLabel.setBounds(0, 100, 1910, 73);

        currentRankLabel.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        currentRankLabel.setText("Current Rank:");
        currentRankLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().add(currentRankLabel);
        currentRankLabel.setBounds(10, 430, 707, 73);

        totalRPLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        totalRPLabel.setText("Ranking Points: 14.0 ");
        totalRPLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().add(totalRPLabel);
        totalRPLabel.setBounds(10, 490, 707, 58);

        totalRecordLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        totalRecordLabel.setText("Record (W-L-T): 14.0 ");
        totalRecordLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().add(totalRecordLabel);
        totalRecordLabel.setBounds(10, 540, 707, 58);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(51, 255, 255));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        totalPointsLabel.setFont(new java.awt.Font("Tahoma", 1, 45)); // NOI18N
        totalPointsLabel.setText("<html><u>Total Points Scored</u></html>");
        totalPointsLabel.setToolTipText("");
        totalPointsLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        totalMatchLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        totalMatchLabel.setText("Match: 14.0 ");
        totalMatchLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        totalAutonLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        totalAutonLabel.setText("Auton: 14.0 ");
        totalAutonLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        totalRotorsLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        totalRotorsLabel.setText("Rotors: 14.0 ");
        totalRotorsLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        totalFuelLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        totalFuelLabel.setText("Fuel: 120");
        totalFuelLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        totalHangsLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        totalHangsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalHangsLabel.setText("Hanging: 14.0 ");
        totalHangsLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                totalHangsLabelformKeyPressed(evt);
            }
        });

        jLabel1.setText("<html><h5>(As Alliance)</h5></html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalMatchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalRotorsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalAutonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                            .addComponent(totalFuelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(totalHangsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(totalPointsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalPointsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalMatchLabel)
                    .addComponent(totalAutonLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalRotorsLabel)
                    .addComponent(totalFuelLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalHangsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 680, 680, 223);

        totalPlayedLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        totalPlayedLabel.setText("Total Played Matches: 14.0 ");
        totalPlayedLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().add(totalPlayedLabel);
        totalPlayedLabel.setBounds(10, 590, 707, 58);

        customSponsors.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SPZProductions/FRC/pit/display/sponsors.png"))); // NOI18N
        getContentPane().add(customSponsors);
        customSponsors.setBounds(10, 770, 680, 300);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        nextMatchOpponents.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nextMatchOpponents.setForeground(new java.awt.Color(255, 255, 255));
        nextMatchOpponents.setText("Opponents: ");

        nextMatchPartners.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nextMatchPartners.setForeground(new java.awt.Color(255, 255, 255));
        nextMatchPartners.setText("Partners: ");

        nextMatchLabel.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        nextMatchLabel.setForeground(new java.awt.Color(255, 255, 255));
        nextMatchLabel.setText("Next Match:");
        nextMatchLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nextMatchLabelformKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nextMatchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addComponent(nextMatchPartners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nextMatchOpponents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(nextMatchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextMatchPartners, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(nextMatchOpponents, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 280, 680, 140);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
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

    private void totalHangsLabelformKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalHangsLabelformKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalHangsLabelformKeyPressed

    private void nextMatchLabelformKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nextMatchLabelformKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextMatchLabelformKeyPressed

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
        mainDisp = new SPZProductionsFRCPitDisplayUI();
        mainDisp.setVisible(true);
        settings = new SettingsUI();
        settings.setVisible(true);
        update = new updateLoop();
    }
    
    public static class CustomRenderer extends DefaultTableCellRenderer{

        //Stores what color you want for rows
        public HashMap<Integer, Color> colorModel = new HashMap<Integer, Color>();

        @Override
        public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //Default Rendering
            Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            super.setForeground(Color.WHITE);
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
    public javax.swing.JLabel currentRankLabel;
    public javax.swing.JLabel customSponsors;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    public javax.swing.JLabel nextMatchLabel;
    private javax.swing.JLabel nextMatchOpponents;
    private javax.swing.JLabel nextMatchPartners;
    public javax.swing.JLabel teamMottoLabel;
    public javax.swing.JLabel teamNameLabel;
    public javax.swing.JLabel teamNicknameLabel;
    public javax.swing.JLabel teamNumberLabel;
    public javax.swing.JLabel totalAutonLabel;
    public javax.swing.JLabel totalFuelLabel;
    public javax.swing.JLabel totalHangsLabel;
    public javax.swing.JLabel totalMatchLabel;
    public javax.swing.JLabel totalPlayedLabel;
    public javax.swing.JLabel totalPointsLabel;
    public javax.swing.JLabel totalRPLabel;
    public javax.swing.JLabel totalRecordLabel;
    public javax.swing.JLabel totalRotorsLabel;
    // End of variables declaration//GEN-END:variables
}
