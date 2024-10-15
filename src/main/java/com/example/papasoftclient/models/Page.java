package com.example.papasoftclient.models;

public class Page {
    private int total;
    private int paginas;

    public Page(){}
    public Page(int total, int paginas) {}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}
