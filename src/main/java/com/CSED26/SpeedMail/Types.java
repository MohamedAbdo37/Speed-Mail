package com.csed26.speedmail;

import java.util.*;

public class Types {
    private List<String> types = new ArrayList<>();

    public Types() {
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String[] getTypes() {
        return types.toArray(new String[0]);
    }

    public void addType(String newType) {
        types.add(newType);
    }

    public boolean searchForType(String type) {
        for (String temp : types) {
            if (temp.equals(type))
                return true;
        }
        return false;
    }
}
