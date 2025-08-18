package com.mmauridev.insteelshipping.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "name", nullable = false, length = 255)
    private String name; // e.g., "Shanghai → Rotterdam"

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequenceNumber ASC") // keeps points in order
    private List<RoutePoint> points;

    public Route() {
    }

    public Route(String name, List<RoutePoint> points) {
        this.name = name;
        this.points = points;
        if (points != null) {
            points.forEach(p -> p.setRoute(this));
        }
    }

    public Long getRouteId() {
        return routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoutePoint> getPoints() {
        return points;
    }

    public void setPoints(List<RoutePoint> points) {
        this.points = points;
    }
}

