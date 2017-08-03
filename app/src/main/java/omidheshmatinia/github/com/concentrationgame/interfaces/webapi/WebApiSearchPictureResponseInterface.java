package omidheshmatinia.github.com.concentrationgame.interfaces.webapi;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.interfaces.WebApiErrorInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public interface WebApiSearchPictureResponseInterface extends WebApiErrorInterface {
    void searchComplete(int maxNumber, List<PictureCard> images);
}
