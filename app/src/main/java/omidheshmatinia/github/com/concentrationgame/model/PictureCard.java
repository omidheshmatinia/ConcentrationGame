package omidheshmatinia.github.com.concentrationgame.model;

import android.support.annotation.BoolRes;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class PictureCard {
    private String imageUrl;
    private int id;
    private int position;
    private boolean isRevealed;

    public PictureCard(String imageUrl, int id) {
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    @Override
    public boolean equals(Object obj) {
        PictureCard item = (PictureCard) obj;
        return item.getId()==this.getId() && item.getPosition() == this.getPosition();
    }
}
