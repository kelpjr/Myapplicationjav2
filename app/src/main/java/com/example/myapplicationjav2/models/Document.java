package com.example.myapplicationjav2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("index")
    @Expose
    private String index;

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("page_number")
    @Expose
    private String page_number;
    @SerializedName("match")
    @Expose
    private String match;
    @SerializedName("probs")
    @Expose
    private String probs;

    @Override
    public String toString() {
        return "Document{" +
                "index='" + index + '\'' +
                ", result='" + result + '\'' +
                ", source='" + source + '\'' +
                ", score='" + score + '\'' +
                ", page_number='" + page_number + '\'' +
                ", match='" + match + '\'' +
                ", probs='" + probs + '\'' +
                '}';
    }
}
