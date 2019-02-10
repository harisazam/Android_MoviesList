package app.android.movieslist.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by harrisazam on 2/10/2019.
 */

public class SpeciesModel implements Parcelable {

    private String id;
    private String name;
    private String classification;
    private String eye_colors;
    private String hair_colors;
    private String length;

    public SpeciesModel(String id, String name, String classification, String eye_colors, String hair_colors, String length) {
        this.id = id;
        this.name = name;
        this.classification = classification;
        this.eye_colors = eye_colors;
        this.hair_colors = hair_colors;
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getEye_colors() {
        return eye_colors;
    }

    public void setEye_colors(String eye_colors) {
        this.eye_colors = eye_colors;
    }

    public String getHair_colors() {
        return hair_colors;
    }

    public void setHair_colors(String hair_colors) {
        this.hair_colors = hair_colors;
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
        dest.writeString(this.classification);
        dest.writeString(this.eye_colors);
        dest.writeString(this.hair_colors);
        dest.writeString(this.length);
    }

    protected SpeciesModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.classification = in.readString();
        this.eye_colors = in.readString();
        this.hair_colors = in.readString();
        this.length = in.readString();
    }

    public static final Creator<SpeciesModel> CREATOR = new Creator<SpeciesModel>() {
        @Override
        public SpeciesModel createFromParcel(Parcel source) {
            return new SpeciesModel(source);
        }

        @Override
        public SpeciesModel[] newArray(int size) {
            return new SpeciesModel[size];
        }
    };
}
