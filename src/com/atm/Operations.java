package com.atm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Operations {
    DecimalFormat money = new DecimalFormat("'MWK'###,###,##0.00");
    Scanner scan = new Scanner(System.in);
    double accBal = 0, amount = 0;
    int option = 0;
    String operation, location;

    ArrayList<String> statements = new ArrayList<>();

    void menu() throws InterruptedException {
        System.out.println("\n\n\nSelect a service:" +
                "\n" +
                "1. Check Balance\n" +
                "2. Withdraw Money\n" +
                "3. Deposit Money\n" +
                "4. Mini Statement\n" +
                "5. Exit\n");
        option = scan.nextInt();
        cases();
    }

    void cases() throws InterruptedException {
        switch (option) {
            case 1 -> balance();
            case 2 -> withdraw();
            case 3 -> deposit();
            case 4 -> statement();
            case 5 -> exit();
        }
    }

    void withdraw() throws InterruptedException {
        System.out.print("Enter amount to withdraw: MWK");
        amount = scan.nextDouble();
        System.out.println("Proceed to withdraw " + money.format(amount) + " from your account?");
        operation = "withdrawn";
        location = "from";
        confirmation();
        Thread.sleep(3000);
        menu();
    }

    void processing() throws InterruptedException {
        int i = 0;
        while (i < 3) {
            System.out.print("Please wait...");
            Thread.sleep(500);
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
            System.out.print("              ");
            Thread.sleep(500);
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
            i++;
        }
    }

    void deposit() throws InterruptedException {
        System.out.print("Enter amount to deposit: MWK");
        amount = scan.nextDouble();
        System.out.println("Proceed to deposit " + money.format(amount) + " into your account?");
        operation = "deposited";
        location = "into";
        confirmation();
        Thread.sleep(2000);
        menu();
    }

    private void addStatement() {
        if (statements.size() < 5) {
            statements.add(0,"You " + operation + " " +
                    "" + money.format(amount) + " " + location + " your account");
        }else {
            statements.remove(0);
            addStatement();
        }
    }

    void statement() throws InterruptedException {
        for (int i = 0; i < statements.size(); i++) {
            System.out.println((i+1) + ". " + statements.get(i));
        }
        Thread.sleep(3000);
        menu();
    }

    void exit() {
        System.out.println("Thank you for banking with us.");
    }

    void balance() throws InterruptedException {
        System.out.println("Your balance is " + money.format(accBal)+ ".");
        menu();
    }

    void confirmation() throws InterruptedException {
        System.out.println("1. Yes\n" +
                "2. No\n" +
                "0. Menu");
        option = scan.nextInt();
        switch (option) {
            case 1 -> {
                if (operation.equals("withdrawn")) {
                    processing();
                    if (amount <= accBal) {
                        accBal -= amount;
                        addStatement();
                        System.out.print("You have successfully " + operation + " " + money.format(amount) + " " + location + " your account.\n" +
                                "Your new balance is " + money.format(accBal) + ".");
                    }else {
                        System.out.print("You have insufficient funds.\n" +
                                "Your current balance is " + money.format(accBal) + ".");
                    }
                }else {
                    accBal += amount;
                    addStatement();
                    processing();
                    System.out.print("You have successfully " + operation + " " + money.format(amount) + " " + location + " your account.\n" +
                            "Your new balance is " + money.format(accBal) + ".");
                }

            }
            case 2 -> {
                if (operation.equals("withdrawn")) {
                    withdraw();
                }else {
                    deposit();
                }
            }
            case 0 -> menu();
            default -> {
                System.out.println("Wrong entry");
                confirmation();
            }
        }
    }
}
