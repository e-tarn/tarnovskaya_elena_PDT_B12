package com.stqa.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    Square s = new Square(5);

    System.out.println("Area of the square with side, equals " + s.area() + " is " + s.area());

    Rectangle r = new Rectangle(12, 3);

    System.out.println("Area of the rectangle with sides, equals " + r.a + " and " + r.b + " is " + r.area());


  }


}
