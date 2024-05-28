package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sellerId;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "registeredStudentId", referencedColumnName = "registeredStudentId")
    private RegisteredStudent registeredStudentId;

    private String bankingNumber;

    private String bankingName;

    @OneToMany(mappedBy = "productId", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private List<Product> productId;

    public Seller(RegisteredStudent registeredStudentId, String bankingNumber, String bankingName) {
        this.registeredStudentId = registeredStudentId;
        this.bankingNumber = bankingNumber;
        this.bankingName = bankingName;
    }
}