package micc.beaconav.indoorEngine.dijkstraSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagash on 05/02/15.
 */

class DijkstraNode<T extends Number, NC extends  DijkstraNodeContentAdapter<T>> {
    private NC content;
    private List<DijkstraNode<T, NC>> adjacentNodes;

    private T bestWeight = null; // null == infinite
    private DijkstraNode<T, NC> bestPredecessor = null; // null == no predecessor parsed

    DijkstraNode(NC content) {
        this.content = content;
        adjacentNodes = new ArrayList<>();
    }

    public void addLinkedNode(DijkstraNode<T, NC> linkedNode, boolean bidirectional) {
        this.adjacentNodes.add(linkedNode);
        if(bidirectional) linkedNode.addLinkedNode(this,false);
    }



}