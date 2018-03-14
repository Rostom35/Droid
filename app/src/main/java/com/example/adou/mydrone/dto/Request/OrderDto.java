package com.example.adou.mydrone.dto.Request;

import com.example.adou.mydrone.dto.LocationDto;

import java.time.LocalDateTime;

/**
 * Created by hakima on 3/14/18.
 */

public class OrderDto {
    LocationDto location;

    Boolean takePicture;

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public Boolean getTakePicture() {
        return takePicture;
    }

    public void setTakePicture(Boolean takePicture) {
        this.takePicture = takePicture;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    LocalDateTime time;


}
