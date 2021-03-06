package test.edu.chl.proximity.Models.ResourceSystem;

import edu.chl.proximity.Models.ResourceSystem.Resources;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Hanna Romer
 * @date 2015-05-19
 */
public class ResourcesTest {

    @Test
    public void testSetResources() throws Exception{
        Resources r=new Resources(0,0,0);

        r.setResources(20,20,20);
        assertTrue(r.getPoints() == 20 && r.getLines() == 20 && r.getPolygons() == 20);

        r.setResources(-10, -34, -435);
        assertTrue(r.getPoints() == -10 && r.getLines() == -34 && r.getPolygons() == -435);

        r.setResources(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertTrue(r.getPoints()==Integer.MAX_VALUE && r.getLines()==Integer.MAX_VALUE && r.getPolygons()==Integer.MAX_VALUE);
    }

    @Test
    public void testGetPoints() {
        Resources r=new Resources(20,13,79);
        assertTrue(r.getPoints()==20);
    }

    @Test
    public void testGetLines() {
        Resources r=new Resources(20,13,79);
        assertTrue(r.getLines()==13);
    }

    @Test
    public void testGetPolygons() {
        Resources r=new Resources(20,13,79);
        assertTrue(r.getPolygons()==79);
    }

    @Test
    public void testAddResources(){
        Resources r = new Resources(9,4987,33);
        r.addResources(new Resources(0, 0, 0));
        r.addResources(new Resources(234, 5, 21));
        assertTrue(r.getPoints() == (9 + 234) && r.getLines() == (4987 + 5) && r.getPolygons() == (33 + 21));

        r.setResources(1, 1, 1);
        r.addResources(new Resources(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertTrue(r.getPoints()==Integer.MIN_VALUE && r.getLines()==Integer.MIN_VALUE && r.getPolygons()==Integer.MIN_VALUE);

        r.setResources(2,2,2);
        r.addResources(null);
        assertTrue(r.getPoints()==2 && r.getLines()==2 && r.getPolygons()==2);

    }

    @Test
    public void testAddPoints(){
        Resources r = new Resources(12,48,293);
        r.addPoints(234);
        assertTrue(r.getPoints() == (12 + 234));

        r.setResources(0, 0, 0);
        r.addPoints(Integer.MAX_VALUE);
        assertTrue(r.getPoints() == Integer.MAX_VALUE);
        r.addPoints(1);
        assertTrue(r.getPoints()==Integer.MIN_VALUE);
    }

    @Test
    public void testAddLines(){
        Resources r = new Resources(12,48,293);
        r.addLines(54);
        assertTrue(r.getLines()==(48+54));
    }

    @Test
    public void testAddPolygons(){
        Resources r = new Resources(12,48,293);
        r.addPolygons(9);
        assertTrue(r.getPolygons()==(293+9));
    }

    @Test
    public void testRemoveResources(){
        Resources r=new Resources(100,63,766);
        Resources r2 = new Resources(32,21,4);
        r.removeResources(r2);
        assertTrue(r.getPoints()==(100-32) && r.getLines()==(63-21) && r.getPolygons()==(766-4));

        r.setResources(2,2,2);
        r.removeResources(null);
        assertTrue(r.getPoints()==2 && r.getLines()==2 && r.getPolygons()==2);
    }

    @Test
    public void testRemovePoints(){
        Resources r=new Resources(234,23,4);
        r.removePoints(3);
        assertTrue(r.getPoints()==231);
    }

    @Test
    public void testRemoveLines(){
        Resources r=new Resources(234,23,4);
        r.removeLines(21);
        assertTrue(r.getLines()==2);
    }

    @Test
    public void testRemovePolygons(){
        Resources r=new Resources(234,23,4);
        r.removePolygons(1);
        assertTrue(r.getPolygons()==3);
    }
}
