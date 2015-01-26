package micc.beaconav.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagash on 25/01/15.
 */
public class GraphNode <ValueType, LinkWeight>
{
    ValueType value;
    List<GraphLink> arcs;

    public GraphNode(ValueType value) {
        this.value = value;
        arcs = new ArrayList<>();
    }


    public void addDirectedLink(GraphNode<ValueType, LinkWeight> linkedNode, LinkWeight linkWeight)
    {
        this.arcs.add(new GraphLink(linkedNode,linkWeight));
    }

    public void addDoubleLink(GraphNode<ValueType, LinkWeight> linkedNode, LinkWeight linksWeight)
    {
        this.addDoubleLink(linkedNode, linksWeight, linksWeight);
    }

    public void addDoubleLink(GraphNode<ValueType, LinkWeight> linkedNode, LinkWeight goingLinkWeight, LinkWeight returnLinkWeight)
    {
        this.arcs.add(new GraphLink(linkedNode,goingLinkWeight));
        linkedNode.arcs.add(new GraphLink(this,returnLinkWeight));
    }





    public GraphLink[] getBestPathLinks(GraphNode goal)
    {
        return null;
    }
    public GraphNode[] getBestPathNodes(GraphNode goal)
    {
        return null;
    }





    public class GraphLink
    {
        private LinkWeight weight;
        private GraphNode<ValueType, LinkWeight> linkedNode;

        public GraphLink(GraphNode<ValueType, LinkWeight> linkedNode, LinkWeight linkWeight)
        {
            this.linkedNode = linkedNode;
            this.weight = linkWeight;
        }

        public LinkWeight getWeight() {
            return weight;
        }

        public GraphNode<ValueType, LinkWeight> getLinkedNode() {
            return linkedNode;
        }
    }

}
