package br.edu.ifpb.coffeeshop.drinkmanagement.exception;

public class StockExceptions {
    public static class InvalidBeverageException extends Exception {
        public InvalidBeverageException() {
            super("Cannot add a null or invalid beverage to stock.");
        }
    }

    public static class NoBeveragesToRemoveException extends Exception {
        public NoBeveragesToRemoveException() {
            super("No beverages were provided to remove.");
        }
    }
}
