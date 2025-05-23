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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aimy
 */
public class ViewAppointments extends javax.swing.JFrame {

    private String studentID;

    /**
     * Creates new form PPE_items
     */
    public ViewAppointments(String studentID) {
        initComponents();
        this.studentID = studentID;
        setLocationRelativeTo(null);
        loadAppointments();
    }

    private void loadAppointments() {
        try (BufferedReader br = new BufferedReader(new FileReader("consultations.txt"))) {
            DefaultTableModel model = (DefaultTableModel) tableAppointments.getModel();
            model.setRowCount(0); // Clear existing rows

            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 10) {
                    System.err.println("Skipping invalid row: " + line);
                    continue;
                }

                String consultationID = data[0];
                String lecturerID = data[1];
                String studentID = data[2]; // This is important to filter by studentID
                String date = data[3];
                String time = data[4];
                String duration = data[5];
                String status = data[8];
                String location = data[9];

                // Add only the student's consultations
                if (studentID.equals(this.studentID) && status.equalsIgnoreCase("Booked")) {
                    model.addRow(new Object[]{
                        consultationID, lecturerID, date, time, duration, status, location
                    });
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading appointments: " + e.getMessage());
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

        btnreschedule = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnFeedback = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAppointments = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnreschedule.setBackground(new java.awt.Color(23, 34, 77));
        btnreschedule.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnreschedule.setForeground(new java.awt.Color(255, 255, 255));
        btnreschedule.setText("Reschedule");
        btnreschedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrescheduleActionPerformed(evt);
            }
        });
        getContentPane().add(btnreschedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, -1, -1));

        btnCancel.setBackground(new java.awt.Color(23, 34, 77));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel Appointment");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, -1, -1));

        btnFeedback.setBackground(new java.awt.Color(23, 34, 77));
        btnFeedback.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFeedback.setForeground(new java.awt.Color(255, 255, 255));
        btnFeedback.setText("Leave Feedback");
        btnFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbackActionPerformed(evt);
            }
        });
        getContentPane().add(btnFeedback, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 510, -1, -1));

        btnClose.setBackground(new java.awt.Color(255, 153, 153));
        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 510, -1, -1));

        tableAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Consultation ID", "Lecturer ID", "Student ID", "Date", "Time", "Duration", "Student Feedback", "Lecturer Feedback", "Status", "Location"
            }
        ));
        tableAppointments.setFocusable(false);
        tableAppointments.setRowHeight(25);
        tableAppointments.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tableAppointments.getTableHeader().setResizingAllowed(false);
        tableAppointments.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableAppointments);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 890, -1));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 36)); // NOI18N
        jLabel1.setText("Appointments");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/management/system/ViewAppointments bg.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadUserData() {
        DefaultTableModel model = (DefaultTableModel) tableAppointments.getModel();
        model.setRowCount(0); // Clear existing data

        try (BufferedReader reader = new BufferedReader(new FileReader("consultations.txt"))) {
            String line;

            if ((line = reader.readLine()) != null) {

            }

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");

                if (userData[2].equals(studentID)) {
                    model.addRow(userData);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading user data: " + ex.getMessage());
        }
    }

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        loadUserData();
    }//GEN-LAST:event_formComponentShown

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableAppointments.getSelectedRow();

        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableAppointments.getModel();
            String consultationID = (String) model.getValueAt(selectedRow, 0); // Consultation ID
            String bookedStudentID = (String) model.getValueAt(selectedRow, 2); // Student ID
            String status = (String) model.getValueAt(selectedRow, 8); // Status

            // Check if the consultation is booked and belongs to the logged-in student
            if ("Booked".equalsIgnoreCase(status) && studentID.equals(bookedStudentID)) {
                // Show confirmation dialog
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to cancel this consultation?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try (BufferedReader reader = new BufferedReader(new FileReader("consultations.txt"))) {
                        List<String> lines = new ArrayList<>();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            String[] data = line.split(",");
                            if (data[0].equals(consultationID)) {
                                // Update the line for the canceled consultation
                                data[2] = ""; // Clear Student ID
                                data[8] = "Available"; // Update Status
                                line = String.join(",", data);
                            }
                            lines.add(line); // Add (updated or unchanged) line to the list
                        }

                        // Rewrite the file with updated lines
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("consultations.txt"))) {
                            for (String updatedLine : lines) {
                                writer.write(updatedLine);
                                writer.newLine();
                            }
                        }

                        JOptionPane.showMessageDialog(this, "Consultation slot cancelled successfully!");
                        model.setRowCount(0); // Clear table rows
                        loadUserData(); // Reload appointments after modifying the file
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error updating file: " + ex.getMessage());
                    }
                }
            } else if (!studentID.equals(bookedStudentID)) {
                JOptionPane.showMessageDialog(this, "You can only cancel your own bookings.");
            } else {
                JOptionPane.showMessageDialog(this, "This slot is not booked and cannot be cancelled.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a consultation slot to cancel.");
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnrescheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrescheduleActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableAppointments.getSelectedRow();

        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableAppointments.getModel();
            String consultationID = (String) model.getValueAt(selectedRow, 0); // Consultation ID
            String bookedStudentID = (String) model.getValueAt(selectedRow, 2); // Student ID
            String status = (String) model.getValueAt(selectedRow, 8); // Status

            // Check if the consultation is booked by the logged-in student
            if ("Booked".equalsIgnoreCase(status) && studentID.equals(bookedStudentID)) {
                // Ask for confirmation to reschedule (cancel the current booking)
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to reschedule (cancel) this consultation?",
                        "Confirm Reschedule",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Update status to "Pending Approval"
                    model.setValueAt("Pending Approval", selectedRow, 8); // Update status in the table

                    try (BufferedReader reader = new BufferedReader(new FileReader("consultations.txt"))) {
                        List<String> lines = new ArrayList<>();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            String[] data = line.split(",");
                            if (data[0].equals(consultationID)) {
                                // Update the line for the rescheduled consultation
                                data[8] = "Pending Approval"; // Set Status to "Pending Approval"
                                line = String.join(",", data); // Rebuild the line
                            }
                            lines.add(line); // Add updated or unchanged line
                        }

                        // Rewrite the file with updated lines
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("consultations.txt"))) {
                            for (String updatedLine : lines) {
                                writer.write(updatedLine);
                                writer.newLine();
                            }
                        }

                        // Inform the user that the reschedule request was successful
                        JOptionPane.showMessageDialog(this, "Reschedule request submitted successfully!");
                        model.setRowCount(0);
                        loadUserData();

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error updating file: " + ex.getMessage());
                    }
                }
            } else if (!studentID.equals(bookedStudentID)) {
                JOptionPane.showMessageDialog(this, "You can only reschedule your own bookings.");
            } else {
                JOptionPane.showMessageDialog(this, "This slot is not booked and cannot be rescheduled.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a consultation slot to reschedule.");
        }
    }//GEN-LAST:event_btnrescheduleActionPerformed

    private void btnFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbackActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableAppointments.getSelectedRow();

        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableAppointments.getModel();
            String status = (String) model.getValueAt(selectedRow, 8); // Column index for "Status"

            if ("Past".equalsIgnoreCase(status)) {
                String consultationID = (String) model.getValueAt(selectedRow, 0); // Consultation ID
                String feedback = JOptionPane.showInputDialog(this, "Enter your feedback for this consultation:");

                if (feedback != null && !feedback.trim().isEmpty()) {
                    // Save the feedback to the file
                    try (BufferedReader reader = new BufferedReader(new FileReader("consultations.txt"))) {
                        List<String> updatedLines = new ArrayList<>();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            String[] data = line.split(",");
                            if (data[0].equals(consultationID)) {
                                data[6] = feedback; // Update StudentFeedback column
                                line = String.join(",", data);
                            }
                            updatedLines.add(line);
                        }

                        // Rewrite the file with updated feedback
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("consultations.txt"))) {
                            for (String updatedLine : updatedLines) {
                                writer.write(updatedLine);
                                writer.newLine();
                            }
                        }

                        // Refresh table
                        loadUserData();
                        JOptionPane.showMessageDialog(this, "Feedback submitted successfully!");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error saving feedback: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Feedback cannot be empty.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "You can only leave feedback for past consultations.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an appointment to leave feedback.");
        }
    }//GEN-LAST:event_btnFeedbackActionPerformed

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
            java.util.logging.Logger.getLogger(ViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAppointments("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnFeedback;
    private javax.swing.JButton btnreschedule;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableAppointments;
    // End of variables declaration//GEN-END:variables
}
