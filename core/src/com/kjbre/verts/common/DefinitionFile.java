package com.kjbre.verts.common;

import java.util.Properties;
/*
 *  Definition File
 *  This is how we store and get data from a definition file, through a properties file
 */
class DefinitionFile {

    public final String location;
    private final String type;
    private final String name;
    public final Properties properties;

    DefinitionFile(String location, String type, String name, Properties properties) {
        this.location = location;
        this.type = type;
        this.name = name;
        this.properties = properties;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Properties getProperties() {
        return properties;
    }
}
