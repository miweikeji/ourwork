package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/10/24.
 */
public class AdvertiseResult extends Meta {

    private List<Advertise>  advert;

    public List<Advertise> getAdvert() {
        return advert;
    }

    public void setAdvert(List<Advertise> advert) {
        this.advert = advert;
    }
}
