package org.nhlstenden.jabberpoint;

import java.util.ArrayList;
import org.nhlstenden.jabberpoint.slide.Slide;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;

/** Maintains the slides in the presentation. */
public class Presentation {
  private String showTitle;
  private ArrayList<Slide> showList;
  private int currentSlideNumber = 0;
  private SlideViewerComponent slideViewComponent;

  public Presentation() {
    this.slideViewComponent = null;
    this.clear();
  }

  public Presentation(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
    this.clear();
  }

  public int getSize() {
    return this.showList.size();
  }

  public String getTitle() {
    return this.showTitle;
  }

  public void setTitle(String nt) {
    this.showTitle = nt;
  }

  public void setShowView(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
  }

  public int getSlideNumber() {
    return this.currentSlideNumber;
  }

  public void setSlideNumber(int number) {
    this.currentSlideNumber = number;
    if (this.slideViewComponent != null) {
      this.slideViewComponent.update(this, this.getCurrentSlide());
    }
  }

  public void prevSlide() {
    if (this.currentSlideNumber > 0) {
      this.setSlideNumber(this.currentSlideNumber - 1);
    }
  }

  public void nextSlide() {
    if (this.currentSlideNumber < (this.showList.size() - 1)) {
      this.setSlideNumber(this.currentSlideNumber + 1);
    }
  }

  void clear() {
    this.showList = new ArrayList<>();
    this.setSlideNumber(-1);
  }

  public void append(Slide slide) {
    this.showList.add(slide);
  }

  public Slide getSlide(int number) {
    if (number < 0 || number >= this.getSize()) {
      return null;
    }
    return this.showList.get(number);
  }

  public Slide getCurrentSlide() {
    return this.getSlide(this.currentSlideNumber);
  }

  public void exit(int n) {
    System.exit(n);
  }
}
