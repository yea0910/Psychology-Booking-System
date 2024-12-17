/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventory.management.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class LecturerConsultationSlots extends javax.swing.JFrame {
    private String lecturerID;
    /**
     * Creates new form LecturerConsultationSlots
     */
    public LecturerConsultationSlots(String lecturerID) {
        this.lecturerID = lecturerID;
        initComponents();
        setLocationRelativeTo(null);
        insertData();
        timeBoxValue();
        durationBoxValue();
        locationBoxValue();
    }

    
    // FROM HERE
    private void insertData() {
    try (BufferedReader br = new BufferedReader(new FileReader("consultations.txt"))) {
        // Access the DefaultTableModel of the JTable
        DefaultTableModel model = (DefaultTableModel) consultationSlots.getModel();
        
        // Clear existing rows in the table
        model.setRowCount(0);
        
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        // for sorting date time
        TreeMap<Date, String> sortedConsultations = new TreeMap<>();

        // Skip the header line in the file
        String line = br.readLine(); // Assumes the first line is the header
        
        // Read the remaining lines of the file
        while ((line = br.readLine()) != null) {
            // Split the line into fields using comma as a delimiter
            String[] rowData = line.split(",");

            // Ensure the row has the correct number of columns (10 in your case)
            if (rowData.length != 10) {
                System.err.println("Invalid row: " + line);
                continue; // Skip invalid rows
            }

            // Extract fields from the row
            String consultationID = rowData[0].trim();
            String fileLecturerID = rowData[1].trim();
            String studentID = rowData[2].trim();
            String date = rowData[3].trim();
            String time = rowData[4].trim();
            String duration = rowData[5].trim();
            //String studentFeedback = rowData[6].trim();
            //String lecturerFeedback = rowData[7].trim();
            String status = rowData[8].trim();
            String location = rowData[9].trim();
            String dateTime = date + " " + time;

            try {
                Date consultationDate = dateTimeFormat.parse(dateTime);
                Date currentDate = new Date(); // Current system date

                // Skip past consultations & booked & Pending
                if (consultationDate.before(currentDate) || status.equalsIgnoreCase("Booked") || status.equalsIgnoreCase("Pending")) {
                    continue; // Skip past dates
                }
                
                // Skip rows that do not belong to the current lecturer
                if (!fileLecturerID.equalsIgnoreCase(this.lecturerID)) {
                    continue;
                }
                
                sortedConsultations.put(consultationDate, line);
            
                // above line for sort by date time, below for sort by consultation ID
               
                // Add the row to the table
//                model.addRow(new Object[] {
//                    consultationID, 
//                    //fileLecturerID, 
//                    //studentID.isEmpty() ? "" : studentID,
//                    date, time, duration, 
//                    status.isEmpty() ? "Available" : status,
//                    location
//                });
                
            } catch (ParseException e) {
                System.err.println("Invalid date-time format: " + e.getMessage());
            }
        }
        
        // line below for sorted by date time
        for (String sortedLine : sortedConsultations.values()) {
            String[] rowData = sortedLine.split(",");
            model.addRow(new Object[] {
                rowData[0], 
                //rowData[1], 
                //rowData[2].isEmpty() ? "" : rowData[2],
                rowData[3], rowData[4], rowData[5], 
                rowData[8].isEmpty() ? "Available" : rowData[8],
                rowData[9]
            });
        } // until here
            
        } catch (IOException e) {
            // Show error message if there's an issue with the file
            JOptionPane.showMessageDialog(this, "Error reading consultation slots: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    
    // UNTIL HERE **************
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        SlotsTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consultationSlots = new javax.swing.JTable();
        LDate = new javax.swing.JLabel();
        LTime = new javax.swing.JLabel();
        LDuration = new javax.swing.JLabel();
        LLocation = new javax.swing.JLabel();
        setConsultation = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        durationBox = new javax.swing.JComboBox<>();
        timeBox = new javax.swing.JComboBox<>();
        locationBox = new javax.swing.JComboBox<>();
        closeButton = new javax.swing.JButton();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 36)); // NOI18N
        jLabel1.setText("Consultation Slots");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));
        setForeground(new java.awt.Color(204, 204, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SlotsTitle.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 36)); // NOI18N
        SlotsTitle.setText("Consultation Slots");
        SlotsTitle.setPreferredSize(new java.awt.Dimension(333, 41));
        getContentPane().add(SlotsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 15, -1, -1));

        consultationSlots.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Consultation ID", "Date", "Time", "Duration", "Status", "Location"
            }
        ));
        consultationSlots.setFocusable(false);
        consultationSlots.setRowHeight(25);
        consultationSlots.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane1.setViewportView(consultationSlots);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 62, 705, 491));

        LDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LDate.setText("Date:");
        getContentPane().add(LDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 85, -1, -1));

        LTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LTime.setText("Time: ");
        getContentPane().add(LTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 131, -1, -1));

        LDuration.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LDuration.setText("Duration:");
        getContentPane().add(LDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 179, -1, -1));

        LLocation.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LLocation.setText("Location:");
        getContentPane().add(LLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 227, -1, -1));

        setConsultation.setBackground(new java.awt.Color(0, 51, 51));
        setConsultation.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        setConsultation.setForeground(new java.awt.Color(255, 255, 255));
        setConsultation.setText("Set");
        setConsultation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setConsultationActionPerformed(evt);
            }
        });
        getContentPane().add(setConsultation, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 340, -1, -1));

        clearButton.setBackground(new java.awt.Color(0, 51, 51));
        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, -1, -1));

        deleteButton.setBackground(new java.awt.Color(0, 51, 51));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete Row");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, -1, -1));

        durationBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        durationBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durationBoxActionPerformed(evt);
            }
        });
        getContentPane().add(durationBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(818, 179, 169, -1));

        timeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(timeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(818, 131, 169, -1));

        locationBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(locationBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(817, 227, 169, -1));

        closeButton.setBackground(new java.awt.Color(255, 153, 153));
        closeButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 440, -1, -1));

        jDateChooser.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(jDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(818, 85, 169, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/management/system/Consultation.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // update consultationID
    private String newConsultationID(String filePath) {
    String lastConsultationID = "C000"; // Default if file is empty
    try (BufferedReader br = new BufferedReader(new FileReader("consultations.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] rowData = line.split(",");
            if (rowData.length > 0) {
                lastConsultationID = rowData[0]; // store the last ID
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
    
    // return the new ID
    try {
        int idNumber = Integer.parseInt(lastConsultationID.substring(1));
        return String.format("C%03d", idNumber + 1);
    } catch (NumberFormatException e) {
        System.err.println("Invalid consultation ID format: " + lastConsultationID);
        return "C001";
    }
}

    // get all data and insert to table & txt file   
    private void setConsultationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setConsultationActionPerformed
        // TODO add your handling code here:
        
        if (jDateChooser.getDate() == null || 
        timeBox.getSelectedIndex() == 0 || 
        durationBox.getSelectedIndex() == 0 || 
        locationBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out!", "Input Error", JOptionPane.ERROR_MESSAGE);
            consultationSlots.clearSelection();
            return;
        }
        consultationSlots.clearSelection();
//        String date = tfDate.getText().trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(jDateChooser.getDate());
        String time = (String) timeBox.getSelectedItem();
        String duration = (String) durationBox.getSelectedItem();
        String location = (String) locationBox.getSelectedItem();
        
        if (date.isEmpty() || time.isEmpty() || duration.isEmpty() || location.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled out!", "Input Error", JOptionPane.ERROR_MESSAGE);
        consultationSlots.clearSelection();
        return;
    }

        
        // Validate input fields
//        if (date.isEmpty() || timeBox.getSelectedIndex() == 0 || durationBox.getSelectedIndex() == 0 || locationBox.getSelectedIndex() == 0) {
//            JOptionPane.showMessageDialog(this, "All fields must be filled out!", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        
        // Check if the selected date is in the past
        LocalDate selectedDate;
        try {
            selectedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (selectedDate.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "The selected date is in the past. You cannot create a consultation slot for past dates.", 
                                              "Invalid Date", JOptionPane.ERROR_MESSAGE);
//                tfDate.setText("");
                jDateChooser.setDate(null);
                timeBox.setSelectedIndex(0);
                durationBox.setSelectedIndex(0);
                locationBox.setSelectedIndex(0);
                return;
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please enter a valid date in 'yyyy-MM-dd' format.", 
                                          "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
         // Check for duplicate consultation slot in the file
        try (BufferedReader reader = new BufferedReader(new FileReader("consultations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                if (rowData.length == 10 && rowData[3].equals(date) && rowData[4].equals(time)) {
                    JOptionPane.showMessageDialog(this, "A consultation slot already exists for this date and time.", 
                                                  "Duplicate Slot", JOptionPane.ERROR_MESSAGE);
                    // tfDate.setText("");
                    jDateChooser.setDate(null);
                    timeBox.setSelectedIndex(0);
                    durationBox.setSelectedIndex(0);
                    locationBox.setSelectedIndex(0);
                    return;  // Stop the process
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate a new Consultation ID (e.g., based on timestamp or row count)
        String consultationID = newConsultationID("consultations.txt");
        String status = "Available";
        String lecturerID = this.lecturerID;
        String studentID = ""; // empty
        String lecturerFeedback = ""; // empty
        String studentFeedback = ""; // empty

        // Update the JTable
        DefaultTableModel model = (DefaultTableModel) consultationSlots.getModel();
        model.addRow(new Object[]{consultationID, date, time, duration, status, location});

        // Append the new row to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("consultations.txt", true))) {
            writer.write(consultationID + "," + lecturerID + "," + studentID + "," + date + "," + time + "," + duration + 
                        "," + studentFeedback + "," + lecturerFeedback + "," + status + "," + location);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Consultation slot added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear the input fields after successful addition
        //tfDate.setText("");
        jDateChooser.setDate(null);
        timeBox.setSelectedIndex(0);
        durationBox.setSelectedIndex(0);
        locationBox.setSelectedIndex(0);
    }//GEN-LAST:event_setConsultationActionPerformed

    
    // timeBox
    private void timeBoxValue() {
        String[] timeSlot = {"Select Time Slot", "10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00"};
        timeBox.removeAllItems(); // remove all
        for (String time : timeSlot) {
            timeBox.addItem(time); // Add each time slot to the combo box
        }
    }
    // give durationbox value
    private void durationBoxValue() {
        String[] durationSlot = {"Select Duration", "15","30","45","60"};
        durationBox.removeAllItems(); // remove all
        for (String duration : durationSlot) {
            durationBox.addItem(duration); // Add each time slot to the combo box
        }
    }
    
    // give durationbox value
    private void locationBoxValue() {
        String[] locationRoom = {"Select Location", "Room 101","Room 102","Room 103","Room 104","Room 105","Room 106"};
        locationBox.removeAllItems(); // remove all
        for (String location : locationRoom) {
            locationBox.addItem(location); // Add each time slot to the combo box
        }
    }
    
    private void durationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationBoxActionPerformed
        // TODO add your handling code here:
        //String selectedTime = (String) timeBox.getSelectedItem();
    }//GEN-LAST:event_durationBoxActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        //tfDate.setText("");
        consultationSlots.clearSelection();
        jDateChooser.setDate(null);
        timeBox.setSelectedIndex(0);
        durationBox.setSelectedIndex(0);
        locationBox.setSelectedIndex(0);
    }//GEN-LAST:event_clearButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = consultationSlots.getSelectedRow();

        if (selectedRow == -1) {
            // if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        return;
        }

        // confirm note
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this row?", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Get the table model and remove the selected row
//            DefaultTableModel model = (DefaultTableModel) consultationSlots.getModel();
//            model.removeRow(selectedRow);
             try {
            // Get the table model and remove the selected row
            DefaultTableModel model = (DefaultTableModel) consultationSlots.getModel();
            String consultationID = model.getValueAt(selectedRow, 0).toString(); // Assuming the first column is the Consultation ID
            
            model.removeRow(selectedRow); // Remove the row from JTable

            // File update logic using try-catch
            File inputFile = new File("consultations.txt");
            File tempFile = new File("consultations_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip the row with matching Consultation ID
                if (!line.startsWith(consultationID + ",")) { 
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            // Replace old file with updated one
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(this, "Row deleted successfully.", "Deletion Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error updating the file.", "File Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error processing the file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        consultationSlots.clearSelection();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    //UNTIL HERE
    
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
            java.util.logging.Logger.getLogger(LecturerConsultationSlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerConsultationSlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerConsultationSlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerConsultationSlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LecturerConsultationSlots("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LDate;
    private javax.swing.JLabel LDuration;
    private javax.swing.JLabel LLocation;
    private javax.swing.JLabel LTime;
    private javax.swing.JLabel SlotsTitle;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JTable consultationSlots;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> durationBox;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> locationBox;
    private javax.swing.JButton setConsultation;
    private javax.swing.JComboBox<String> timeBox;
    // End of variables declaration//GEN-END:variables
}
