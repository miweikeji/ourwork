package app.entity;

/**
 * Created by tlt on 2015/10/24.
 */
public class Phone {
    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
