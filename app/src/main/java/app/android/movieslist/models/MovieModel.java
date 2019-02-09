package app.android.movieslist.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class MovieModel implements Parcelable {

    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;
    private String[] people;
    private String[] species;
    private String[] locations;
    private String[] vehicles;

    public MovieModel(String id, String title, String description, String director, String producer, String release_date, String rt_score, String[] people, String[] species, String[] locations, String[] vehicles) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.producer = producer;
        this.release_date = release_date;
        this.rt_score = rt_score;
        this.people = people;
        this.species = species;
        this.locations = locations;
        this.vehicles = vehicles;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRt_score() {
        return rt_score;
    }

    public void setRt_score(String rt_score) {
        this.rt_score = rt_score;
    }

    public String[] getPeople() {
        return people;
    }

    public void setPeople(String[] people) {
        this.people = people;
    }

    public String[] getSpecies() {
        return species;
    }

    public void setSpecies(String[] species) {
        this.species = species;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public String[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(String[] vehicles) {
        this.vehicles = vehicles;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.director);
        dest.writeString(this.producer);
        dest.writeString(this.release_date);
        dest.writeString(this.rt_score);
        dest.writeStringArray(this.people);
        dest.writeStringArray(this.species);
        dest.writeStringArray(this.locations);
        dest.writeStringArray(this.vehicles);
    }

    protected MovieModel(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.director = in.readString();
        this.producer = in.readString();
        this.release_date = in.readString();
        this.rt_score = in.readString();
        this.people = in.createStringArray();
        this.species = in.createStringArray();
        this.locations = in.createStringArray();
        this.vehicles = in.createStringArray();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
