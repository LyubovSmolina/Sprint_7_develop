package ru.praktikum.courier;
import org.apache.commons.lang3.RandomStringUtils;


public class Courier {
    private String login;
    private String password;
    private String firstName;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier() {

    }

    //метод, позволяющий создать курьера с индивидуальным логином, применена функция рандома
    public static Courier random() {
        return new Courier("Shustrik" + RandomStringUtils.randomAlphabetic(10), "12345","Petya");

    }
    //учетная запись курьера с пустым полем "Login"
    public static Courier withOutLogin() {
        return new Courier(null, "12345","Petya");

    }

    //учетная запись курьера с пустым полем "Password"
    public static Courier withOutPassword() {
        return new Courier("Shustrik" + RandomStringUtils.randomAlphabetic(10), null,"Petya");

    }
    //учетная запись курьера с пустым полем "firstName"
    public static Courier withOutFirstName() {
        return new Courier("Shustrik" + RandomStringUtils.randomAlphabetic(10), "12345",null);

    }


}
