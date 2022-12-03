package com.zzh.kafka.streams.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/11/28 21:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String firstName;
    private String LastName;
    private String customerId;
    private String creditCardNumber;
    private String itemPurchased;
    private String department;
    private Integer quantity;
    private double price;
    private Date purchaseDate;
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(firstName, that.firstName) && Objects.equals(LastName, that.LastName) && Objects.equals(customerId, that.customerId) && Objects.equals(creditCardNumber, that.creditCardNumber) && Objects.equals(itemPurchased, that.itemPurchased) && Objects.equals(department, that.department) && Objects.equals(quantity, that.quantity) && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, LastName, customerId, creditCardNumber, itemPurchased, department, quantity, price, purchaseDate, zipCode);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", itemPurchased='" + itemPurchased + '\'' +
                ", department='" + department + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

}
