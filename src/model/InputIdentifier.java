package model;

import exceptions.InputOutOfRangeException;
import exceptions.NegativeNumException;

import java.util.Scanner;

public class InputIdentifier {

    public int identifyInput(int num, int boundSize) {

        if(num < 0) {
            throw new NegativeNumException(num);
        }

        if(num > boundSize || num == 0) {
            throw new InputOutOfRangeException(num);
        }
        return num;
    }

}
