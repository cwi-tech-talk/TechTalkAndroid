package br.com.alencar.jose.techtalkandroid.model;

import java.io.Serializable;

/**
 * Created by jose on 25/07/17.
 */

public class BasicItemDTO implements Serializable {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
