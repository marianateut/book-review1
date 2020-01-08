package org.fasttrackit.bookreview1.transfer;

import javax.validation.constraints.NotNull;

public class SaveBookRequest {
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private int yearOfRelease;
    @NotNull
    private String language;
    @NotNull
    private String type;
    @NotNull
    private String description;
    private int pages;
    private String imagePath;
    private Double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SaveBookRequest{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", language='" + language + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", pages=" + pages +
                ", imagePath='" + imagePath + '\'' +
                ", price=" + price +
                '}';
    }
}
