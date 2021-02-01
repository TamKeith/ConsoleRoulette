package com.tamsanqakn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static int chosenNumber;
    private static double amount;

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
        for(int i=0; i<playerNames.length; i++){

            System.out.print("PLEASE ENTER BETS FOR: " + playerNames[i]);
            String playerName = playerNames[i];
            quit = false;

            while(!quit){

                System.out.println();

                System.out.println("Choose the type of bet: \n" +
                        " 1 : Choose Odd Number Bet \n" +
                        " 2 : Choose Even Number Bet \n" +
                        " 3 : Enter a Numeric Bet \n" +
                        " 4 : Stop taking bets for current player.");
                boolean isANumber = scanner.hasNextInt();
                if(isANumber){

                    int choice = scanner.nextInt();
                    boolean stopBets = false;
                    while(!stopBets){

                        if((choice == 1) || (choice == 2) || (choice == 3)) {
                            System.out.println("Enter the amount to bet for: " + playerNames[i]);
                            amount = scanner.nextDouble();
                        }

                        PlayerBets playerBets;
                        switch (choice) {
                            case 1:
                                playerBets = new PlayerBets(playerNames[i], amount, false, true);
                                playerBets.setBetPosition();
                                System.out.println("Odd Number bet has been placed for " + playerNames[i]);
                                player.get(playerNames[i]).add(playerBets);
                                System.out.println(playerBets.getName());
                                System.out.println(playerBets.getName() + " " + Constants.ODD + " " + playerBets.getBetAmount());
                                stopBets = true;
                                break;
                            case 2:
                                playerBets = new PlayerBets(playerNames[i], amount, true, true);
                                playerBets.setBetPosition();
                                System.out.println("Even number bet has been placed for " + playerNames[i]);
                                player.get(playerNames[i]).add(playerBets);
                                System.out.println(playerBets.getName());
                                System.out.println(playerBets.getName() + " " + Constants.EVEN + " " + playerBets.getBetAmount());
                                stopBets = true;
                                break;
                            case 3:
                                System.out.println("Enter a number between 1 - 36 to bet on:");
                                int betPosition = scanner.nextInt();
                                playerBets = new PlayerBets(playerNames[i], amount, false, false);
                                playerBets.setBetPosition(betPosition);
                                player.get(playerNames[i]).add(playerBets);
                                System.out.println(playerBets.getName());
                                System.out.println(playerBets.getName() + " " + playerBets.getBetPosition() + " " + playerBets.getBetAmount());
                                stopBets = true;
                                break;
                            case 4:
                                System.out.println("Exiting bets for player " + playerNames[i]);
                                System.out.println();
                                stopBets = true;
                                quit = true;
                                break;
                            default:
                                System.out.println("Please enter a choice from 1 to 4:");
                                break;
                        }
                    }
                } else {
                    System.out.println("Please enter a numeric choice between 1, 2 and 3");
                }
                scanner.nextLine();
            }
        }

        int delay = 0;
        int period = 30 * 1000;
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // chooser number between 1 - 36 for the ball to land on
                chosenNumber = ballChosenNumber();
            }
        }, delay, period);

        System.out.println("Player \t Bet \t Outcome \t Winnings");
        System.out.println("---");
        for(int i = 0; i < playerNames.length; i++){
            for(int j = 0; j < player.get(playerNames[i]).size(); j++){

                double amountBet = player.get(playerNames[i]).get(j).getBetAmount();
                int betPosition = player.get(playerNames[i]).get(j).getBetPosition();
                boolean isBetEven = player.get(playerNames[i]).get(j).isEven();
                boolean isPlacedEvenOrOddBet = player.get(playerNames[i]).get(j).isPlacedEvenOrOddBet();
                double winnings;

                if((!isBetEven) && (!isPlacedEvenOrOddBet)){
                    if((chosenNumber == betPosition)) {
                        winnings = 36 * amountBet;
                        System.out.println(playerNames[i]);
                        System.out.println(playerNames[i] + "\t" + player.get(playerNames[i]).get(j).getBetAmount() + "\t" + Constants.WIN + "\t" + winnings);
                    } else {
                        System.out.println(playerNames[i]);
                        System.out.println(playerNames[i] + "\t" + player.get(playerNames[i]).get(j).getBetAmount() + "\t" + Constants.LOSE + "\t" + 0.0);
                    }
                } else if ((isBetEven) && (isPlacedEvenOrOddBet)){
                    if((chosenNumber % 2 == 0)){
                        winnings = 2 * amountBet;
                        System.out.println(playerNames[i]);
                        System.out.println(playerNames[i] + "\t" + Constants.EVEN + "\t" + Constants.WIN + "\t" + winnings);
                    } else {
                        System.out.println(playerNames[i]);
                        System.out.println(playerNames[i] + "\t" + Constants.EVEN + "\t" + Constants.LOSE + "\t" + 0.0);
                    }
                } else if ((!isBetEven) && (isPlacedEvenOrOddBet)){
                    if(chosenNumber % 2 != 0) {
                        winnings = 2 * amountBet;
                        System.out.println(playerNames[i]);
                        System.out.println(playerNames[i] + "\t" + Constants.ODD + "\t" + Constants.WIN + "\t" + winnings);
                    } else {
                        System.out.println(playerNames[i]);
                        System.out.println(playerNames[i] + "\t" + Constants.ODD + "\t" + Constants.LOSE + "\t" + 0.0);
                    }
                }
            }
        }

        scanner.close();
        System.out.println("********************************************************************************");
        System.out.println("           Thank you for using the Wonderlabz Console Roulette");
        System.out.println("********************************************************************************");
    }

    private static int ballChosenNumber() {
        Random rand = new Random();
        return rand.nextInt(36);
    }

}

enum Constants{
    WIN, LOSE, EVEN, ODD;
}

