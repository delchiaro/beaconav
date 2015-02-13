package micc.beaconav.indoorEngine.dijkstraSolver;

/**
 * Created by nagash on 13/02/15.
 */
public class DijkstraStatistics
{
    private boolean _isPermanent;
    private DijkstraNodeAdapter _bestPredecessor;
    private double _bestWeight;




    public DijkstraStatistics() {
        reset();
    }
    public void reset() {
        _isPermanent = false;
        _bestPredecessor = null;
        _bestWeight = Double.POSITIVE_INFINITY;
    }


    void setAsStartPoint() {
        setAsPermanent();
        _bestWeight = 0.;
        _bestPredecessor = null;
    }

    DijkstraNodeAdapter getBestPredecessor() {
        return this._bestPredecessor;
    }
    void setBestPredecessor(DijkstraNodeAdapter newBestPredecessor) {
        this._bestPredecessor = newBestPredecessor;
    }
    void setBestWeight(double newBestWeight) {
        this._bestWeight = newBestWeight;
    }
    double getBestWeight() {
        return _bestWeight;
    }



    void setAsPermanent() {
        this._isPermanent = true;
    }
    boolean isPermanentNode() {
        return this._isPermanent;
    }




}
