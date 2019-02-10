package app.android.movieslist.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by harrisazam on 2/10/2019.
 */

public class PeopleModel implements Parcelable {

    private String id;
    private String name;
    private String gender;
    private String age;
    private String eye_color;
    private String hair_color;
    private String[] films;
    private String species;

    public PeopleModel(String id, String name, String gender, String age, String eye_color, String hair_color, String[] films, String species) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.eye_color = eye_color;
        this.hair_color = hair_color;
        this.films = films;
        this.species = species;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String[] getFilms() {
        return films;
    }

    public void setFilms(String[] films) {
        this.films = films;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.gender);
        dest.writeString(this.age);
        dest.writeString(this.eye_color);
        dest.writeString(this.hair_color);
        dest.writeStringArray(this.films);
        dest.writeString(this.species);
    }

    protected PeopleModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.gender = in.readString();
        this.age = in.readString();
        this.eye_color = in.readString();
        this.hair_color = in.readString();
        this.films = in.createStringArray();
        this.species = in.readString();
    }

    public static final Creator<PeopleModel> CREATOR = new Creator<PeopleModel>() {
        @Override
        public PeopleModel createFromParcel(Parcel source) {
            return new PeopleModel(source);
        }

        @Override
        public PeopleModel[] newArray(int size) {
            return new PeopleModel[size];
        }
    };
}
