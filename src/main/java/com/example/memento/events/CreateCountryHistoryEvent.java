package com.example.memento.events;

public class CreateCountryHistoryEvent {

    public final Integer id;
    public final String oldName;
    public final String NewName;

    public CreateCountryHistoryEvent(Integer id, String oldName, String newName) {
        this.id = id;
        this.oldName = oldName;
        NewName = newName;
    }
}
