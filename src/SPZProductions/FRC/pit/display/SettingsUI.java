/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPZProductions.FRC.pit.display;

import com.cpjd.main.TBA;
import com.cpjd.models.Team;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static SPZProductions.FRC.pit.display.SPZProductionsFRCPitDisplayUI.mainDisp;
import static SPZProductions.FRC.pit.display.SPZProductionsFRCPitDisplayUI.update;
import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


/**
 *
 * @author SPZ Productions
 */
public class SettingsUI extends javax.swing.JFrame {

    /**
     * Creates new form SettingsUI
     */
    public SettingsUI() {
        initComponents();
        moreInit(); 
    }
    
    public void moreInit(){

        ColorSelectionModel model = this.colorPicker.getSelectionModel();
        ChangeListener changeListener = new ChangeListener() {
          @Override
          public void stateChanged(ChangeEvent changeEvent) {
            mainDisp.changeColor(colorPicker.getColor(), labelNumber.getSelectedIndex());
          }
        };
        model.addChangeListener(changeListener);
        
        AbstractDocument document = (AbstractDocument) eventKeyText.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset,String string, AttributeSet attr)throws BadLocationException {
                super.insertString(fb, offset, string.toUpperCase(), attr);
            }
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                fb.replace(offset, length, text.toLowerCase(), attrs);
            }
        });
        
        teamSponsorText.setEnabled(false);
        teamMottoText.setEnabled(false);
        teamNameText.setEnabled(false);        
    }
    
    public void updateAll(){
        //Update Variables in the Main File
        mainDisp.teamNumber = Integer.parseInt(teamNumberSpinner.getValue().toString());
        mainDisp.year = Integer.parseInt(yearSpinner.getValue().toString());
        mainDisp.eventKey = eventKeyText.getText().toLowerCase();
        
        //Update Everything
        mainDisp.moreInit();
                
        //Check if the Labels are supposed to be visible or not
        if(!sponsorsCheck.isSelected()){
            mainDisp.teamNameLabel.setVisible(false);
            teamSponsorText.setEnabled(false);
            customTeamSponsorsCheck.setEnabled(false);
            customTeamSponsorsCheck.setSelected(false);
        }else{
            mainDisp.teamNameLabel.setVisible(true);
            customTeamSponsorsCheck.setEnabled(true);
        }
        
        if(!mottoCheck.isSelected()){
            mainDisp.teamMottoLabel.setVisible(false);
            teamMottoText.setEnabled(false);
            customTeamMottoCheck.setEnabled(false);
            customTeamMottoCheck.setSelected(false);
        }else{
            mainDisp.teamMottoLabel.setVisible(true);
            customTeamMottoCheck.setEnabled(true);
        }
        
        //Check for custom names and set them, if required
        if(customTeamNameCheck.isSelected()){  
            mainDisp.teamNicknameLabel.setText(" " + teamNameText.getText());
            teamMottoText.setEnabled(true);
        }
        
        if(customTeamMottoCheck.isSelected()){
            mainDisp.teamMottoLabel.setText(" " + teamMottoText.getText());
        }
        
        if(customTeamSponsorsCheck.isSelected()){
            mainDisp.teamNameLabel.setText(" " + teamSponsorText.getText());
            teamSponsorText.setEnabled(true);
        }
        
        teamSponsorText.setEnabled(true);
        teamMottoText.setEnabled(true);
        teamNameText.setEnabled(true);
        
        mainDisp.getTeamMatches(Integer.parseInt(teamNumberSpinner.getValue().toString()));
    }
    
    public void fullscreenToggle(){
        if(!mainDisp.isFullscreen){
            mainDisp.dispose();
            mainDisp.setExtendedState(JFrame.MAXIMIZED_BOTH);
            mainDisp.setUndecorated(true);
            mainDisp.setResizable(false);
            mainDisp.setVisible(true);
            mainDisp.isFullscreen = true;
        }else{
            mainDisp.dispose();
            mainDisp.setUndecorated(false);
            mainDisp.setResizable(true);
            mainDisp.setVisible(true);
            mainDisp.isFullscreen = false;
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

        teamNumberLabel = new javax.swing.JLabel();
        eventKeyText = new javax.swing.JTextField();
        eventKeyLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        updateAllButton = new javax.swing.JButton();
        teamNumberSpinner = new javax.swing.JSpinner();
        yearSpinner = new javax.swing.JSpinner();
        sponsorsCheck = new javax.swing.JCheckBox();
        mottoCheck = new javax.swing.JCheckBox();
        customTeamNameCheck = new javax.swing.JCheckBox();
        teamNameText = new javax.swing.JTextField();
        customTeamSponsorsCheck = new javax.swing.JCheckBox();
        teamSponsorText = new javax.swing.JTextField();
        customTeamMottoCheck = new javax.swing.JCheckBox();
        teamMottoText = new javax.swing.JTextField();
        colorPicker = new javax.swing.JColorChooser();
        labelNumber = new javax.swing.JComboBox<>();
        colorToChangeLabel = new javax.swing.JLabel();
        toggleFullscreenButton = new javax.swing.JButton();
        sponsorsImageCheck = new javax.swing.JCheckBox();
        refreshSponsorsImage = new javax.swing.JButton();
        rpCheck = new javax.swing.JCheckBox();
        statsCheck = new javax.swing.JCheckBox();
        recordCheck = new javax.swing.JCheckBox();
        playedCheck = new javax.swing.JCheckBox();
        aboutButton = new javax.swing.JButton();
        waitTimeSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Settings");
        setMaximumSize(new java.awt.Dimension(495, 455));
        setMinimumSize(new java.awt.Dimension(495, 455));
        setResizable(false);

        teamNumberLabel.setText("Team Number:");

        eventKeyText.setText(mainDisp.eventKey);
        eventKeyText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventKeyTextActionPerformed(evt);
            }
        });
        eventKeyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                eventKeyTextKeyPressed(evt);
            }
        });

        eventKeyLabel.setText("Blue Alliance Event Code: ");

        yearLabel.setText("Year: ");

        updateAllButton.setText("Update All");
        updateAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAllButtonActionPerformed(evt);
            }
        });

        teamNumberSpinner.setModel(new javax.swing.SpinnerNumberModel());
        teamNumberSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(teamNumberSpinner, "0000"));
        teamNumberSpinner.setValue(mainDisp.teamNumber);
        teamNumberSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                teamNumberSpinnerStateChanged(evt);
            }
        });
        teamNumberSpinner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teamNumberSpinnerKeyReleased(evt);
            }
        });

        yearSpinner.setModel(new javax.swing.SpinnerNumberModel());
        yearSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(yearSpinner, "0000"));
        yearSpinner.setValue(mainDisp.year);
        yearSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                yearSpinnerStateChanged(evt);
            }
        });
        yearSpinner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yearSpinnerKeyReleased(evt);
            }
        });

        sponsorsCheck.setSelected(true);
        sponsorsCheck.setText("Enable Team Sponsors List");
        sponsorsCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sponsorsCheckActionPerformed(evt);
            }
        });

        mottoCheck.setSelected(true);
        mottoCheck.setText("Enable Team Motto");
        mottoCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mottoCheckActionPerformed(evt);
            }
        });

        customTeamNameCheck.setText("Custom Team Name:");
        customTeamNameCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customTeamNameCheckActionPerformed(evt);
            }
        });

        teamNameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teamNameTextKeyReleased(evt);
            }
        });

        customTeamSponsorsCheck.setText("Custom Team Sponsors:");
        customTeamSponsorsCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customTeamSponsorsCheckActionPerformed(evt);
            }
        });

        teamSponsorText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teamSponsorTextKeyReleased(evt);
            }
        });

        customTeamMottoCheck.setText("Custom Team Motto:");
        customTeamMottoCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customTeamMottoCheckActionPerformed(evt);
            }
        });

        teamMottoText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teamMottoTextKeyReleased(evt);
            }
        });

        colorPicker.setColor(new java.awt.Color(0, 0, 0));

        labelNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Team Number", "Team Name", "Team Sponsors", "Team Motto", "Team Rank", "Match Table Text", "Match Table Header Background", "Match Table Header Text", "Display Background", "Team Statistics", "Total Points Scored Background" }));
        labelNumber.setSelectedIndex(6);
        labelNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelNumberActionPerformed(evt);
            }
        });

        colorToChangeLabel.setText("Color to Change");

        toggleFullscreenButton.setText("Toggle Fullscreen");
        toggleFullscreenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleFullscreenButtonActionPerformed(evt);
            }
        });

        sponsorsImageCheck.setSelected(true);
        sponsorsImageCheck.setText("Enable Sponsors Image");
        sponsorsImageCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sponsorsImageCheckActionPerformed(evt);
            }
        });

        refreshSponsorsImage.setText("Refresh Sponsors Image");
        refreshSponsorsImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshSponsorsImageActionPerformed(evt);
            }
        });

        rpCheck.setSelected(true);
        rpCheck.setText("Enable RP");
        rpCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rpCheckActionPerformed(evt);
            }
        });

        statsCheck.setSelected(true);
        statsCheck.setText("Enable Match Stats");
        statsCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsCheckActionPerformed(evt);
            }
        });

        recordCheck.setSelected(true);
        recordCheck.setText("Enable Record");
        recordCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordCheckActionPerformed(evt);
            }
        });

        playedCheck.setSelected(true);
        playedCheck.setText("Enable Played Matches");
        playedCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playedCheckActionPerformed(evt);
            }
        });

        aboutButton.setText("About");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });

        waitTimeSpinner.setModel(new javax.swing.SpinnerNumberModel(30, 1, 99, 1));
        waitTimeSpinner.setRequestFocusEnabled(false);
        waitTimeSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                waitTimeSpinnerStateChanged(evt);
            }
        });

        jLabel1.setText("Data Refresh Rate(Seconds)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(colorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(colorToChangeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(waitTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sponsorsCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mottoCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sponsorsImageCheck))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(customTeamNameCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(teamNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rpCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recordCheck))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(customTeamMottoCheck)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(teamMottoText))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(customTeamSponsorsCheck)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(teamSponsorText, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(playedCheck)
                                    .addComponent(statsCheck))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(teamNumberLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(teamNumberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(yearLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(eventKeyLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventKeyText))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(toggleFullscreenButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshSponsorsImage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aboutButton)
                                .addGap(66, 66, 66)
                                .addComponent(updateAllButton)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eventKeyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eventKeyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(teamNumberLabel)
                        .addComponent(teamNumberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sponsorsCheck)
                    .addComponent(mottoCheck)
                    .addComponent(sponsorsImageCheck))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customTeamNameCheck)
                    .addComponent(teamNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rpCheck)
                    .addComponent(recordCheck))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customTeamSponsorsCheck)
                    .addComponent(teamSponsorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playedCheck))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customTeamMottoCheck)
                    .addComponent(teamMottoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statsCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(waitTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(colorToChangeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleFullscreenButton)
                    .addComponent(updateAllButton)
                    .addComponent(refreshSponsorsImage)
                    .addComponent(aboutButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAllButtonActionPerformed
        updateAll();
    }//GEN-LAST:event_updateAllButtonActionPerformed

    private void sponsorsCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sponsorsCheckActionPerformed
        if(!sponsorsCheck.isSelected()){
            mainDisp.teamNameLabel.setVisible(false);
            teamSponsorText.setEnabled(false);
            customTeamSponsorsCheck.setEnabled(false);
        }else{
            mainDisp.teamNameLabel.setVisible(true);
            if(customTeamSponsorsCheck.isSelected()){
                teamSponsorText.setEnabled(true);
            }
            customTeamSponsorsCheck.setEnabled(true);
        }
    }//GEN-LAST:event_sponsorsCheckActionPerformed

    private void mottoCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mottoCheckActionPerformed
        if(!mottoCheck.isSelected()){
            mainDisp.teamMottoLabel.setVisible(false);
            teamMottoText.setEnabled(false);
            customTeamMottoCheck.setEnabled(false);
        }else{
            mainDisp.teamMottoLabel.setVisible(true);
            if(customTeamMottoCheck.isSelected()){
                teamMottoText.setEnabled(true);
            }
            customTeamMottoCheck.setEnabled(true);
        }
    }//GEN-LAST:event_mottoCheckActionPerformed

    private void customTeamNameCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTeamNameCheckActionPerformed
        int teamNumber = mainDisp.teamNumber;
        TBA tba = mainDisp.tba;
        Team t = mainDisp.t;
        if(customTeamNameCheck.isSelected()){
            teamNameText.setEnabled(true);
            mainDisp.teamNicknameLabel.setText(" " + teamNameText.getText());
        }else{
            teamNameText.setEnabled(false);
            t = tba.getTeam(teamNumber);
            mainDisp.teamNicknameLabel.setText(t.nickname);
            mainDisp.resizeNameLabel();
        }
    }//GEN-LAST:event_customTeamNameCheckActionPerformed

    private void customTeamMottoCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTeamMottoCheckActionPerformed
        int teamNumber = mainDisp.teamNumber;
        TBA tba = mainDisp.tba;
        Team t = mainDisp.t;
        if(customTeamMottoCheck.isSelected()){
            teamMottoText.setEnabled(true);
            mainDisp.teamMottoLabel.setText(" " + teamMottoText.getText());
        }else{
            teamMottoText.setEnabled(false);
            t = tba.getTeam(teamNumber);
            mainDisp.teamMottoLabel.setText(t.motto);
            mainDisp.resizeNameLabel();
        }
    }//GEN-LAST:event_customTeamMottoCheckActionPerformed

    private void customTeamSponsorsCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTeamSponsorsCheckActionPerformed
        int teamNumber = mainDisp.teamNumber;
        TBA tba = mainDisp.tba;
        Team t = mainDisp.t;
        if(customTeamSponsorsCheck.isSelected()){
            teamSponsorText.setEnabled(true);
            mainDisp.teamNameLabel.setText(" " + teamSponsorText.getText());
        }else{
            teamSponsorText.setEnabled(false);
            t = tba.getTeam(teamNumber);
            mainDisp.teamNameLabel.setText(t.name);
            mainDisp.resizeNameLabel();
        }
    }//GEN-LAST:event_customTeamSponsorsCheckActionPerformed

    private void teamMottoTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teamMottoTextKeyReleased
        if(customTeamMottoCheck.isSelected()){
            mainDisp.teamMottoLabel.setText(" " + teamMottoText.getText());
        }
    }//GEN-LAST:event_teamMottoTextKeyReleased

    private void teamSponsorTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teamSponsorTextKeyReleased
        if(customTeamSponsorsCheck.isSelected()){
            mainDisp.teamNameLabel.setText(" " + teamSponsorText.getText());
        }
    }//GEN-LAST:event_teamSponsorTextKeyReleased

    private void teamNameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teamNameTextKeyReleased
        if(customTeamNameCheck.isSelected()){
            mainDisp.teamNicknameLabel.setText(" " + teamNameText.getText());
        }
    }//GEN-LAST:event_teamNameTextKeyReleased

    private void eventKeyTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eventKeyTextKeyPressed
        mainDisp.eventKey = eventKeyText.getText();
    }//GEN-LAST:event_eventKeyTextKeyPressed

    private void yearSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_yearSpinnerStateChanged
        mainDisp.year = Integer.parseInt(yearSpinner.getValue().toString());
    }//GEN-LAST:event_yearSpinnerStateChanged

    private void teamNumberSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_teamNumberSpinnerStateChanged
        mainDisp.teamNumber = Integer.parseInt(teamNumberSpinner.getValue().toString());
    }//GEN-LAST:event_teamNumberSpinnerStateChanged

    private void teamNumberSpinnerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teamNumberSpinnerKeyReleased
        mainDisp.teamNumber = Integer.parseInt(teamNumberSpinner.getValue().toString());
    }//GEN-LAST:event_teamNumberSpinnerKeyReleased

    private void yearSpinnerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearSpinnerKeyReleased
        mainDisp.year = Integer.parseInt(yearSpinner.getValue().toString());
    }//GEN-LAST:event_yearSpinnerKeyReleased

    private void eventKeyTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventKeyTextActionPerformed
        mainDisp.eventKey = eventKeyText.getText();
    }//GEN-LAST:event_eventKeyTextActionPerformed

    private void toggleFullscreenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleFullscreenButtonActionPerformed
        fullscreenToggle();
    }//GEN-LAST:event_toggleFullscreenButtonActionPerformed

    private void labelNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelNumberActionPerformed
        int selected = labelNumber.getSelectedIndex();
        
        switch (selected) {
            case 0:
                colorPicker.setColor(mainDisp.teamNumberLabel.getForeground());
                break;
            case 1:
                colorPicker.setColor(mainDisp.teamNicknameLabel.getForeground());
                break;
            case 2:
                colorPicker.setColor(mainDisp.teamNameLabel.getForeground());
                break;
            case 3:
                colorPicker.setColor(mainDisp.teamMottoLabel.getForeground());
                break;
            case 4:
                colorPicker.setColor(mainDisp.currentRankLabel.getForeground());
                break;
            case 5:
                colorPicker.setColor(mainDisp.jTable1.getForeground());
                break;
            case 6:
                colorPicker.setColor(mainDisp.jTable1.getTableHeader().getBackground());
                break;
            case 7:
                colorPicker.setColor(mainDisp.jTable1.getTableHeader().getForeground());
                break;
            case 8:
                colorPicker.setColor(mainDisp.getContentPane().getBackground());
                break;
            case 9:
                colorPicker.setColor(mainDisp.totalAutoLabel.getForeground());
                break;
            case 10:
                colorPicker.setColor(mainDisp.jPanel1.getBackground());
                break;
            default:
                break;
        }
    }//GEN-LAST:event_labelNumberActionPerformed

    private void sponsorsImageCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sponsorsImageCheckActionPerformed
        if(sponsorsImageCheck.isSelected()){
            //SPZProductionsFRCPitDisplayUI.sponsorsImageArea.setVisible(true);
        }else{
            //SPZProductionsFRCPitDisplayUI.sponsorsImageArea.setVisible(false);
        }
    }//GEN-LAST:event_sponsorsImageCheckActionPerformed

    private void refreshSponsorsImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshSponsorsImageActionPerformed
        File f = null;
        try {
            f = new File(this.getClass().getProtectionDomain().
                    getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException ex) {
            Logger.getLogger(SettingsUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String path = f.getParent() + "\\sponsors.png";
        System.out.println(path);
        
        mainDisp.customSponsors.setIcon(new ImageIcon(path));
        
    }//GEN-LAST:event_refreshSponsorsImageActionPerformed

    private void rpCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rpCheckActionPerformed
        if(rpCheck.isSelected()){
            mainDisp.totalRPLabel.setVisible(true);
        }else{
            mainDisp.totalRPLabel.setVisible(false);
        }
    }//GEN-LAST:event_rpCheckActionPerformed

    private void recordCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordCheckActionPerformed
        if(recordCheck.isSelected()){
            mainDisp.totalRecordLabel.setVisible(true);
        }else{
            mainDisp.totalRecordLabel.setVisible(false);
        }
    }//GEN-LAST:event_recordCheckActionPerformed

    private void playedCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playedCheckActionPerformed
        if(playedCheck.isSelected()){
            mainDisp.totalPlayedLabel.setVisible(true);
        }else{
            mainDisp.totalPlayedLabel.setVisible(false);
        }
    }//GEN-LAST:event_playedCheckActionPerformed

    private void statsCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsCheckActionPerformed
        if(statsCheck.isSelected()){
            mainDisp.jPanel1.setVisible(true);
        }else{
            mainDisp.jPanel1.setVisible(false);
        }
    }//GEN-LAST:event_statsCheckActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        // Open About Window
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void waitTimeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_waitTimeSpinnerStateChanged
        update.waitTime = Integer.valueOf(waitTimeSpinner.getValue().toString());
    }//GEN-LAST:event_waitTimeSpinnerStateChanged

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
            java.util.logging.Logger.getLogger(SettingsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingsUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JColorChooser colorPicker;
    private javax.swing.JLabel colorToChangeLabel;
    private javax.swing.JCheckBox customTeamMottoCheck;
    private javax.swing.JCheckBox customTeamNameCheck;
    private javax.swing.JCheckBox customTeamSponsorsCheck;
    private javax.swing.JLabel eventKeyLabel;
    private javax.swing.JTextField eventKeyText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> labelNumber;
    private javax.swing.JCheckBox mottoCheck;
    private javax.swing.JCheckBox playedCheck;
    private javax.swing.JCheckBox recordCheck;
    private javax.swing.JButton refreshSponsorsImage;
    private javax.swing.JCheckBox rpCheck;
    private javax.swing.JCheckBox sponsorsCheck;
    private javax.swing.JCheckBox sponsorsImageCheck;
    private javax.swing.JCheckBox statsCheck;
    private javax.swing.JTextField teamMottoText;
    private javax.swing.JTextField teamNameText;
    private javax.swing.JLabel teamNumberLabel;
    private javax.swing.JSpinner teamNumberSpinner;
    private javax.swing.JTextField teamSponsorText;
    private javax.swing.JButton toggleFullscreenButton;
    private javax.swing.JButton updateAllButton;
    private javax.swing.JSpinner waitTimeSpinner;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JSpinner yearSpinner;
    // End of variables declaration//GEN-END:variables
}
