package model;

import exceptions.InputOutOfRangeException;
import exceptions.InvalidInputException;
import exceptions.NegativeNumException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Uuup {
    private MediaDataBase movieDataBase;
    private MediaDataBase tvShowDataBase;
    private ArrayList<Person> userList;
    private CatchException newCatch;


    public Uuup() {
        movieDataBase = new MovieDatabase("MovieList1.txt");
        tvShowDataBase = new TVShowDatabase("TVShowList1.txt");
        userList = new ArrayList<>();
        newCatch = new CatchException();
    }

    public void run() {
        Person user1 = new Person();
        user1.printOutProfileInfo();
        userChooseFavMedia(user1);
        deleteOrSignOut(user1);

        Person user2 = userSignIn();
        userChooseFavMedia(user2);
        deleteOrSignOut(user2);

        movieDataBase.writeIntoDatabase(userList);

    }

    public void deleteOrSignOut(Person user) {
        int num = 0;
        printOutUserWishLists(user);
        do {
            System.out.println("Want to delete a Media or keep adding or Sign out? (1: delete Movie; 2: delete TVShow; 3: keep adding; 4: Sign out)");
            Scanner sc = new Scanner(System.in);
            num = newCatch.catchExceptions(new InputIdentifier(), sc, 4); // only have options 1, 2, 3, 4
            if(num == 1) {
                deleteFromList(user.getMovieList(), user, "M");
            }
            else if(num == 2) {
                deleteFromList(user.getTVShowList(), user, "TV");
            }
            else if(num == 3) {
                userChooseFavMedia(user);
                printOutUserWishLists(user);
            }
        }while(num != 4);

        userSignOut(user);

    }

    public void deleteFromList(MediaList aMediaList, Person user, String type) {
        if(aMediaList.getSize() != 0) {
            int deleteNum = askForDeleteNum(aMediaList.getSize());
            user.deleteFromWishList(deleteNum - 1, type);
            System.out.println("Now your list is: ");
            printOutUserWishLists(user);
        }
        else {
            String mediaType = "";
            if(type.equals("M")) {
                mediaType = "Movie";
            }
            else {
                mediaType = "TCShow";
            }
            System.out.println("There is nothing left in your" + mediaType + " List.");
        }
    }

    public int askForDeleteNum(int boundSize) {
        int deleteNum = -1;
        do {
            System.out.println("Please enter your delete number: ");
            Scanner sc = new Scanner(System.in);
            deleteNum = newCatch.catchExceptions(new InputIdentifier(), sc, boundSize);

        }while(deleteNum == -1);
        return deleteNum;
    }

    //MODIFIES: user
    //EFFECTS: ask for user's fav media number and add them into their wish list
    private void userChooseFavMedia(Person user) {
        movieDataBase.printMediaDataBase("Movie", 1);
        System.out.println();
        tvShowDataBase.printMediaDataBase("TVShow", movieDataBase.getMediaDataBaseSize() + 1);
        System.out.println("Adding your favorite Movie/TVShow by entering the number in front of it");
        Scanner sc2 = new Scanner(System.in);

        boolean validChoose = false;
        do {
            System.out.println("Please enter here: ");
            String favNumber2 = sc2.nextLine();
            try {
                user.addToWishList(favNumber2, movieDataBase, tvShowDataBase);
                validChoose = true;

            } catch(NegativeNumException e) {
                String err = e.getErrorMessege();
                System.out.println(err + " Please choose again.");

            } catch(InputOutOfRangeException e) {
                String err = e.getErrorMessege();
                System.out.println(err + " Please choose again.");

            } catch(InvalidInputException e) {
                String err = e.getErrorMessege();
                System.out.println(err + " Please choose again.");
            }
        }while(!validChoose);

    }

    //MODIFIES: this
    //EFFECTS: prints out user wishList into console and add this user into user List
    private void userSignOut(Person user) {
        printOutUserWishLists(user);
        userList.add(user);
    }

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

    //EFFECTS: prints out the user's information
    public void printOutUserWishLists(Person user) {
        MediaList movieList = user.getMovieList();
        MediaList tvshowList = user.getTVShowList();
        System.out.println("**************************************************************************************");
        if(movieList.getSize() == 0) {
            if(tvshowList.getSize() == 0) {
                System.out.println("Ops, there is nothing in your wish list. Go add some ~.");
            }
            else {
                System.out.println("Your TVShow Wish List: ");
                tvshowList.printOutList();
            }
        }
        else {
            if(tvshowList.getSize() == 0) {
                System.out.println("Your Movie Wish List: ");
                movieList.printOutList();
            }
            else {
                System.out.println("Your Movie Wish List: ");
                movieList.printOutList();
                System.out.println("Your TVShow Wish List: ");
                tvshowList.printOutList();
            }
        }
        System.out.println("**************************************************************************************");

    }

}
