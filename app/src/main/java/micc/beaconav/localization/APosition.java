package micc.beaconav.localization;

/**
 * Created by Nagash on 29/12/2014.
 */
public abstract class APosition
{
    static public double distance(APosition p1, APosition p2){
        float xDistance = xDistance(p1, p2);
        float yDistance = yDistance(p1, p2);
        return Math.sqrt(xDistance*xDistance + yDistance*yDistance);

    }
    static public float xDistance(APosition p1, APosition p2)
    {
        return p1.X() - p2.X();
    }
    static public float yDistance(APosition p1, APosition p2)
    {
        return p1.Y() - p2.Y();
    }


    public abstract float X();
    public abstract float Y();


    public double getDistance(APosition otherPosition)
    {
        return APosition.distance(this, otherPosition);
    }


}
