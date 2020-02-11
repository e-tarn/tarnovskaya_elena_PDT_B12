package com.stqa.sandbox;

public class Square {
  double l;

  public Square(double len) {
    l = len;
  }

  public double area() {
    return this.l * this.l;
  }
}
