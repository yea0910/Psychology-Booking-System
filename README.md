# Psychology Consultation Management System
This is a Java-based GUI application built using Object-Oriented Programming (OOP) principles to manage psychology consultation bookings. The system supports user registration, appointment scheduling, feedback management, and user profile updates. All data is stored persistently using plain text files.
---
## Features
1) Student
   - Register & log in
   - View available consultation slots
   - Book consultation
   - View, cancel, or reschedule upcoming appointments
   - Leave feedback for past appointments
   - Edit personal profile (Name, Password)
3) Lecturer
   - Register & log in
   - Set, view, and delete available consultation slots
   - Approve or reject rescheduled requests
   - View and leave feedback on appointments
5) System
   - GUI interface using Java Swing
   - Text file storage (no database required)
   - Input validation for user data
   - Slot sorting using TreeMap (by date and time)
   - Use of JDateChooser for accurate date selection
---
## Technologies Used
- Java SE (Swing, AWT)
- Java Collections (List, ArrayList, TreeMap)
- Java File I/O (BufferedReader, BufferedWriter, FileReader, FileWriter)
- Java Time (LocalDateTime, DateTimeFormatter)
- GUI Components: JTable, JLabel, JTextField, JButton, JDateChooser, etc.
---
### Login Page
![image](https://github.com/user-attachments/assets/1194b3e1-987f-4fef-918f-cb13203a3711)

### Sign Up Page
![image](https://github.com/user-attachments/assets/eca50bbd-283e-462e-bf18-65ba038c9109)

### Homepage
![image](https://github.com/user-attachments/assets/9a8a40ae-8ef8-4b74-9a0f-fb924a02b667)

### Consultation Slots Page
Student:\
![image](https://github.com/user-attachments/assets/5b32190f-d4b7-4659-886d-729b6c0a02ad)

Lecturer:\
![image](https://github.com/user-attachments/assets/cd7457d8-6a77-4ecd-9d19-e9c8d4766a2e)

### View Appointment Page
Student:\
![image](https://github.com/user-attachments/assets/7edc9ee1-508f-4078-829c-27d13c849b7e)

Lecturer:\
![image](https://github.com/user-attachments/assets/9d62b252-d9df-48e6-bc5a-fe2b11a558c0)

### User Profile Page
![image](https://github.com/user-attachments/assets/732a04d5-a056-408d-b0e7-2932a12500b7)
---
## How to Run
1) Open in your preferred Java IDE (e.g., NetBeans, IntelliJ)
2) Add jcalendar-1.4.jar to the classpath (for date picker)
3) Run Login.java or StudentHomepage.java to start the system
4) Sample user credentials are stored in users.txt






