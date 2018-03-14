package com.example.adou.mydrone.dto.Request;

import com.example.adou.mydrone.dto.LocationDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hakima on 3/13/18.
 */

public class MissionDto {

    private String id;

    private String title;

    private LocalDateTime dateBegin;

    private LocalDateTime dateEnd;

    private List<OrderDto> orderList;

    private List<LocationDto> locationDroneList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDateTime dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<OrderDto> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDto> orderList) {
        this.orderList = orderList;
    }

    public List<LocationDto> getLocationDroneList() {
        return locationDroneList;
    }

    public void setLocationDroneList(List<LocationDto> locationDroneList) {
        this.locationDroneList = locationDroneList;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    private String userLogin;

}
