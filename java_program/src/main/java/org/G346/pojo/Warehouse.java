package org.G346.pojo;

public class Warehouse {
    private String id;

    private Integer capacity;

    @Override
    public String toString() {
        return "Warehouse{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}