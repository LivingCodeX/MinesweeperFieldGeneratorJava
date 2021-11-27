package me.livingcodex.minesweepgenerator;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final int fieldWidth = 8;
        final int fieldHeight = 4;
        final int bombs = 8;

        MinesweeperField field = new MinesweeperField(fieldWidth, fieldHeight);

        Bomb[] bombLocations = generateBombs(fieldWidth, fieldHeight, bombs);
        field.generateField(bombLocations);

        String[] s = field.field2Text();
        for (String s0 : s) {
            System.out.println(s0);
        }
    }

    private static Bomb[] generateBombs(int fieldWidth, int fieldHeight, int bombs) {
        if (fieldWidth <= 0) throw new InvalidParameterException("The field width has to be greater than 0.");
        if (fieldHeight <= 0) throw new InvalidParameterException("The field width has to be greater than 0.");
        if (bombs <= 0) return new Bomb[0];

        int totalSpots = fieldWidth * fieldHeight;

        if (totalSpots < bombs)
            throw new InvalidParameterException("There field (" + totalSpots + ") is too small for " + bombs + " bombs.");

        if (totalSpots == bombs) {
            Bomb[] bombLocations = new Bomb[totalSpots];
            for (int i = 0; i < totalSpots; i++) {
                int x = i % fieldWidth;
                int y = i / fieldWidth;
                bombLocations[i] = new Bomb(x, y);
            }
            return bombLocations;
        }

        //Normal generation
        int[] bomb1DLocations = randomNumbers(totalSpots, bombs);
        //int[][] bomb2DLocations = new int[bombs][2];
        Bomb[] bombLocations = new Bomb[bombs];

        for (int i = 0; i < bombs; i++) {
            int loc1D = bomb1DLocations[i];

            int x = loc1D % fieldWidth;
            int y = loc1D / fieldWidth;

            bombLocations[i] = new Bomb(x, y);
        }

        return bombLocations;
    }

    private static int[] randomNumbers(int bound, int amount) {
        Set<Integer> numbers = new HashSet<>(amount); //A set doesn't allow duplicates, so only one bomb per spot

        Random rnd = new Random();

        while (numbers.size() < amount) {
            int num = rnd.nextInt(bound);

            numbers.add(num); //Only TRIES to add
        }

        //Some conversion magic
        Integer[] integerArray = numbers.toArray(new Integer[0]);
        int[] intArray = new int[amount];
        for (int i = 0; i < amount; i++) {
            intArray[i] = integerArray[i];
        }

        return intArray;
    }

}