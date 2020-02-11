package com.stqa.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
@Test
  public void testPoint1(){
  Point p = new Point(2,0);
  Point p1 = new Point(4,0);
  Assert.assertEquals(2.0, p.distance(p1) );
}

  @Test
  public void testPoint2(){
    Point p = new Point(2,22);
    Point p1 = new Point(6,22);
    Assert.assertEquals(4.0, p.distance(p1) );
  }
}
