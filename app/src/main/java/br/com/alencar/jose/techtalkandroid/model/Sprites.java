package br.com.alencar.jose.techtalkandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jose on 26/07/17.
 */

public class Sprites {
    @SerializedName("front_default")
    private String frontDefault;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}
