import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PresentationTest {
    Presentation presentation;
    Slide slide1, slide2, slide3;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        slide1 = new Slide();
        slide2 = new Slide();
        slide3 = new Slide();
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.append(slide3);
    }

    @Test
    void startsAtSlide1() {
        assertEquals(1, presentation.getSlideNumber());
        assertEquals(slide1, presentation.getCurrentSlide());
    }

    @Test
    void retrieveSlide_3_returnsSlide3(){
        assertEquals(slide3, presentation.getSlide(3));
    }

    @Test
    void retrieveSlide_4_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> presentation.getSlide(4));
    }

    @Test
    void nextSlide_once_currentSlideIs2(){
        presentation.nextSlide();
        assertEquals(2, presentation.getSlideNumber());
    }

    @Test
    void prevSlide_once_throwsIllegalArgumentException() {
        presentation.prevSlide();
        assertThrows(IllegalArgumentException.class, () -> presentation.prevSlide());
    }

    @Test
    void nextSlide_prevSlide_currentSlideIs1(){
        presentation.nextSlide();
        presentation.prevSlide();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void getSize_normal_returns3(){
        assertEquals(3, presentation.getSize());
    }

    @Test
    void clear_normal_removesAllSlides(){
        presentation.clear();
        assertEquals(0, presentation.getSize());
        assertThrows(IllegalArgumentException.class, () -> presentation.getSlide(0));
    }
}
