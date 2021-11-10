package pl.loginMenu;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa Login odpowiada za stworzenie okna logowania oraz sprawdzania
 * czy wpisany użytkownik znajduje się w bazie danych
 */

public class Login extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton registerButton;
    private JTextField field1;
    private JPasswordField field2;
    private JPanel interfacePanel;
    private JLabel helloLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Database.setDefaultUsers();
                    new Login();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    /*
    * Konstruktor Login odpowiada za stworzenie okna JFrame wraz z wszystkimi funkcjonalnościami
    * Menu Logowania. Poszczególne etapy dodawania kolejnych elementów odbywają się w poniższych metodach
    * pomocniczych. Konstruktor tworzy obiekt JFrame.
    */
    public Login() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        this.setSize(250, 400);
        this.setLayout(null);
        this.setResizable(false);
        setPanels();
        addButtonsAndTextFields();
        this.setVisible(true);
    }

    /*
    * Metoda setPanels() odpowiada za stworzenie dwóch paneli helloPanel oraz interfacePanel.
    * Panel helloPanel odpowiada za wyświetlanie etykiety helloLabel, która zmienia się wraz z wykonywanymi operacjami.
    * Panel interfacePanel zawiera wszystkie elementy umożliwiające użytkownikowi wpisanie nazwy użytkownika, hasła,
    * "restart" - ponowna próba wpisania danych użytkownika, register - zarejestrowanie nowego użytkownika do bazy danych.
    *
     */
    private void setPanels() {
        JPanel helloPanel = new JPanel();
        helloPanel.setBounds(0, 0, 250, 100);
        helloPanel.setBackground(Color.blue);
        helloPanel.setLayout(new BorderLayout());

        helloLabel = new JLabel();
        helloLabel.setText("LOGIN TO DATABASE");
        helloLabel.setVerticalAlignment(JLabel.CENTER);
        helloLabel.setHorizontalAlignment(JLabel.CENTER);
        helloLabel.setForeground(Color.white);
        helloLabel.setBackground(Color.blue);
        helloLabel.setOpaque(true);
        helloPanel.add(helloLabel, BorderLayout.CENTER);

        this.add(helloPanel);

        interfacePanel = new JPanel();
        interfacePanel.setBounds(0, 130, 250, 270);
        interfacePanel.setBackground(Color.white);
        interfacePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        this.add(interfacePanel);
    }

    /*
    * Metoda addButtonsAndTextFields dodaje do panelu interfacePanel elementy użytkowe, dzięki którym
    * użytkownik może wprowadzić swoje dane, a program sprawdzić ich zgodność z bazą danych
     */

    private void addButtonsAndTextFields() {
        button1 = new JButton("Submit");
        button2 = new JButton("Reset");
        registerButton = new JButton("Register");

        field1 = new JTextField();
        field1.setPreferredSize(new Dimension(100, 40));
        field1.setText("Username:");
        field1.setFont(new Font("Arial", Font.PLAIN, 15));

        field2 = new JPasswordField();
        field2.setPreferredSize(new Dimension(100, 40));
        field2.setText("Password:");
        field2.setFont(new Font("Arial", Font.PLAIN, 15));

        button1.addActionListener(this);
        button2.addActionListener(this);
        registerButton.addActionListener(this);

        JLabel userLabel = new JLabel();
        userLabel.setText("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        interfacePanel.add(userLabel);
        interfacePanel.add(field1);
        interfacePanel.add(passwordLabel);
        interfacePanel.add(field2);
        interfacePanel.add(button1);
        interfacePanel.add(button2);
        interfacePanel.add(registerButton);
    }

    /*
    * Metoda checkUser() sprawdza czy wpisane dane użytkownika zgadzają się z bazą danych - wykorzystuje do tego statyczną metodę z klasy Database.
    * Ponadto zmienia etykietę helloLabel adekwatnie do aktualnego stanu programu
     */
    void checkUser() {
        String user = field1.getText();
        String password = new String(field2.getPassword());
        disableEnableFunc(true);
        button2.setEnabled(true);
        registerButton.setEnabled(true);
        if (Database.checkUser(user, password)) {
            this.getContentPane().setBackground(Color.green);
            interfacePanel.setBackground(Color.green);
            helloLabel.setText("USER LOGGED IN!");

        } else {
            this.getContentPane().setBackground(Color.red);
            interfacePanel.setBackground(Color.red);
            helloLabel.setText("WRONG USERNAME OR PASSWORD!");
        }
    }
    /*
    * Metoda disableEnableFun() w zależności od podanego parametru wyłącza/włącza wszystkie elementy użytkowe okna.
     */
    void disableEnableFunc(boolean check) {//true - disable, false - enable
        if (check) {
            field1.setEnabled(false);
            field2.setEnabled(false);
            button1.setEnabled(false);
            button2.setEnabled(false);
            registerButton.setEnabled(false);
        } else {
            field1.setEnabled(true);
            field2.setEnabled(true);
            button1.setEnabled(true);
            button2.setEnabled(true);
            registerButton.setEnabled(true);
        }
    }
    /*
    * Metoda hardReset() ustawią wszystkie użytkowe elementy okna na tryb "Enabled", oraz resetuje pola tekstowe, etykietę helloLabel
    * do stanu początkowego.
     */
    void hardReset() {
        this.getContentPane().setBackground(Color.white);
        interfacePanel.setBackground(Color.white);
        field1.setText("Username:");
        field2.setText("Password:");
        helloLabel.setText("LOGIN TO DATABASE");
        disableEnableFunc(false);
    }
    /*
    * Metoda newWindow() ukrywa okno logowania, oraz inicjalizuje dodatkowe okno, w którym można zarejestrować nowego użytkownika do bazy danych
    *  - Database.
     */
    void newWindow() {
        setVisible(false);
        new Register(this);
        hardReset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            checkUser();
        }
        if (e.getSource() == button2) {
            hardReset();
        }
        if (e.getSource() == registerButton) {
            newWindow();
        }
    }
}