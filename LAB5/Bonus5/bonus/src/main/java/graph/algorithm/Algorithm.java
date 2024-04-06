package graph.algorithm;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.clique.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Algorithm {
    SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    public Algorithm(Map<String, List<String>> manAbilities){

        for (Map.Entry<String, List<String>> entry : manAbilities.entrySet()) {
            List<String> men = entry.getValue();

            for (String man : men) {
                graph.addVertex(man);
            }

            for (int i = 0; i < men.size(); i++) {
                for(int j = i + 1; j < men.size(); j++){
                    graph.addEdge(men.get(i), men.get(j));
                }
            }
        }

        makeBiggestGroup();

    }

    private void makeBiggestGroup(){
        BronKerboschCliqueFinder<String, DefaultEdge> cliqueFinder;

        int maxSize = 0;
        while(!graph.vertexSet().isEmpty()) {
            cliqueFinder = new BronKerboschCliqueFinder<>(graph);
            for (Set<String> clique : cliqueFinder) {
                if (clique.size() > maxSize) {
                    maxSize = clique.size();
                }
            }

            for (Set<String> clique : cliqueFinder) {
                if (clique.size() == maxSize) {
                    System.out.println("group: " + clique);

                    for(String node : clique){
                        graph.removeVertex(node);
                    }
                    break;
                }
            }
            maxSize = 0;
        }
    }

}
