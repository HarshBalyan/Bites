package harsh.volleytest;

/**
 * Created by harsh on 10/14/2015.
 */
public class Restaurant {
    private String imgUrl, Rest_Name, City_Name;


    public Restaurant(String imgUrl, String Rest_Name, String City_Name) {
        this.imgUrl = imgUrl;
        this.City_Name = City_Name;
        this.Rest_Name = Rest_Name;

    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRest_Name() {
        return Rest_Name;
    }

    public void setRest_Name(String rest_Name) {
        Rest_Name = rest_Name;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public void setCity_Name(String city_Name) {
        City_Name = city_Name;
    }
}





