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
        ColorSelectionModel model = backgroundColorPicker.getSelectionModel();
        ChangeListener changeListener = new ChangeListener() {
          @Override
          public void stateChanged(ChangeEvent changeEvent) {
            mainDisp.changeBG(backgroundColorPicker.getColor());
          }
        };
        model.addChangeListener(changeListener);
        
        ColorSelectionModel model1 = this.textColorPicker.getSelectionModel();
        ChangeListener changeListener1 = new ChangeListener() {
          @Override
          public void stateChanged(ChangeEvent changeEvent) {
            mainDisp.changeTextColor(textColorPicker.getColor(), labelNumber.getSelectedIndex());
            System.out.println("Color Changed");
          }
        };
        model1.addChangeListener(changeListener1);
        
        teamSponsorText.setEnabled(false);
        teamMottoText.setEnabled(false);
        teamNameText.setEnabled(false);        
    }
    
    public void update(){
        //Update Variables in the Main File
        SPZProductionsFRCPitDisplayUI.teamNumber = Integer.parseInt(teamNumberSpinner.getValue().toString());
        SPZProductionsFRCPitDisplayUI.year = Integer.parseInt(yearSpinner.getValue().toString());
        SPZProductionsFRCPitDisplayUI.eventKey = eventKeyText.getText();
        
        //Update Everything
        mainDisp.moreInit();
                
        //Check if the Labels are supposed to be visible or not
        if(!sponsorsCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamNameLabel.setVisible(false);
            teamSponsorText.setEnabled(false);
            customTeamSponsorsCheck.setEnabled(false);
            customTeamSponsorsCheck.setSelected(false);
        }else{
            SPZProductionsFRCPitDisplayUI.teamNameLabel.setVisible(true);
            customTeamSponsorsCheck.setEnabled(true);
        }
        
        if(!mottoCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamMottoLabel.setVisible(false);
            teamMottoText.setEnabled(false);
            customTeamMottoCheck.setEnabled(false);
            customTeamMottoCheck.setSelected(false);
        }else{
            SPZProductionsFRCPitDisplayUI.teamMottoLabel.setVisible(true);
            customTeamMottoCheck.setEnabled(true);
        }
        
        //Check for custom names and set them, if required
        if(customTeamNameCheck.isSelected()){  
            SPZProductionsFRCPitDisplayUI.teamNicknameLabel.setText(" " + teamNameText.getText());
            teamMottoText.setEnabled(true);
        }
        
        if(customTeamMottoCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamMottoLabel.setText(" " + teamMottoText.getText());
        }
        
        if(customTeamSponsorsCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamNameLabel.setText(" " + teamSponsorText.getText());
            teamSponsorText.setEnabled(true);
        }
        
        teamSponsorText.setEnabled(true);
        teamMottoText.setEnabled(true);
        teamNameText.setEnabled(true);
        
        mainDisp.getTeamMatches(Integer.parseInt(teamNumberSpinner.getValue().toString()));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        eventKeyText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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
        colorTabs = new javax.swing.JTabbedPane();
        backgroundColorTab = new javax.swing.JPanel();
        backgroundColorPicker = new javax.swing.JColorChooser();
        textColorTab = new javax.swing.JPanel();
        textColorPicker = new javax.swing.JColorChooser();
        labelNumber = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText("Team Number:");

        eventKeyText.setText(SPZProductions.FRC.pit.display.SPZProductionsFRCPitDisplayUI.eventKey);
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

        jLabel2.setText("Blue Alliance Event Code: ");

        jLabel3.setText("Year: ");

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        teamNumberSpinner.setModel(new javax.swing.SpinnerNumberModel());
        teamNumberSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(teamNumberSpinner, "0000"));
        teamNumberSpinner.setValue(SPZProductions.FRC.pit.display.SPZProductionsFRCPitDisplayUI.teamNumber);
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
        yearSpinner.setValue(SPZProductions.FRC.pit.display.SPZProductionsFRCPitDisplayUI.year);
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

        javax.swing.GroupLayout backgroundColorTabLayout = new javax.swing.GroupLayout(backgroundColorTab);
        backgroundColorTab.setLayout(backgroundColorTabLayout);
        backgroundColorTabLayout.setHorizontalGroup(
            backgroundColorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundColorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 441, Short.MAX_VALUE)
        );
        backgroundColorTabLayout.setVerticalGroup(
            backgroundColorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundColorTabLayout.createSequentialGroup()
                .addComponent(backgroundColorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );

        colorTabs.addTab("Background Color", backgroundColorTab);

        textColorPicker.setColor(new java.awt.Color(0, 0, 0));

        labelNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Team Number", "Team Name", "Team Sponsors", "Team Motto", "Team Rank", "Match Table" }));

        jLabel4.setText("Text Color to Change");

        javax.swing.GroupLayout textColorTabLayout = new javax.swing.GroupLayout(textColorTab);
        textColorTab.setLayout(textColorTabLayout);
        textColorTabLayout.setHorizontalGroup(
            textColorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textColorTabLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
            .addComponent(textColorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        textColorTabLayout.setVerticalGroup(
            textColorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textColorTabLayout.createSequentialGroup()
                .addComponent(textColorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(textColorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textColorTabLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(textColorTabLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(287, 287, 287))
        );

        colorTabs.addTab("Text Color", textColorTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(colorTabs)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(customTeamMottoCheck)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(teamMottoText))
                                .addComponent(mottoCheck)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(teamNumberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(customTeamSponsorsCheck)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(teamSponsorText, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(customTeamNameCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(teamNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sponsorsCheck)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventKeyText, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(teamNumberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eventKeyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sponsorsCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mottoCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customTeamNameCheck)
                    .addComponent(teamNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customTeamSponsorsCheck)
                    .addComponent(teamSponsorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customTeamMottoCheck)
                    .addComponent(teamMottoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        update();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sponsorsCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sponsorsCheckActionPerformed
        if(!sponsorsCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamNameLabel.setVisible(false);
            teamSponsorText.setEnabled(false);
            customTeamSponsorsCheck.setEnabled(false);
        }else{
            SPZProductionsFRCPitDisplayUI.teamNameLabel.setVisible(true);
            if(customTeamSponsorsCheck.isSelected()){
                teamSponsorText.setEnabled(true);
            }
            customTeamSponsorsCheck.setEnabled(true);
        }
    }//GEN-LAST:event_sponsorsCheckActionPerformed

    private void mottoCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mottoCheckActionPerformed
        if(!mottoCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamMottoLabel.setVisible(false);
            teamMottoText.setEnabled(false);
            customTeamMottoCheck.setEnabled(false);
        }else{
            SPZProductionsFRCPitDisplayUI.teamMottoLabel.setVisible(true);
            if(customTeamMottoCheck.isSelected()){
                teamMottoText.setEnabled(true);
            }
            customTeamMottoCheck.setEnabled(true);
        }
    }//GEN-LAST:event_mottoCheckActionPerformed

    private void customTeamNameCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTeamNameCheckActionPerformed
        int teamNumber = SPZProductionsFRCPitDisplayUI.teamNumber;
        TBA tba = SPZProductionsFRCPitDisplayUI.tba;
        Team t = SPZProductionsFRCPitDisplayUI.t;
        if(customTeamNameCheck.isSelected()){
            teamNameText.setEnabled(true);
            SPZProductionsFRCPitDisplayUI.teamNicknameLabel.setText(" " + teamNameText.getText());
        }else{
            teamNameText.setEnabled(false);
            t = tba.getTeam(teamNumber);
            SPZProductionsFRCPitDisplayUI.teamNicknameLabel.setText(t.nickname);
            SPZProductionsFRCPitDisplayUI.resizeNameLabel();
        }
    }//GEN-LAST:event_customTeamNameCheckActionPerformed

    private void customTeamMottoCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTeamMottoCheckActionPerformed
        int teamNumber = SPZProductionsFRCPitDisplayUI.teamNumber;
        TBA tba = SPZProductionsFRCPitDisplayUI.tba;
        Team t = SPZProductionsFRCPitDisplayUI.t;
        if(customTeamMottoCheck.isSelected()){
            teamMottoText.setEnabled(true);
            SPZProductionsFRCPitDisplayUI.teamMottoLabel.setText(" " + teamMottoText.getText());
        }else{
            teamMottoText.setEnabled(false);
            t = tba.getTeam(teamNumber);
            SPZProductionsFRCPitDisplayUI.teamMottoLabel.setText(t.motto);
            SPZProductionsFRCPitDisplayUI.resizeNameLabel();
        }
    }//GEN-LAST:event_customTeamMottoCheckActionPerformed

    private void customTeamSponsorsCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTeamSponsorsCheckActionPerformed
        int teamNumber = SPZProductionsFRCPitDisplayUI.teamNumber;
        TBA tba = SPZProductionsFRCPitDisplayUI.tba;
        Team t = SPZProductionsFRCPitDisplayUI.t;
        if(customTeamSponsorsCheck.isSelected()){
            teamSponsorText.setEnabled(true);
            SPZProductionsFRCPitDisplayUI.teamNameLabel.setText(" " + teamSponsorText.getText());
        }else{
            teamSponsorText.setEnabled(false);
            t = tba.getTeam(teamNumber);
            SPZProductionsFRCPitDisplayUI.teamNameLabel.setText(t.name);
            SPZProductionsFRCPitDisplayUI.resizeNameLabel();
        }
    }//GEN-LAST:event_customTeamSponsorsCheckActionPerformed

    private void teamMottoTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teamMottoTextKeyReleased
        if(customTeamMottoCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamMottoLabel.setText(" " + teamMottoText.getText());
        }
    }//GEN-LAST:event_teamMottoTextKeyReleased

    private void teamSponsorTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teamSponsorTextKeyReleased
        if(customTeamSponsorsCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamNameLabel.setText(" " + teamSponsorText.getText());
        }
    }//GEN-LAST:event_teamSponsorTextKeyReleased

    private void teamNameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teamNameTextKeyReleased
        if(customTeamNameCheck.isSelected()){
            SPZProductionsFRCPitDisplayUI.teamNicknameLabel.setText(" " + teamNameText.getText());
        }
    }//GEN-LAST:event_teamNameTextKeyReleased

    private void eventKeyTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eventKeyTextKeyPressed
        SPZProductionsFRCPitDisplayUI.eventKey = eventKeyText.getText();
    }//GEN-LAST:event_eventKeyTextKeyPressed

    private void yearSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_yearSpinnerStateChanged
        SPZProductionsFRCPitDisplayUI.year = Integer.parseInt(yearSpinner.getValue().toString());
    }//GEN-LAST:event_yearSpinnerStateChanged

    private void teamNumberSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_teamNumberSpinnerStateChanged
        SPZProductionsFRCPitDisplayUI.teamNumber = Integer.parseInt(teamNumberSpinner.getValue().toString());
    }//GEN-LAST:event_teamNumberSpinnerStateChanged

    private void teamNumberSpinnerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teamNumberSpinnerKeyReleased
        SPZProductionsFRCPitDisplayUI.teamNumber = Integer.parseInt(teamNumberSpinner.getValue().toString());
    }//GEN-LAST:event_teamNumberSpinnerKeyReleased

    private void yearSpinnerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearSpinnerKeyReleased
        SPZProductionsFRCPitDisplayUI.year = Integer.parseInt(yearSpinner.getValue().toString());
    }//GEN-LAST:event_yearSpinnerKeyReleased

    private void eventKeyTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventKeyTextActionPerformed
        SPZProductionsFRCPitDisplayUI.eventKey = eventKeyText.getText();
    }//GEN-LAST:event_eventKeyTextActionPerformed

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
    private javax.swing.JColorChooser backgroundColorPicker;
    private javax.swing.JPanel backgroundColorTab;
    private javax.swing.JTabbedPane colorTabs;
    private javax.swing.JCheckBox customTeamMottoCheck;
    private javax.swing.JCheckBox customTeamNameCheck;
    private javax.swing.JCheckBox customTeamSponsorsCheck;
    private javax.swing.JTextField eventKeyText;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> labelNumber;
    private javax.swing.JCheckBox mottoCheck;
    private javax.swing.JCheckBox sponsorsCheck;
    private javax.swing.JTextField teamMottoText;
    private javax.swing.JTextField teamNameText;
    private javax.swing.JSpinner teamNumberSpinner;
    private javax.swing.JTextField teamSponsorText;
    private javax.swing.JColorChooser textColorPicker;
    private javax.swing.JPanel textColorTab;
    private javax.swing.JSpinner yearSpinner;
    // End of variables declaration//GEN-END:variables
}