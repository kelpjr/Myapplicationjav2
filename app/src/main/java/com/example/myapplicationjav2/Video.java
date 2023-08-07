package com.example.myapplicationjav2;

import java.net.URI;
import java.util.ArrayList;

public class Video {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpatial_orientation() {
        return spatial_orientation;
    }

    public void setSpatial_orientation(String spatial_orientation) {
        this.spatial_orientation = spatial_orientation;
    }

    public boolean isGlasses_boolean() {
        return glasses_boolean;
    }

    public void setGlasses_boolean(boolean glasses_boolean) {
        this.glasses_boolean = glasses_boolean;
    }

    public boolean isHat_boolean() {
        return hat_boolean;
    }

    public void setHat_boolean(boolean hat_boolean) {
        this.hat_boolean = hat_boolean;
    }

    public Object getHolding_objects() {
        return holding_objects;
    }

    public void setHolding_objects(Object holding_objects) {
        this.holding_objects = holding_objects;
    }

    public Object getBag_type() {
        return bag_type;
    }

    public void setBag_type(Object bag_type) {
        this.bag_type = bag_type;
    }

    public Object getUpper_attire() {
        return upper_attire;
    }

    public void setUpper_attire(Object upper_attire) {
        this.upper_attire = upper_attire;
    }

    public Object getLower_attire() {
        return lower_attire;
    }

    public void setLower_attire(Object lower_attire) {
        this.lower_attire = lower_attire;
    }

    public boolean isBoots_boolean() {
        return boots_boolean;
    }

    public void setBoots_boolean(boolean boots_boolean) {
        this.boots_boolean = boots_boolean;
    }

    public URI getMotion_event() {
        return motion_event;
    }

    public void setMotion_event(URI motion_event) {
        this.motion_event = motion_event;
    }

    public ArrayList getColors() {
        return colors;
    }

    public void setColors(ArrayList colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "VideoDataModel{" +
                "id='" + id + '\'' +
                ", caption='" + caption + '\'' +
                ", time_stamp='" + time_stamp + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", spatial_orientation='" + spatial_orientation + '\'' +
                ", glasses_boolean=" + glasses_boolean +
                ", hat_boolean=" + hat_boolean +
                ", holding_objects=" + holding_objects +
                ", bag_type=" + bag_type +
                ", upper_attire=" + upper_attire +
                ", lower_attire=" + lower_attire +
                ", boots_boolean=" + boots_boolean +
                ", motion_event=" + motion_event +
                ", colors=" + colors +
                '}';
    }

    private String id;
    private String caption;
    private String time_stamp;
    private String gender;
    private int age;
    private String spatial_orientation;
    private boolean glasses_boolean;
    private boolean hat_boolean;
    private Object holding_objects;
    private Object bag_type;
    private Object upper_attire;
    private Object lower_attire;
    private boolean boots_boolean;
    private URI motion_event;
    private ArrayList colors;

    public Video(String id, String caption, String time_stamp, String gender, int age, String spatial_orientation, boolean glasses_boolean, boolean hat_boolean, Object holding_objects, Object bag_type, Object upper_attire, Object lower_attire, boolean boots_boolean, URI motion_event, ArrayList colors) {
        this.id = id;
        this.caption = caption;
        this.time_stamp = time_stamp;
        this.gender = gender;
        this.age = age;
        this.spatial_orientation = spatial_orientation;
        this.glasses_boolean = glasses_boolean;
        this.hat_boolean = hat_boolean;
        this.holding_objects = holding_objects;
        this.bag_type = bag_type;
        this.upper_attire = upper_attire;
        this.lower_attire = lower_attire;
        this.boots_boolean = boots_boolean;
        this.motion_event = motion_event;
        this.colors = colors;
    }
}
