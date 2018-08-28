package com.kekelian.bean;

import java.util.List;

public class GankResp {
    private boolean error;
    private List<GankEntry> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankEntry> getResults() {
        return results;
    }

    public void setResults(List<GankEntry> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankResp{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
