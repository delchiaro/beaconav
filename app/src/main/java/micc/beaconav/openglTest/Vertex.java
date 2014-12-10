package micc.beaconav.openglTest;

/**
 * Created by nagash on 09/12/14.
 */
public class Vertex
{
    private float x;
    private float y;
    private float z;

    public Vertex(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex(Vertex copy)
    {
        this.x = copy.x;
        this.y = copy.y;
        this.z = copy.z;
    }

    public float get_x() { return this.x;}
    public float get_y() { return this.y;}
    public float get_z() { return this.z;}

    public void set_x(float value){ this.x = value; }
    public void set_y(float value){ this.y = value; }
    public void set_z(float value){ this.z = value; }

}
