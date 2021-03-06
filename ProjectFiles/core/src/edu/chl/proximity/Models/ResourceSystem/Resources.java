package edu.chl.proximity.Models.ResourceSystem;

/**
 * @author Hanna Romer
 * @date 2015-04-15.
 *
 * Class containing the different resources in the game; points, lines and polygons
 *
 * ---
 * 08/05 modified by Hanna Romer. Added a way to set resources as given amounts.
 * 21/05 modified by Hanna Romer. Added nullcheckers in addResources and removeResources.
 */
public class Resources {
    private int points;
    private int lines;
    private int polygons;

    /**
     * Create a new instance of Resources
     * @param startPoints Starting amount of points
     * @param startLines Starting amount of lines
     * @param startPolygons Starting amount of polygons
     */
    public Resources(int startPoints, int startLines, int startPolygons ){
        points=startPoints;
        lines=startLines;
        polygons=startPolygons;
    }

    /**
     * Set resources, used when starting new map
     * @param points Set this as points
     * @param lines Set this as lines
     * @param polygons Set this ad polygons
     */
    public void setResources(int points, int lines, int polygons){
        this.points=points;
        this.lines=lines;
        this.polygons=polygons;
    }

    /**
     * Get current amount of points
     * @return current amount of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Get current amount of lines
     * @return current amount of lines
     */
    public int getLines() {
        return lines;
    }

    /**
     * Get current amount of polygons
     * @return current amount of polygons
     */
    public int getPolygons() {
        return polygons;
    }

    /**
     * Add all points, lines and polygons from another
     * resources to this resources
     * @param r the instance of resources who's points, lines and polygons is to be added.
     */
    public void addResources(Resources r){
        if(r!=null) {
            points += r.getPoints();
            lines += r.getLines();
            polygons += r.getPolygons();
        }
    }

    /**
     * Add given amount of points to current
     * amount of points
     * @param p amount of points to add
     */
    public void addPoints(int p){
        points += p;
    }

    /**
     * Add given amount of lines to
     * current amount of lines
     * @param l amount of lines to add
     */
    public void addLines(int l){
        lines += l;
    }

    /**
     * Add given amount of polygons to
     * current amount of polygons
     * @param p amount of polygons to add
     */
    public void addPolygons(int p){
        polygons += p;
    }

    /**
     * Remove all points, lines and polygons of another
     * resources from this resources
     * @param r Resources that are to be removed
     */
    public void removeResources(Resources r){
        if(r!=null) {
            points = points - r.getPoints();
            lines = lines - r.getLines();
            polygons = polygons - r.getPolygons();
        }
    }

    /**
     * Remove given amount of points to current
     * amount of points
     * @param p amount of points to remove
     */
    public void removePoints(int p){
        points -= p;
    }

    /**
     * Remove given amount of lines to
     * current amount of lines
     * @param l amount of lines to remove
     */
    public void removeLines(int l){
        lines -= l;
    }

    /**
     * Remove given amount of polygons to
     * current amount of polygons
     * @param p amount of polygons to remove
     */
    public void removePolygons(int p){
        polygons -=p;
    }
}
