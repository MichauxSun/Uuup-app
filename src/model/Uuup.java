package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Uuup {
    private MediaDataBase movieDataBase;
    private MediaDataBase tvShowDataBase;
    private ArrayList<Person> userList;

    public Uuup() {
        movieDataBase = new MovieDatabase("MovieList1.txt");
        tvShowDataBase = new TVShowDatabase("TVShowList1.txt");
        userList = new ArrayList<>();
    }

    public void run() {
        Person user1 = new Person();
        user1.printOutProfileInfo();
        userChooseFavMedia(user1);
        userSignOut(user1);

        Person user2 = userSignIn();
        userChooseFavMedia(user2);
        userSignOut(user2);

        movieDataBase.writeIntoDatabase(userList);

    }

    //MODIFIES: user
    //EFFECTS: ask for user's fav media number and add them into their wish list
    private void userChooseFavMedia(Person user) {
        movieDataBase.printMediaDataBase("Movie", 1);
        System.out.println();
        tvShowDataBase.printMediaDataBase("TVShow", movieDataBase.getMediaDataBaseSize() + 1);
        System.out.println("Adding your favorite Movie/TVShow by entering the number in front of it");
        //p2: add TVShow and Movie to each wishList respectively
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Please enter here: ");
        String favNumber2 = sc2.nextLine();
        user.addToWishList(favNumber2, movieDataBase, tvShowDataBase);
    }

    //MODIFIES: this
    //EFFECTS: prints out user wishList into console and add this user into user List
    private void userSignOut(Person user) {
        user.printOutWishList();
        userList.add(user);
    }

    //REQUIRES: user inputs cannot be null
    //EFFECTS: creates a user with personalised user name and password and prints out the user info into the console
    public Person userSignIn(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please create your account name: ");
        String name = sc.nextLine();
        System.out.println("Please enter your email address: ");
        String eAddress = sc.nextLine();

        int gender;
        do {
            System.out.println("Please choose your gender(1 for F; 2 for M; 3 for Trans; 4 for rather not tell): ");
            gender = Integer.parseInt(sc.nextLine());
        } while(gender < 1 || gender > 4);

        Person user = new Person(name, eAddress, gender);

        String pw1, pw2;
        do {
            System.out.println("Please set your password: ");
            pw1 = sc.nextLine();
            System.out.println("Please confirm your password(you have to reset if two pw don't match): ");
            pw2 = sc.nextLine();

        }while(!pw1.equals(pw2));
        user.setPassword(pw2);

        System.out.println("User: ");
        user.printOutProfileInfo();

        return user;
    }

}
