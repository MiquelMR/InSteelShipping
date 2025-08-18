package com.mmauridev.insteelshipping.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "freights")
public class Freight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "freight_id")
    private Long freightId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "operation_id", referencedColumnName = "operation_id", nullable = false)
    private Operation operation;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id", nullable = false)
    private Route route;

    @Column(name = "weight_kg", precision = 10, scale = 2)
    private BigDecimal weightKg;

    @Column(name = "cost", precision = 15, scale = 2)
    private BigDecimal cost;

    public Freight() {
    }

    public Freight(Operation operation, Route route, BigDecimal weightKg, BigDecimal cost) {
        this.operation = operation;
        this.route = route;
        this.weightKg = weightKg;
        this.cost = cost;
    }

    public Long getFreightId() {
        return freightId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public BigDecimal getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(BigDecimal weightKg) {
        this.weightKg = weightKg;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}

