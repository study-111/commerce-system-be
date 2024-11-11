package study111.commerce.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {

    ORDERED("Ordered"),
    PAID("Paid"),
    CANCELED("Canceled");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }
}
