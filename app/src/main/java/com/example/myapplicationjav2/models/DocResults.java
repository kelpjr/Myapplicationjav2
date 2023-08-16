package com.example.myapplicationjav2.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocResults {
    @SerializedName("data")
    private List<Document> document;

    public List<Document> getDocument(){
        return document;
    }

}
