package model;

import exceptions.InputOutOfRangeException;
import exceptions.InvalidInputException;
import exceptions.NegativeNumException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class MediaDataBase implements Loadable, Writable {
    protected ArrayList<Media> dataBase;

    //MODIFIES: this
    //EFFECTS: adding movies and TV shows to the corresponding data base

    public MediaDataBase(String mediaList) {
        this.dataBase = new ArrayList<>();
        loadingMedia(mediaList);
    }

    //MODIFIES: this
    //EFFECTS: adding movies to the movie data base
    @Override
    public void loadingMedia(String mediaFile) {
        try {
            FileReader fr = new FileReader(mediaFile);
            BufferedReader br = new BufferedReader(fr);
            String aLine;
            while((aLine = br.readLine()) != null) {
                Scanner sc = new Scanner(aLine);
                sc.next();
                String mediaName = "";
                String s = sc.next();
                while(!s.equals(";") && s != null) {
                    mediaName += " " + s;
                    s = sc.next();
                }
                String onScreenDate = sc.nextLine();
                Media aMedia = createMedia(mediaName, onScreenDate);
                dataBase.add(aMedia);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public abstract Media createMedia(String mediaName, String onScreenDate);

    //REQUIRES: users list cannot be null
    //EFFECT: write every user's wishList into a txt file
    @Override
    public void writeIntoDatabase(ArrayList<Person> users) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("outputfile.txt","UTF-8");
            for(Person person: users) {
                if(person.getMovieList().getSize() == 0) {
                    if(person.getTVShowList().getSize() == 0) {
                        writer.println(person.getName() + ": Ops, there is nothing in your wish list. Go add some ~.");
                    }
                    else {
                        writer.print(person.getName() + ": ");
                        person.getTVShowList().printOutList(writer);
                    }
                }
                else {
                    if(person.getTVShowList().getSize() == 0) {
                        writer.print(person.getName() + ": ");
                        person.getMovieList().printOutList(writer);
                    }
                    else {
                        writer.print(person.getName() + ": ");
                        person.getMovieList().printOutList(writer);
                        person.getTVShowList().printOutList(writer);
                    }
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( writer != null ) {
                writer.close();
            }
        }
    }

    //REQUIRES: the number that passed in should be within the rang [1, movieList.size()]
    //EFFECTS: returns a Movie object to the method call
    public Media getAMedia(int num) {
        InputIdentifier identifier = new InputIdentifier();
        Scanner sc = new Scanner(num+"");
        identifier.identifyInput(sc, dataBase.size());
        int index = num - 1;
        return dataBase.get(index);
    }

    //this method is for test checking purpose
    public boolean containMedia(Media m) {
        for(Media aMedia: dataBase) {
            if(aMedia.getMediaName().equals(m.getMediaName())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the size of the mediaDataBase as an integer to the method call
    public int getMediaDataBaseSize() {
        return dataBase.size();
    }

    public ArrayList<Media> getDataBase() {
        return this.dataBase;
    }

    //EFFECTS: prints out all media in the mediaDataBase
    public void printMediaDataBase(String media, int startNum) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(media + " list: ");
        int i = startNum;
        for(Media aMedia: dataBase) {
            System.out.println(i + ". " + aMedia.getMediaName() + " On Screen Date: " + aMedia.getOnScreenDate());
            i++;
        }
    }
}
