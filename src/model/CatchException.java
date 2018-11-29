package model;

import exceptions.InputOutOfRangeException;
import exceptions.InvalidInputException;
import exceptions.NegativeNumException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CatchException {
    public int catchExceptions(InputIdentifier i, int number, int boundSize) {
        int num = -1 ;
        try {
            num = i.identifyInput(number, boundSize);
        }catch(InputMismatchException e) {
            System.out.println("please enter an integer");
        }catch(InputOutOfRangeException e) {
            System.out.println(e.getErrorMessege());
        }catch(NegativeNumException e) {
            System.out.println(e.getErrorMessege());
        }catch(InvalidInputException e) {
            System.out.println(e.getErrorMessege());
        }
        return num;
    }
}
