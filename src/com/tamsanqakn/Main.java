package com.tamsanqakn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        /**
         *  Read all gamers from the text file into a simple string
        */
        String allGamers;
        File file = new File("gamers.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            StringBuilder sb = new StringBuilder();
            String gamers;
            while ((gamers = br.readLine()) != null) {
                sb.append(gamers);
                sb.append(System.lineSeparator());
            }
            br.close();
            allGamers = sb.toString();
            System.out.println(allGamers);

        }

        /**
         * Split the string from the text file into player names
         * */
        String[] playerNames = allGamers.split("\n");

        /**
         * create hashmap to store player details and their bets
         * */
        HashMap<String, List<PlayerBets>> player = new HashMap<>();
        for(int i=0; i<playerNames.length; i++){
            player.put(playerNames[i], new ArrayList<PlayerBets>());
        }

        boolean quit = false;
        System.out.println("********************************************************************************");
        System.out.println("             Welcome to the Wonderlabz Console Roulette \n");
        System.out.println("********************************************************************************");
        Scanner scanner = new Scanner(System.in);

    }
}
