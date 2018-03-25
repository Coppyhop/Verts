package com.kjbre.verts.common;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DefFileLoader extends AsynchronousAssetLoader<DefinitionFile, DefFileLoader.DefinitionParameter>{

    private DefinitionFile defFile;

    public DefFileLoader(FileHandleResolver resolver){
        super(resolver);
    }

    protected DefinitionFile getDefFile(){
        return defFile;
    }

    @Override
    public void loadAsync (AssetManager manager, String fileName, FileHandle file, DefinitionParameter parameter){

        String defFile = file.path();

        Properties props = new Properties();
        try {
            props.load(new FileInputStream(defFile));
            String location = props.getProperty("location");
            String type = props.getProperty("type");
            this.defFile = new DefinitionFile(location, type, file.name(), props);
        } catch (IOException e){
            System.err.println("[ERROR] " + e.getMessage());
        }
    }

    @Override
    public DefinitionFile loadSync (AssetManager manager, String fileName, FileHandle file, DefinitionParameter parameter){
        DefinitionFile defFile = this.defFile;
        this.defFile = null;
        return defFile;
    }

    @Override
    public Array<AssetDescriptor> getDependencies (String fileName, FileHandle file, DefinitionParameter parameter){
        return null;
    }

    static public class DefinitionParameter extends AssetLoaderParameters<DefinitionFile> {

    }
}
