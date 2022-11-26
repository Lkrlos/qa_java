package com.example;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class LionTest  {

    @Mock
    Feline feline;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final String sex;
    private final boolean hasMane;

    public LionTest(String sex, boolean hasMane) {
        this.sex = sex;
        this.hasMane = hasMane;
    }

    @Parameterized.Parameters(name = "Sex lion - {0}, expected {1}")
    public static Object [][] getParametr() {
        return new Object[][] {
                {"Самец", true},
                {"Самка", false},
        };
    }

    @Test
    public void testDoesHaveMane() throws Exception {
        var lion = new Lion(sex, new Feline());
        assertEquals(hasMane, lion.doesHaveMane());
    }

    @Test
    public void testExpectedException() {
        Exception actualExeption = assertThrows(Exception.class, () ->
                new Lion("qwerty", feline));
        assertEquals("Используйте допустимые значения пола животного - самей или самка", actualExeption.getMessage());
    }

    @Test
    public void testGetFood() throws Exception {
        when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        var cat = new Cat(feline);
        assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.getFood("Хищник"));
    }


    @Test
    public void testGetKittens() {
        when(feline.getKittens()).thenReturn(1);
        var cat = new Cat(feline);
        assertEquals(1, feline.getKittens());
    }
}