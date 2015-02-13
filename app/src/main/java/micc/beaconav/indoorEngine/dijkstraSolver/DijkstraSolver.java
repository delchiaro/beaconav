package micc.beaconav.indoorEngine.dijkstraSolver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by nagash on 05/02/15.
 */
public class DijkstraSolver< NC extends DijkstraNodeAdapter> {




    private DijkstraNodeAdapter getBestPredecessor(DijkstraNodeAdapter node) {
        return node.getDijkstraStatistic().getBestPredecessor();
    }
    private double getBestWeight(DijkstraNodeAdapter node) {
        return node.getDijkstraStatistic().getBestWeight();
    }



    private boolean trySetPredecessor(DijkstraNodeAdapter node, DijkstraNodeAdapter tryToBePredecessorNode) {

        DijkstraNodeAdapter bestPredecessor = node.getDijkstraStatistic().getBestPredecessor();
        double newWeight = getBestWeight(tryToBePredecessorNode) +  tryToBePredecessorNode.getArchWeight(node);
        if(bestPredecessor == null || newWeight < getBestWeight(node))
        {
            node.getDijkstraStatistic().setBestPredecessor(tryToBePredecessorNode);
            node.getDijkstraStatistic().setBestWeight(newWeight);
            return true;
        }
        else return false;
    }




    public LinkedList<DijkstraNodeAdapter> solve( NC start, NC goal)
    {
        start.getDijkstraStatistic().reset();
        goal.getDijkstraStatistic().reset();


        DijkstraComparator comparator = new DijkstraComparator();


        // non utile per l'algoritmo, ma serve per resettare le statistiche a fine algoritmo
        HashSet<DijkstraNodeAdapter> nodeAdapterVisited = new HashSet<>();

        TreeSet<DijkstraNodeAdapter> frontiera = new TreeSet<DijkstraNodeAdapter>(comparator);

        start.getDijkstraStatistic().setAsStartPoint();

        List<DijkstraNodeAdapter> adjacent = start.getAdjacent();
        for(DijkstraNodeAdapter node : adjacent)
        {
            if( trySetPredecessor(node, start) )
            {
                frontiera.add(node);
                nodeAdapterVisited.add(node);
            }
            else return null;
            // dovrebbe essere sempre true alla partenza, altrimenti significa che le statistiche sono state manomesse
        }


        while(frontiera.isEmpty() == false && goal.getDijkstraStatistic().isPermanentNode() == false)
        {
            DijkstraNodeAdapter bestInFrontiera = frontiera.pollFirst();
            bestInFrontiera.getDijkstraStatistic().setAsPermanent();

            for(DijkstraNodeAdapter node : bestInFrontiera.getAdjacent())
            {
                if( trySetPredecessor(node, bestInFrontiera) ) {
                    frontiera.add(node);
                    nodeAdapterVisited.add(node);
                }
            }
        }




        // creo la lista da ritornare, il risultato
        LinkedList<DijkstraNodeAdapter> bestPath = new LinkedList<>();
        DijkstraNodeAdapter iter = goal;
        while(iter.getDijkstraStatistic().getBestPredecessor() != null)
        {
            bestPath.push(iter);
            iter = goal.getDijkstraStatistic().getBestPredecessor();
        }


        for( DijkstraNodeAdapter node : nodeAdapterVisited )
        {
            node.getDijkstraStatistic().reset();
        }
        // resetto tutte le statistiche dei nodi toccati

        return bestPath;

    }
}
