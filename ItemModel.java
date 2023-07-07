package com.example.fetch;

public class ItemModel {
    String listId;
    String name;
    String id;

    public ItemModel(String listId, String name, String id) {
        this.listId = listId;
        this.name = name;
        this.id = id;
    }

    public String getlistId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
