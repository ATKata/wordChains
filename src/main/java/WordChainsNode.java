import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordChainsNode {
    private final WordChainsNode parent;
    private final int distanceToTarget;
    private final String word;
    private final List<WordChainsNode> childNodes;
    public WordChainsNode(WordChainsNode parent, String word, int distanceToTarget) {
        this.parent = parent;
        this.word = word;
        this.distanceToTarget = distanceToTarget;
        this.childNodes = new ArrayList<>();
        if (this.parent != null) {
            parent.addChildNode(this);
        }
        if (distanceToTarget == 0) {
            System.out.printf("Found: %s%n", toString());
        }
    }
    @Override
    public String toString() {
        return String.format("%s", this.chain().stream().collect(Collectors.joining(", ")));
    }
    public List<String> chain() {
        return buildChain(new ArrayList<>());
    }

    public int distanceToTarget() {
        return distanceToTarget;
    }

    public String word() {
        return word;
    }

    private List<String> buildChain(List<String> chain) {
        chain.add(0, this.word);
        if (parent != null) {
            return parent.buildChain(chain);
        }
        return chain;
    }
    private void addChildNode(WordChainsNode wordChainsNode) {
        childNodes.add(wordChainsNode);
    }
}
