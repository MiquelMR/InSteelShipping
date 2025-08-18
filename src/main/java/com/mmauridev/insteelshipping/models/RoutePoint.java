package com.mmauridev.insteelshipping.models;

import jakarta.persistence.*;

@Entity
@Table(name = "route_points")
public class RoutePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_point_id")
    private Long routePointId;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "sequence_number")
    private int sequenceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    public RoutePoint() {
    }

    public RoutePoint(double latitude, double longitude, int sequenceNumber, Route route) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.sequenceNumber = sequenceNumber;
        this.route = route;
    }

    public Long getRoutePointId() {

        return routePointId;
    }

    public void setRoutePointId(Long routePointId) {
        this.routePointId = routePointId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
