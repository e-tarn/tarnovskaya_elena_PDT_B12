package com.stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
  @Test
  public void testPrimesFor(){
    Assert.assertTrue(Primes.isPrime(7));
  }

  @Test(enabled =  false)
  public void testPrimesWhile(){
    Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
  }
}
