package pl.loginMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstrakcyjna klasa Database przetrzymuje statyczną zmienną - (Pole klasy), która służy za bazę danych do menu logowania.
 * Baza danych jest Hash Mapą, w której wartościami są nazwy użytkowników, a kluczami - hasła użytkowników znajdujących się w bazie danych.
 */
public abstract class Database {
    private static Map<String,String> database = new HashMap<String, String>();

    /*
    * Metoda setDefaultUsers() wypełnia bazę danych początkowymi użytkownikami.
     */
     static void setDefaultUsers(){
        database.put("pass1","user1");
        database.put("pass2","user2");
        database.put("pass3","user3");
        database.put("pass4","user4");
        database.put("Password:","Username:");
    }

    //checkUser() sprawdza, czy w bazie danych znajduje się element user - połączony z kluczem - pass.
    // informacja ta zostaje zwrócona, co programowi sprawdzić, czy w bazie danych znajduje się użytkownik
    // z powiązanym kluczem - hasłem
    static boolean checkUser(String user,String pass){
        return database.containsKey(pass) && database.get(pass).equals(user);
    }

    /*
    * Funkcja registerUser() jest w stanie wprowadzić nowego użytkownika do bazy danych pod warunkiem, że argumentami metody
    * nie są puste ciągi znaków, oraz że wprowadzona nazwa użytkownika nie zjaduje się jeszcze w bazie danych.
    * Adekwatnie do wyniku tych analiz, zwraca odpowiedzi:
    *  - 0 - błąd, argumenty są pustymi Stringami
    *  - 1 - błąd, nazwa użytkownika istnieje już w bazie danych
    *  - 2 - Udało się, użytkownik został wpisany do bazy danych.
     */
    static int registerUser(String username, String password){
         if(username.equals("")||password.equals(""))
             return 0;
         else if(database.containsValue(username))
             return 1;
         else {
             database.put(password, username);
             return 2;
         }
    }
}
