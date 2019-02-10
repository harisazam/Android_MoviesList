package app.android.movieslist.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by harrisazam on 2/10/2019.
 */

public class VehicleModel implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String vehicle_class;
    private String length;

    public VehicleModel(String id, String name, String description, String vehicle_class, String length) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.vehicle_class = vehicle_class;
        this.length = length;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVehicle_class() {
        return vehicle_class;
    }

    public void setVehicle_class(String vehicle_class) {
        this.vehicle_class = vehicle_class;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.vehicle_class);
        dest.writeString(this.length);
    }

    protected VehicleModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.vehicle_class = in.readString();
        this.length = in.readString();
    }

    public static final Creator<VehicleModel> CREATOR = new Creator<VehicleModel>() {
        @Override
        public VehicleModel createFromParcel(Parcel source) {
            return new VehicleModel(source);
        }

        @Override
        public VehicleModel[] newArray(int size) {
            return new VehicleModel[size];
        }
    };
}
