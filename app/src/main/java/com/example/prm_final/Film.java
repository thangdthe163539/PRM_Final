package com.example.prm_final;

import java.io.Serializable;

public class Film implements Serializable {
    int id;
    String name;
    String category;
    String image;
    String video;
    String content;
    String score;
    int views;
    String date;

    public Film() {
    }

    public Film(int id, String name, String category, String image, String video, String content, String score, String date, int views) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.image = image;
        this.video = video;
        this.content = content;
        this.score = score;
        this.views = views;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", content='" + content + '\'' +
                ", score='" + score + '\'' +
                ", views=" + views +
                ", date='" + date + '\'' +
                '}';
    }
}
