//Submitted by : Shweta
package com.summit.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Reviews {
  protected Integer reviewId;
  protected Timestamp created;
  protected String content;
  protected Double rating;
  protected Users user;
  protected Date visitDate;
  protected Trails trail;

  public Reviews(Integer reviewId, Timestamp created, String content, Double rating,
      Users user, Date visitDate, Trails trail) {
    this.reviewId = reviewId;
    this.created = created;
    this.content = content;
    this.rating = rating;
    this.user = user;
    this.visitDate = visitDate;
    this.trail = trail;
  }

  public Reviews(Timestamp created, String content, Double rating,
      Users user, Date visitDate, Trails trail) {
    this.created = created;
    this.content = content;
    this.rating = rating;
    this.user = user;
    this.visitDate = visitDate;
    this.trail = trail;
  }

  public Integer getReviewId() {
    return reviewId;
  }

  public void setReviewId(Integer reviewId) {
    this.reviewId = reviewId;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }

  public Date getVisitDate() {
    return visitDate;
  }

  public void setVisitDate(Date visitDate) {
    this.visitDate = visitDate;
  }

  public Trails getTrail() {
    return trail;
  }

  public void setTrail(Trails trail) {
    this.trail = trail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reviews reviews = (Reviews) o;
    return Objects.equals(reviewId, reviews.reviewId) && Objects
        .equals(created, reviews.created) && Objects.equals(content, reviews.content)
        && Objects.equals(rating, reviews.rating) && Objects
        .equals(user, reviews.user) && Objects.equals(visitDate, reviews.visitDate)
        && Objects.equals(trail, reviews.trail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reviewId, created, content, rating, user, visitDate, trail);
  }

  @Override
  public String toString() {
    return "Reviews{" +
        "reviewId=" + reviewId +
        ", created=" + created +
        ", content='" + content + '\'' +
        ", rating=" + rating +
        ", user=" + user +
        ", visitDate=" + visitDate +
        ", trail=" + trail +
        '}';
  }
}
