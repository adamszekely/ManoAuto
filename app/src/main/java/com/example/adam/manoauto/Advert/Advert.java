package com.example.adam.manoauto.Advert;

/**
 * Created by Adam on 19/11/2017.
 * Class for storing adverts retrieved from firebase.
 */

public class Advert {
    public String airCond;
    public String carName;
    public String carType;
    public String color;
    public String doorNo;
    public String engine;
    public String ess;
    public String fuel;
    public String gear;
    public String id;
    public String imageURL1;
    public String imageURL2;
    public String imageURL3;
    public String imageURL4;
    public String imageURL5;
    public String imageURL6;
    public String imageURL7;
    public String km;
    public String location;
    public String price;
    public String seat;
    public String state;
    public String steering;
    public String wheelDrive;
    public String wheelSize;
    public String year;

    public Advert(){};

    public Advert(String airCond,String carName,String carType,String color,String doorNo,String engine,String ess,String fuel,String gear,
                  String id,String imageURL1,String imageURL2,String imageURL3,String imageURL4,String imageURL5,String imageURL6,String imageURL7,
                  String km,String location,String price,String seat,String state,String steering,String wheelDrive,String wheelSize,String year )
    {
        this.airCond=airCond;
        this.carName=carName;
        this.carType=carType;
        this.color=color;
        this.doorNo=doorNo;
        this.engine=engine;
        this.ess=ess;
        this.fuel=fuel;
        this.gear=gear;
        this.id=id;
        this.imageURL1=imageURL1;
        this.imageURL2=imageURL2;
        this.imageURL3=imageURL3;
        this.imageURL4=imageURL4;
        this.imageURL5=imageURL5;
        this.imageURL6=imageURL6;
        this.imageURL7=imageURL7;
        this.km=km;
        this.location=location;
        this.price=price;
        this.seat=seat;
        this.state=state;
        this.steering=steering;
        this.wheelDrive=wheelDrive;
        this.wheelSize=wheelSize;
        this.year=year;
    }


    public void setAirCond(String airCond) {
        this.airCond = airCond;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setEss(String ess) {
        this.ess = ess;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageURL1(String imageURL1) {
        this.imageURL1 = imageURL1;
    }

    public void setImageURL2(String imageURL2) {
        this.imageURL2 = imageURL2;
    }

    public void setImageURL3(String imageURL3) {
        this.imageURL3 = imageURL3;
    }

    public void setImageURL4(String imageURL4) {
        this.imageURL4 = imageURL4;
    }

    public void setImageURL5(String imageURL5) {
        this.imageURL5 = imageURL5;
    }

    public void setImageURL6(String imageURL6) {
        this.imageURL6 = imageURL6;
    }

    public void setImageURL7(String imageURL7) {
        this.imageURL7 = imageURL7;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSteering(String steering) {
        this.steering = steering;
    }

    public void setWheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

    public void setWheelSize(String wheelSize) {
        this.wheelSize = wheelSize;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAirCond() {
        return airCond;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarType() {
        return carType;
    }

    public String getColor() {
        return color;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public String getEngine() {
        return engine;
    }

    public String getEss() {
        return ess;
    }

    public String getFuel() {
        return fuel;
    }

    public String getGear() {
        return gear;
    }

    public String getId() {
        return id;
    }

    public String getImageURL1() {
        return imageURL1;
    }

    public String getImageURL2() {
        return imageURL2;
    }

    public String getImageURL3() {
        return imageURL3;
    }

    public String getImageURL4() {
        return imageURL4;
    }

    public String getImageURL5() {
        return imageURL5;
    }

    public String getImageURL6() {
        return imageURL6;
    }

    public String getImageURL7() {
        return imageURL7;
    }

    public String getKm() {
        return km;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public String getSeat() {
        return seat;
    }

    public String getState() {
        return state;
    }

    public String getSteering() {
        return steering;
    }

    public String getWheelDrive() {
        return wheelDrive;
    }

    public String getWheelSize() {
        return wheelSize;
    }

    public String getYear() {
        return year;
    }



}
