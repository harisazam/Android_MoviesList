package app.android.movieslist.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by harrisazam on 2/10/2019.
 */

public class LocationModel implements Parcelable {

    private String id;
    private String name;
    private String climate;
    private String terrain;
    private String surface_water;
    private String[] residents;

    public LocationModel(String id, String name, String climate, String terrain, String surface_water, String[] residents) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.surface_water = surface_water;
        this.residents = residents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public void setSurface_water(String surface_water) {
        this.surface_water = surface_water;
    }

    public String[] getResidents() {
        return residents;
    }

    public void setResidents(String[] residents) {
        this.residents = residents;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.climate);
        dest.writeString(this.terrain);
        dest.writeString(this.surface_water);
        dest.writeStringArray(this.residents);
    }

    protected LocationModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.climate = in.readString();
        this.terrain = in.readString();
        this.surface_water = in.readString();
        this.residents = in.createStringArray();
    }

    public static final Creator<LocationModel> CREATOR = new Creator<LocationModel>() {
        @Override
        public LocationModel createFromParcel(Parcel source) {
            return new LocationModel(source);
        }

        @Override
        public LocationModel[] newArray(int size) {
            return new LocationModel[size];
        }
    };
}
