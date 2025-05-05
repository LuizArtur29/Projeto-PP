package br.edu.ifpb.coffeeshop.drinkmanagement.model;

import br.edu.ifpb.coffeeshop.drinkmanagement.model.Itens.Bebida;

import java.util.*;

public class Stock {

    private static Stock instance;
    // Map key is item description (e.g., "Espresso"), value is a list of available instances
    private final Map<String, List<Item>> items;

    private Stock() {
        this.items = new HashMap<>();
    }

    public static Stock getInstance() {
        if (instance == null) {
            instance = new Stock();
        }
        return instance;
    }

    // --- Methods for checking and removing specific Item instances ---

    /**
     * Checks if there is at least one instance of the specified item type available.
     *
     * @param name The description of the item type (e.g., "Espresso").
     * @return true if at least one instance is available, false otherwise.
     */
    public boolean hasItem(String name) {
        return this.items.containsKey(name) && !this.items.get(name).isEmpty();
    }

    public int countItems(String name) {
        try {
            return this.items.get(name).size();
        } catch (Exception e) {
            return 0; // TODO: Add exception
        }
    }

    /**
     * Checks if at least one instance of each item type in the list is available.
     *
     * @param itemNameList A list of item descriptions.
     * @return true if all specified types have at least one instance available, false otherwise.
     */
    public boolean hasItems(List<String> itemNameList) {
        if (itemNameList == null || itemNameList.isEmpty()) {
            return true; // Requesting nothing requires nothing
        }
        for (String itemName : itemNameList) {
            if (!hasItem(itemName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes one instance of each specified item type from the stock.
     * It's recommended to call hasItems first.
     *
     * @param itemNameList A list of item descriptions for which one instance each should be removed.
     */
    public void removeItems(List<String> itemNameList) {
        if (itemNameList == null || itemNameList.isEmpty()) return;

        for (String itemName : itemNameList) {
            List<Item> availableList = this.items.get(itemName);
            if (availableList != null && !availableList.isEmpty()) {
                availableList.remove(availableList.size() - 1); // Remove the last available instance
                // Optional: remove the key if the list becomes empty
                // if (availableList.isEmpty()) {
                //     this.items.remove(itemName);
                // }
            } else {
                System.out.println("Warning: Attempted to remove '" + itemName + "', but none were in stock.");
                // Depending on requirements, you might want to stop or log more severely
            }
        }
    }

    /**
     * Adds a specific Item instance to the stock.
     *
     * @param itemToAdd The Item object to add.
     */
    public void addItem(Item itemToAdd) {
        if (itemToAdd == null) {
            System.out.println("Error: Cannot add a null item to stock.");
            return;
        }
        String itemName = itemToAdd.getName();
        // Get the list for this item type, creating it if it doesn't exist
        List<Item> availableList = this.items.computeIfAbsent(itemName, k -> new ArrayList<>());
        availableList.add(itemToAdd);
    }

    public void addItens(List<Item> itemsToAdd) {
        if (itemsToAdd == null || itemsToAdd.isEmpty()) return;
        for (Item item : itemsToAdd) {
            addItem(item);
        }
    }

    public Item removeItem(String name) {
        if (name == null) {
            System.out.println("Warning: Attempted to remove a null item.");
            return null;
        }

        List<Item> availableList = this.items.get(name);

        if (availableList != null && !availableList.isEmpty()) {
            // Remove the last element from the list (LIFO)
            Item removedItem = availableList.remove(availableList.size() - 1);


            // Optional: Remove the key from the map if the list becomes empty
            // if (availableList.isEmpty()) {
            //     this.items.remove(itemName);
            // }


            return removedItem; // Return the actual object that was removed
        } else {
            System.out.println("Warning: Attempted to remove '" + name + "', but none were in stock.");
            return null; // No item of this type found
        }
    }

    /**
     * Returns an unmodifiable view of the stock map.
     * The keys are item descriptions, and the values are lists of Item instances.
     * Note: While the map itself is unmodifiable, the lists *within* the map are not inherently protected by this call.
     *
     * @return An unmodifiable map representing the current stock.
     */
    public Map<String, List<Item>> getItemsMap() {
        // Return an unmodifiable view, but create a new HashMap to avoid exposing internal state directly
        return Collections.unmodifiableMap(new HashMap<>(this.items));
    }

    public int countCategories() {
        return this.getItemsMap().size();
    }

    /**
     * Gets the number of available instances for a specific item type.
     *
     * @param itemName The description of the item type.
     * @return The count of available instances, or 0 if the type is not stocked.
     */
    public int getItemCount(String itemName) {
        List<Item> availableList = this.items.get(itemName);
        return (availableList != null) ? availableList.size() : 0;
    }

    /**
     * Retorna um resumo do estoque, listando o nome de cada item e a quantidade disponível.
     *
     * @return Um mapa onde a chave é o nome do item e o valor é a quantidade em estoque.
     * Retorna uma visão não modificável do resumo.
     */
    public Map<String, Integer> getStockSummary() {
        Map<String, Integer> summary = new HashMap<>();
        // Itera sobre as entradas do mapa de items
        for (Map.Entry<String, List<Item>> entry : this.items.entrySet()) {
            String itemName = entry.getKey(); // O nome do item (a chave)
            List<Item> itemList = entry.getValue(); // A lista de itens desse tipo
            int count = itemList.size(); // A quantidade de itens desse tipo
            summary.put(itemName, count); // Adiciona ao mapa de resumo
        }
        // Retorna uma cópia não modificável para proteger o estado interno
        return Collections.unmodifiableMap(summary);
    }

    /**
     * Retorna um resumo do estoque de Bebidas, listando o nome de cada bebida e a quantidade disponível.
     * Inclui apenas itens que são instâncias de Beverage e que têm quantidade > 0.
     * @return Um mapa onde a chave é o nome da bebida e o valor é a quantidade em estoque.
     * Retorna uma visão não modificável do resumo.
     */
    public Map<String, Integer> getBeverageStockSummary() {
        Map<String, Integer> beverageSummary = new HashMap<>();
        for (Map.Entry<String, List<Item>> entry : this.items.entrySet()) {
            List<Item> itemList = entry.getValue();
            // Só verificamos o tipo se houver itens na lista
            if (!itemList.isEmpty()) {
                Item firstItem = itemList.get(0);
                if (firstItem instanceof Bebida) {
                    // Se o primeiro item é uma Beverage, assumimos que todos são
                    String itemName = entry.getKey();
                    int count = itemList.size();
                    beverageSummary.put(itemName, count);
                }
            }
            // Nota: Itens de tipo Beverage com quantidade 0 não serão incluídos
            // pois não podemos determinar o tipo sem uma instância.
        }
        return Collections.unmodifiableMap(beverageSummary);
    }

    /**
     * Retorna um resumo do estoque de Adicionais, listando o nome de cada adicional e a quantidade disponível.
     * Inclui apenas itens que são instâncias de Additional e que têm quantidade > 0.
     * @return Um mapa onde a chave é o nome do adicional e o valor é a quantidade em estoque.
     * Retorna uma visão não modificável do resumo.
     */
    public Map<String, Integer> getAdditionalStockSummary() {
        Map<String, Integer> additionalSummary = new HashMap<>();
        for (Map.Entry<String, List<Item>> entry : this.items.entrySet()) {
            List<Item> itemList = entry.getValue();
            // Só verificamos o tipo se houver itens na lista
            if (!itemList.isEmpty()) {
                Item firstItem = itemList.get(0);
                if (firstItem instanceof Additional) {
                    // Se o primeiro item é um Additional, assumimos que todos são
                    String itemName = entry.getKey();
                    int count = itemList.size();
                    additionalSummary.put(itemName, count);
                }
            }
            // Nota: Itens de tipo Additional com quantidade 0 não serão incluídos
            // pois não podemos determinar o tipo sem uma instância.
        }
        return Collections.unmodifiableMap(additionalSummary);
    }
}
