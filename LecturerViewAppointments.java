/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventory.management.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class LecturerViewAppointments extends javax.swing.JFrame {
    private String lecturerID;
    /**
     * Creates new form LecturerViewAppointments
     */
    public LecturerViewAppointments(String lecturerID) {
        this.lecturerID = lecturerID;
        initComponents();
        setLocationRelativeTo(null);
        insertAppointmentData();
    }
    
private void insertAppointmentData() {
    try (BufferedReader br = new BufferedReader(new FileReader("consultations.txt"))) {
        DefaultTableModel model = (DefaultTableModel) viewAppointmentTable.getModel();
        model.setRowCount(0); // Clear the table
        
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        TreeMap<Date, String> sortedConsultations = new TreeMap<>(); // For sorting

        StringBuilder updatedFileContent = new StringBuilder(); // To store updated file content
        String header = br.readLine(); // Read the header line
        updatedFileContent.append(header).append("\n"); // Add header to the updated content

        String line;
        while ((line = br.readLine()) != null) {
            String[] rowData = line.split(",");

            // Validate the row
            if (rowData.length != 10) {
                System.err.println("Invalid row: " + line);
                continue;
            }

            // Extract fields
            String consultationID = rowData[0].trim();
            String fileLecturerID = rowData[1].trim();
            String studentID = rowData[2].trim();
            String date = rowData[3].trim();
            String time = rowData[4].trim();
            String duration = rowData[5].trim();
            String studentFeedback = rowData[6].trim();
            String lecturerFeedback = rowData[7].trim();
            String status = rowData[8].trim();
            String location = rowData[9].trim();
            String dateTime = date + " " + time;

            try {
                Date consultationDate = dateTimeFormat.parse(dateTime);
                Date currentDate = new Date();

                // Update the status if the date has passed
                if (consultationDate.before(currentDate)) {
                    if (status.equalsIgnoreCase("Available")) {
                        status = "Expired";
                    } else if (status.equalsIgnoreCase("Booked")) {
                        status = "Past";
                    }
                }

                // Skip rows not matching the lecturer
                if (!fileLecturerID.equalsIgnoreCase(this.lecturerID)) {
                    continue;
                }

                // Add updated line to the sorted map
                String updatedLine = consultationID + "," + fileLecturerID + "," + studentID + "," + date + "," +
                                     time + "," + duration + "," + studentFeedback + "," + lecturerFeedback + "," +
                                     status + "," + location;
                sortedConsultations.put(consultationDate, updatedLine);

            } catch (ParseException e) {
                System.err.println("Invalid date format: " + e.getMessage());
            }
        }

        // Write the updated content back to consultations.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("consultations.txt"))) {
            bw.write(header); // Write header first
            bw.newLine();
            for (String updatedLine : sortedConsultations.values()) {
                bw.write(updatedLine);
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }

        // Load sorted data into the table
        for (String sortedLine : sortedConsultations.values()) {
            String[] rowData = sortedLine.split(",");
            model.addRow(new Object[] {
                rowData[0], 
                //rowData[1], 
                rowData[2].isEmpty() ? "" : rowData[2],
                rowData[3], rowData[4], rowData[5],
                rowData[6].isEmpty() ? "" : rowData[6], // Student Feedback
                rowData[7].isEmpty() ? "" : rowData[7], // Lecturer Feedback
                rowData[8], // Updated Status
                rowData[9] // Location
            });
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error reading consultation slots: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
//    private void insertAppointmentData() {
//    try (BufferedReader br = new BufferedReader(new FileReader("consultations.txt"))) {
//        // Access the DefaultTableModel of the JTable
//        DefaultTableModel model = (DefaultTableModel) viewAppointmentTable.getModel();
//        
//        // Clear existing rows in the table
//        model.setRowCount(0);
//        
//        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        TreeMap<Date, String> sortedConsultations = new TreeMap<>(); // For sorting
//
//        // Skip the header line in the file
//        String line = br.readLine(); // Assumes the first line is the header
//        
//        // Read the remaining lines of the file
//        // while ((line = br.readLine()) != null) {
//        while ((line = br.readLine()) != null) {
//            // Split the line into fields using comma as a delimiter
//            String[] rowData = line.split(",");
//
//            // Ensure the row has the correct number of columns (10 in your case)
//            if (rowData.length != 10) {
//                System.err.println("Invalid row: " + line);
//                continue; // Skip invalid rows
//            }
//
//            // Extract fields from the row
//            String consultationID = rowData[0].trim();
//            String fileLecturerID = rowData[1].trim();
//            String studentID = rowData[2].trim();
//            String date = rowData[3].trim();
//            String time = rowData[4].trim();
//            String duration = rowData[5].trim();
//            String studentFeedback = rowData[6].trim();
//            String lecturerFeedback = rowData[7].trim();
//            String status = rowData[8].trim();
//            String location = rowData[9].trim();
//            String dateTime = date + " " + time;
//            
//            try {
//                Date consultationDate = dateTimeFormat.parse(dateTime);
//                Date currentDate = new Date();
//                
//                // Skip rows not matching the current lecturer
//                if (!fileLecturerID.equalsIgnoreCase(this.lecturerID)) {
//                    continue;
//                }
//                
//                // Update the status based on the consultation date
//                if (consultationDate.before(currentDate)) {
//                    if (status.equalsIgnoreCase("Available")) {
//                        status = "Expired"; // Available slots become Expired
//                    } else if (status.equalsIgnoreCase("Booked")) {
//                        status = "Past"; // Booked slots become Past
//                    }
//                }
//
//                // Add valid rows to the TreeMap for sorting
//                sortedConsultations.put(consultationDate, line);
//
//            } catch (ParseException e) {
//                System.err.println("Invalid date-time format: " + dateTime + ", " + e.getMessage());
//            }
//        }
//        // Add sorted rows to the table
//        for (String sortedLine : sortedConsultations.values()) {
//            String[] rowData = sortedLine.split(",");
//            model.addRow(new Object[] {
//                rowData[0], rowData[1], 
//                rowData[2].isEmpty() ? "" : rowData[2],
//                rowData[3], rowData[4], rowData[5],
//                rowData[6].isEmpty() ? "" : rowData[6], // Student Feedback
//                rowData[7].isEmpty() ? "" : rowData[7], // Lecturer Feedback
//                rowData[8].isEmpty() ? "Available" : rowData[8], // Status
//                rowData[9] // Location
//            });
//        }
//
//    } catch (IOException e) {
//        JOptionPane.showMessageDialog(this, "Error reading consultation slots: " + e.getMessage(),
//                "Error", JOptionPane.ERROR_MESSAGE);
//    }
//}

//                // Skip rows that do not belong to the current lecturer
//                if (!fileLecturerID.equalsIgnoreCase(this.lecturerID)) {
//                    continue;
//                }
//            
//                // Add the row to the table
//                model.addRow(new Object[] {
//                    consultationID, fileLecturerID, 
//                    studentID.isEmpty() ? "" : studentID,
//                    date, time, duration, studentFeedback, lecturerFeedback,
//                    status.isEmpty() ? "Available" : status,
//                    location
//                });
//        }
            
//        } catch (IOException e) {
//            // Show error message if there's an issue with the file
//            JOptionPane.showMessageDialog(this, "Error reading consultation slots: " + e.getMessage(),
//                    "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ViewTitle = new javax.swing.JLabel();
        approvalButton = new javax.swing.JButton();
        addFeedbackButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewAppointmentTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ViewTitle.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 36)); // NOI18N
        ViewTitle.setText("Appointments");
        getContentPane().add(ViewTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        approvalButton.setBackground(new java.awt.Color(0, 51, 51));
        approvalButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        approvalButton.setForeground(new java.awt.Color(255, 255, 255));
        approvalButton.setText("Approval");
        approvalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approvalButtonActionPerformed(evt);
            }
        });
        getContentPane().add(approvalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 520, -1, -1));

        addFeedbackButton.setBackground(new java.awt.Color(0, 51, 51));
        addFeedbackButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addFeedbackButton.setForeground(new java.awt.Color(255, 255, 255));
        addFeedbackButton.setText("Add Feedback");
        addFeedbackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFeedbackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addFeedbackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 520, -1, -1));

        closeButton.setBackground(new java.awt.Color(255, 153, 153));
        closeButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 520, -1, -1));

        viewAppointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Consultation ID", "Student ID", "Date", "Time", "Duration", "Student Feedback", "Lecturer Feedback", "Status", "Location"
            }
        ));
        viewAppointmentTable.setFocusable(false);
        viewAppointmentTable.setRowHeight(25);
        viewAppointmentTable.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane1.setViewportView(viewAppointmentTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 71, 837, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/management/system/ViewAppointments bg.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 950, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void addFeedbackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFeedbackButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = viewAppointmentTable.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an appointment to leave feedback.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) viewAppointmentTable.getModel();
    String status = (String) model.getValueAt(selectedRow, 7);  // Column for "Status"

    if (!"Past".equalsIgnoreCase(status)) {
        JOptionPane.showMessageDialog(this, "You can only leave feedback for past consultations.");
        viewAppointmentTable.clearSelection();
        return;
    }

    String consultationID = (String) model.getValueAt(selectedRow, 0);  // Column for "Consultation ID"
    String feedback = JOptionPane.showInputDialog(this, "Enter your feedback for this consultation:");

    if (feedback == null || feedback.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Feedback cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
        viewAppointmentTable.clearSelection();
        return;
    }

    try (RandomAccessFile file = new RandomAccessFile("consultations.txt", "rw")) {
        String line;
        long filePointer = 0;

        while ((line = file.readLine()) != null) {
            if (line.startsWith(consultationID + ",")) {
                // Replace Student Feedback in the correct column (index 6)
                int startOfFeedback = line.indexOf(",", line.indexOf(",", line.indexOf(",", 
                                            line.indexOf(",", line.indexOf(",", 
                                            line.indexOf(",", line.indexOf(",") + 1) + 1) + 1) + 1) + 1) + 1);
                int endOfFeedback = line.indexOf(",", startOfFeedback + 1);

                // Build updated line
                String updatedLine = line.substring(0, startOfFeedback + 1) + feedback + line.substring(endOfFeedback);

                // Overwrite the matching line
                file.seek(filePointer);  // Go back to the start of the matching line
                file.writeBytes(updatedLine + System.lineSeparator());
                break;
            }
            filePointer = file.getFilePointer();  // Save current file pointer
        }

        // Update JTable and display success message
        model.setValueAt(feedback, selectedRow, 6);  // Update JTable "Student Feedback"
        JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        viewAppointmentTable.clearSelection();

    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error saving feedback: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addFeedbackButtonActionPerformed

    private void approvalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approvalButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = viewAppointmentTable.getSelectedRow();

        if (selectedRow == -1) {
            // if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a row to approve.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            viewAppointmentTable.clearSelection();
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) viewAppointmentTable.getModel();
        String status = (String) model.getValueAt(selectedRow, 7); // Index for Status column

        if (!"Pending".equalsIgnoreCase(status)) {
            JOptionPane.showMessageDialog(this, "You can only approve or reject 'Pending' requests.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            viewAppointmentTable.clearSelection();
            return;
        }

        // Confirmation dialog for approval/rejection
        int choice = JOptionPane.showConfirmDialog(this, 
            "Do you want to approve this reschedule request? (Yes = Approve, No = Reject)", 
            "Approval Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);

        if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.NO_OPTION) {
            String consultationID = (String) model.getValueAt(selectedRow, 0); // Consultation ID

            try (RandomAccessFile file = new RandomAccessFile("consultations.txt", "rw")) {
                String line;
                long filePointer = 0;

                while ((line = file.readLine()) != null) {
                    String[] rowData = line.split(",");
                    if (rowData.length == 10 && rowData[0].equals(consultationID)) {
                        // Update status to "Available"
                        rowData[8] = "Available";
                        String updatedLine = String.join(",", rowData);

                        file.seek(filePointer); // Move to the start of the line
                        file.writeBytes(updatedLine + System.lineSeparator());
                        break;
                    }
                    filePointer = file.getFilePointer(); // Update file pointer position
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error updating file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update JTable status
            model.setValueAt("Available", selectedRow, 7);

            String message = (choice == JOptionPane.YES_OPTION) ? "Request Approved!" : "Request Rejected!";
            JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
            viewAppointmentTable.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, "Operation cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
            viewAppointmentTable.clearSelection();
        }
    
    }//GEN-LAST:event_approvalButtonActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(LecturerViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LecturerViewAppointments("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ViewTitle;
    private javax.swing.JButton addFeedbackButton;
    private javax.swing.JButton approvalButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable viewAppointmentTable;
    // End of variables declaration//GEN-END:variables
}
