package com.example.fyp;

import java.util.HashMap;

public class Upload {
    private String mName , mImageUri;

    public HashMap getParameters() {
        return Parameters;
    }

    public void setParameters(HashMap parameters) {
        Parameters = parameters;
    }

    private HashMap Parameters;

    public Upload(){

    }

    public Upload(HashMap parameters){
        if (parameters != null){
            Parameters = parameters;
        }
    }
    public Upload(String Name, String ImageUri){
        if(Name.trim().equals("")){
            Name = "No Name";
        }
        mName = Name;
        mImageUri = ImageUri;
    }


    public String getmName(){
        return mName;
    }

    public void setmName(String Name){
        mName = Name;
    }

    public String getmImageUri(){
        return mImageUri;
    }

    public  void setmImageUri(String ImageUri){
        mImageUri = ImageUri;
    }
}
