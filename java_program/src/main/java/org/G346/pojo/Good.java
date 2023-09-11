package org.G346.pojo;

public class Good {
    private String id;

    private String name;

    private String warehouse;

    private Integer number;

    private String good_describe;
    public String getGood_describe() {
        return good_describe;
    }

    public void setGood_describe(String good_describe) {
        this.good_describe = good_describe;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse == null ? null : warehouse.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}