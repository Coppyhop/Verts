package com.kjbre.verts.common;

import java.util.Properties;

public class DefinitionFile {

    public String location;
    public DefinitionType type;
    public String name;
    public Properties properties;

    public DefinitionFile(String location, DefinitionType type, String name, Properties properties) {
        this.location = location;
        this.type = type;
        this.name = name;
        this.properties = properties;
    }

    public String getLocation() {
        return location;
    }

    public DefinitionType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Properties getProperties() {
        return properties;
    }
}
