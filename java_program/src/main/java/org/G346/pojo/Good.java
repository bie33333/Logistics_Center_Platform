package org.G346.pojo;

public class Good {
    private String name;

    private Integer integral;

    private Integer stock;

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", integral=" + integral +
                ", stock=" + stock +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}