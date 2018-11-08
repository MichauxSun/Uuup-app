package model;

import exceptions.InputOutOfRangeException;
import exceptions.NegativeNumException;

import java.util.Scanner;

public class InputIdentifier {

    public int identifyInput(Scanner in, int boundSize) {
        int input = in.nextInt();
        if(input < 0) {
            throw new NegativeNumException(input);
        }

        if(input > boundSize || input == 0) {
            throw new InputOutOfRangeException(input);
        }
        return input;
    }

}
