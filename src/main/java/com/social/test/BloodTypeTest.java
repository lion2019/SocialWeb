package com.social.test;

import com.social.domain.BloodType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BloodTypeTest {
   @Test
    void test(){
       for(BloodType bt: BloodType.values()){
           System.out.println(bt);
       }
       System.out.println(BloodType.O);
   }
}