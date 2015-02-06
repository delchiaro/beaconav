package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;

import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.indoorEngine.spot.Spot;
import micc.beaconav.indoorEngine.spot.Vertex;

/**
 * Created by nagash on 24/01/15.
 */
public class ConvexArea extends Drawable
{
    private Room _containerRoom;
    private ArrayList<Ingress> ingresses;
    private ArrayList<Vertex> _vertices = new ArrayList<Vertex>();

    public ConvexArea(Room containerRoom){
        super(0);
        this._containerRoom = containerRoom;
    }


    @Override
    protected void _coreDraw(Canvas canvas) {
        Iterator<Ingress> ingressIter = ingresses.iterator();
        while(ingressIter.hasNext()) {
            ingressIter.next().draw(canvas); //delego disegno delle porte ad ogni convex area
        }
    }

    public Room getConteinerRoom(){
        return this._containerRoom;
    }
    public void pushCorner(Vertex newCorner) {
        this._vertices.add(newCorner);
    }
    public void addCorner(Vertex newCorner, int index) {
        this._vertices.add(index, newCorner);
    }
    public int indexOfCorner(Vertex corner){
        return this._vertices.indexOf(corner);
    }
    public Vertex addRoomCorner(int roomCornerIndex) {
        Vertex corner = _containerRoom.getCorner(roomCornerIndex);
        this._vertices.add(_containerRoom.getCorner(roomCornerIndex));
        return corner;
    }


    public boolean checkSpotInArea(Vertex spot)
    {
        int size = _vertices.size();
        Iterator<Vertex> iter = _vertices.iterator();

        float[] vert_x = new float[size];
        float[] vert_y = new float[size];

        for(int i = 0; iter.hasNext(); i++)
        {
            Vertex vertex = iter.next();
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



    public boolean isConvex()
    {
        if (_vertices.size()<4)
            return true;

        boolean sign=false;
        int n=_vertices.size();
        for(int i=0;i<n;i++)
        {
            double dx1 = _vertices.get((i+2)%n).getX()-_vertices.get((i+1)%n).getX();
            double dy1 = _vertices.get((i+2)%n).getY()-_vertices.get((i+1)%n).getY();
            double dx2 = _vertices.get(i).getX()-_vertices.get((i+1)%n).getX();
            double dy2 = _vertices.get(i).getY()-_vertices.get((i+1)%n).getY();
            double zcrossproduct = dx1*dy2 - dy1*dx2;
            if (i==0)
                sign=zcrossproduct>0;
            else
            {
                if (sign!=(zcrossproduct>0))
                    return false;
            }
        }
        return true;
    }


    public final Building getContainerBuilding() {
        return getContainerFloor().getContainerBuilding();
    }

    public final Floor getContainerFloor() {
        return getContainerRoom().getContainerFloor();
    }


    //gestione associazione bidirezionale Room - ConvexArea

    public final Room getContainerRoom(){
        return this._containerRoom;
    }
    final ConvexArea  setContainerRoom(Room room) {
        this._containerRoom = room;
        return this;
    }
    final void  unsetContainerRoom() {
        this._containerRoom = null;
    }
    public final void removeFromContainerRoom() {
        if(this._containerRoom!=null)
            this._containerRoom.removeConvexArea(this);
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


