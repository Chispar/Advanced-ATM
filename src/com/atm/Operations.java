package com.atm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Operations {
    DecimalFormat money = new DecimalFormat("'MWK'###,###,##0.00");
    Scanner scan = new Scanner(System.in);
    private double accBal = 0, amount = 0;
    private int option = 0;
    private String operation, location;

    ArrayList<String> statements = new ArrayList<>();

    public void menu() {
        System.out.println("\n\nSelect a service:" +
                "\n" +
                "1. Check Balance\n" +
                "2. Withdraw Money\n" +
                "3. Deposit Money\n" +
                "4. Mini Statement\n" +
                "5. Exit\n");
        option = scan.nextInt();
        cases();
    }

    private void cases() {
        switch (option) {
            case 1 -> balance();
            case 2 -> withdraw();
            case 3 -> deposit();
            case 4 -> statement();
            case 5 -> exit();
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: MWK");
        amount = scan.nextDouble();
        System.out.println("Proceed to withdraw " + money.format(amount) + " from your account?");
        operation = "withdrawn";
        location = "from";
        confirmation();
        menu();
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: MWK");
        amount = scan.nextDouble();
        System.out.println("Proceed to deposit " + money.format(amount) + " into your account?");
        operation = "deposited";
        location = "into";
        confirmation();
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

    private void statement() {
        for (int i = 0; i < statements.size(); i++) {
            System.out.println((i+1) + ". " + statements.get(i));
        }
        menu();
    }

    private void exit() {
        System.out.println("Thank you for banking with us.");
    }

    private void balance() {
        System.out.println("Your balance is " + money.format(accBal)+ ".");
        menu();
    }

    private void confirmation() {
        System.out.println("1. Yes\n" +
                "2. No\n" +
                "0. Menu");
        option = scan.nextInt();
        switch (option) {
            case 1 -> {
                if (operation.equals("withdrawn")) {
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
