package pl.loginMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa Register odpowiada za stworzenie dodatkowego okna, w którym można zarejestrować nowego użytkownika,
 * tym samym dodać go do bazy danych.
 */

public class Register extends JFrame implements ActionListener {
    private JButton rbutton1;
    private JButton rbutton2;
    private JPanel helloRegisterPanel;
    private JPanel interfaceRegisterPanel;
    private JLabel helloRegisterLabel;
    private JTextField registerUsername;
    private JPasswordField registerPassword;

    private JFrame parent;

    /*
    * Konstruktor klasy Register odpowiada za stworzenie JFrame nowego okna.
    * Inicjalizuje również łańcuch metod, który wypełni okno innymi elementami,
    * tworząc tym samym w pełni funkcjonalne okno rejestrowania użytkownika - setRegisterPanels();
     */
    public Register(JFrame parent){
        this.parent = parent;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,150);
        setLayout(null);
        setResizable(false);
        setRegisterPanels();
        setVisible(true);
    }
    /*
    * Metoda setRegisterPanels() dodaje do okna panel z etykietą oraz panel z elementami umożliwiającymi użytkownikowi
    * rejestrację do systemu.
     */
    private void setRegisterPanels(){
        helloRegisterPanel = new JPanel();
        helloRegisterPanel.setBounds(0,0,400,30);
        helloRegisterPanel.setBackground(Color.blue);

        helloRegisterLabel = new JLabel();
        helloRegisterLabel.setText("REGISTER:");
        helloRegisterLabel.setBackground(Color.blue);
        helloRegisterLabel.setOpaque(true);
        helloRegisterLabel.setForeground(Color.white);

        helloRegisterPanel.add(helloRegisterLabel,JPanel.CENTER_ALIGNMENT);

        interfaceRegisterPanel = new JPanel();
        interfaceRegisterPanel.setBounds(0,30,400,120);
        interfaceRegisterPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));

        add(helloRegisterPanel);
        add(interfaceRegisterPanel);
        registerAddButtonsAndTextFields();
    }

    /*
    * Poniższa metoda dodaje do utworzonych wczęsniej paneli elementy - przyciski, etykiety oraz pole tekstowe + (1 pole JPasswordField)
    * umożliwiające bezpośrednie wpisanie oraz zatwierdzenie danych przez użytkownika.
     */
    private void registerAddButtonsAndTextFields(){
        registerUsername = new JTextField();
        registerPassword = new JPasswordField();


        rbutton1 = new JButton("Submit");
        rbutton2 = new JButton("Cancel");

        JLabel userLabel = new JLabel();
        JLabel passLabel = new JLabel();

        userLabel.setText("Username:");
        userLabel.setFont(new Font("Arial",Font.PLAIN,15));
        passLabel.setText("Password:");
        passLabel.setFont(new Font("Arial",Font.PLAIN,15));

        rbutton1.addActionListener(this);
        rbutton2.addActionListener(this);

        registerUsername.setPreferredSize(new Dimension(100,40));
        registerUsername.setText("Username:");
        registerUsername.setFont(new Font("Arial",Font.PLAIN,15));
        registerPassword.setPreferredSize(new Dimension(100,40));
        registerPassword.setText("Password:");
        registerPassword.setFont(new Font("Arial",Font.PLAIN,15));


        interfaceRegisterPanel.add(userLabel);
        interfaceRegisterPanel.add(registerUsername);
        interfaceRegisterPanel.add(passLabel);
        interfaceRegisterPanel.add(registerPassword);
        interfaceRegisterPanel.add(rbutton1);
        interfaceRegisterPanel.add(rbutton2);

    }
    /*
    * Poniższa metoda ustawia całą funkcjonalność okna na Enabled oraz przywraca wszystkie etykiety oraz pola tekstowe do stanu początkowego.
     */
    private void enableFunctionality(){
        rbutton2.setText("Cancel");
        rbutton1.setEnabled(true);
        registerUsername.setEnabled(true);
        registerPassword.setEnabled(true);
        registerPassword.setText("Password:");
        registerUsername.setText("Username:");
    }
    /*
    * Metoda setUser() wysyła informację o wpisaniu użytkownika do bazy danych do metody registerUser w Database.
    * W zależności od odpowiedzi metody registerUser, setUser() inicjalizuje dodatkowe okienka informacyjne, które bezpośrednio dają informacje
    * o wyniku próby rejestracji.
     */
    private void setUser(){
        String regUser = registerUsername.getText();
        char [] regPassword1 = registerPassword.getPassword();
        String regPassword = new String(regPassword1);
        System.out.println("Haslo: "+regPassword);
        int answer = Database.registerUser(regUser,regPassword);
        if(answer==0) {
            JOptionPane.showMessageDialog(this,"Username and password can't be empty!","",JOptionPane.WARNING_MESSAGE);
            enableFunctionality();
        }
        else if(answer==1){
            JOptionPane.showMessageDialog(this,"Username already existing!","",JOptionPane.WARNING_MESSAGE);
            enableFunctionality();
        }
        else{
            JOptionPane.showMessageDialog(this,"User successfully registered!");
            this.dispose();
            parent.setVisible(true);
        }
    }
    /*
    * Metoda wyłącza okno rejestracji i na nowo "ujawnia" okno logowania.
     */
    private void shutDown(){
        this.dispose();
        parent.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==rbutton1){
            setUser();
        }
        if(e.getSource()== rbutton2){
            shutDown();
        }
    }
}
