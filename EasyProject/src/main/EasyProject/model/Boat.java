package main.EasyProject.model;

public class Boat {
    private Long id;
    private String title;
    private int rentalPrice;
    private int purchasePrice;

    private int speed;
    private int seating;

    private int maxTransportedWeight;

    private String imageURL;
    private String description;

    public Boat(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSeating() {
        return seating;
    }

    public void setSeating(int seating) {
        this.seating = seating;
    }

    public int getMaxTransportedWeight() {
        return maxTransportedWeight;
    }

    public void setMaxTransportedWeight(int maxTransportedWeight) {
        this.maxTransportedWeight = maxTransportedWeight;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}