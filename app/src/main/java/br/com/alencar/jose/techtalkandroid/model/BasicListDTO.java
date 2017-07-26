package br.com.alencar.jose.techtalkandroid.model;

import java.util.List;

/**
 * Created by jose on 25/07/17.
 */

public class BasicListDTO {
    private int count;
    private String previous;
    private List<BasicItemDTO> results;
    private String next;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<BasicItemDTO> getResults() {
        return results;
    }

    public void setResults(List<BasicItemDTO> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
