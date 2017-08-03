package omidheshmatinia.github.com.concentrationgame.model;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class PictureCard {
    private String imageUrl;
    private int id;

    public PictureCard(String imageUrl, int id) {
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
