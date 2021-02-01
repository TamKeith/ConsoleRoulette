package com.tamsanqakn;

import java.util.Random;

public class PlayerBets {

    private String name;
    private double betAmount;
    private int betPosition;
    private boolean isEven;
    private boolean placedEvenOrOddBet;

    public PlayerBets(){
    }

    public PlayerBets(String name, double betAmount, boolean isEven, boolean placedEvenOrOddBet) {
        this.name = name;
        this.betAmount = betAmount;
        this.betPosition = 0;
        this.isEven = isEven;
        this.placedEvenOrOddBet = placedEvenOrOddBet;
    }

    private int generateEvenRandomNumber(){
        Random rand = new Random();
        while(true) {
            int randomNumber = rand.nextInt(36 + 1) + 1;
            if((randomNumber%2) == 0){
                return randomNumber;
            }
        }
    }

    private int generateOddRandomNumber(){
        Random rand = new Random();
        while(true){
            int randomNumber = rand.nextInt(36 + 1) + 1;
            if((randomNumber % 2) != 0){
                return randomNumber;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public int getBetPosition() {
        return betPosition;
    }

    public void setBetPosition(int betPosition) {
        this.betPosition = betPosition;
    }

    public void setBetPosition(){
        if(placedEvenOrOddBet){
            if(isEven){
                betPosition = generateEvenRandomNumber();
            } else {
                betPosition = generateOddRandomNumber();
            }
        }
    }

    public boolean isEven() {
        return isEven;
    }

    public void setEven(boolean even) {
        isEven = even;
    }

    public boolean isPlacedEvenOrOddBet() {
        return placedEvenOrOddBet;
    }

    public void setPlacedEvenOrOddBet(boolean placedEvenOrOddBet) {
        this.placedEvenOrOddBet = placedEvenOrOddBet;
    }
}
