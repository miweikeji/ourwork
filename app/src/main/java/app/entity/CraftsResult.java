package app.entity;

/**
 * Created by Administrator on 2015/10/15.
 */
public class CraftsResult extends Meta  {

    private Crafts crafts;

    public Crafts getCrafts() {
        return crafts;
    }

    public void setCrafts(Crafts crafts) {
        this.crafts = crafts;
    }

    @Override
    public String toString() {
        return "CraftsResult{" +
                "crafts=" + crafts +
                '}';
    }
}
