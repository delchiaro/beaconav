package micc.beaconav.indoorEngine.building;

import java.util.ArrayList;
import java.util.Iterator;

import micc.beaconav.indoorEngine.spot.Spot;

/**
 * Created by nagash on 24/01/15.
 */
public class ConvexArea
{
    private Room _room;
    private ArrayList<Ingress> ingresses;
    private ArrayList<Spot> _vertices = new ArrayList<Spot>();

    public ConvexArea(Room containerRoom){
        this._room = containerRoom;
    }


    public Room getConteinerRoom(){
        return this._room;
    }
    public void pushCorner(Spot newCorner) {
        this._vertices.add(newCorner);
    }
    public void addCorner(Spot newCorner, int index) {
        this._vertices.add(index, newCorner);
    }
    public int indexOfCorner(Spot corner){
        return this._vertices.indexOf(corner);
    }
    public Spot addRoomCorner(int roomCornerIndex) {
        Spot corner = _room.getCorner(roomCornerIndex);
        this._vertices.add(_room.getCorner(roomCornerIndex));
        return corner;
    }


    public boolean checkSpotInArea(Spot spot)
    {
        int size = _vertices.size();
        Iterator<Spot> iter = _vertices.iterator();

        float[] vert_x = new float[size];
        float[] vert_y = new float[size];

        for(int i = 0; iter.hasNext(); i++)
        {
            Spot vertex = iter.next();
            vert_x[i] = vertex.getX();
            vert_y[i] = vertex.getY();
        }

        return ConvexArea.pnpoly(size, vert_x, vert_y, spot.getX(), spot.getY() );
    }

    boolean addIngress(Ingress newIngress)
    {
        if(newIngress.getAreaA() == this  ||  newIngress.getAreaB() == this)
        {
            this.ingresses.add(newIngress);
            return true;
        }
        else return false;
    }










// * * * * STATIC FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    static void linkConvexAreaIngress(ConvexArea area_a, ConvexArea area_b, Ingress newIngress){
        area_a.addIngress(newIngress);
        area_b.addIngress(newIngress);
    }

    // ritorna true se test è all'interno del poligono
    private static boolean pnpoly(int nvert, float[] vertx, float[] verty, float testx, float testy){
        int i, j;
        boolean c = false;
        for (i = 0, j = nvert-1; i < nvert; j = i++)
        {
            if ( ((verty[i]>testy) != (verty[j]>testy)) &&
                    (testx < (vertx[j]-vertx[i]) * (testy-verty[i]) / (verty[j]-verty[i]) + vertx[i]) )
                c = !c;
        }
        return c;
    }
}


