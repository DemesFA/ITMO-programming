package com.rafael.bank;

import com.rafael.bank.model.Account;
import com.rafael.bank.service.Controller;
import com.rafael.bank.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI(new Controller(), System.in, System.out);
        ui.start();
    }
}