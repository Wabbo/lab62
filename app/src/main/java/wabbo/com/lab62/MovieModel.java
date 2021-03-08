package wabbo.com.lab62;

public class MovieModel {
    String title, imgURL;
    Double rating;
    Integer releaseYear;

    public MovieModel(String title, Double rating, String imgURL, Integer releaseYear) {
        this.title = title;
        this.rating = rating;
        this.imgURL = imgURL;
        this.releaseYear = releaseYear;
    }

    public MovieModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getrating() {
        return rating;
    }

    public void setrating(Double rating) {
        this.rating = rating;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Integer getreleasYear() {
        return releaseYear;
    }

    public void setreleasYear(Integer releasYear) {
        this.releaseYear = releasYear;
    }
}
