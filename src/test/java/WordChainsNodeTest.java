import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordChainsNodeTest {
    WordChainsNode topNode;
    @Before
    public void setup() {
        topNode = new WordChainsNode(null, "fred", 20);
    }


    @Test
    public void chain_forTopNode_containsOnlyOrigin() {
        assertThat(topNode.chain()).containsExactly("fred");
    }

    @Test
    public void chain_forNodeBelowTop_containsThisWordAndTopNodeWord() {
        WordChainsNode node = new WordChainsNode(topNode, "likes", 2);
        assertThat(node.chain()).containsExactly("fred", "likes");
    }

    @Test
    public void chain_level3() {
        WordChainsNode node = new WordChainsNode(topNode, "likes", 2);
        WordChainsNode childNode = new WordChainsNode(node, "bread", 1);
        assertThat(childNode.chain()).containsExactly("fred", "likes", "bread");
    }
}
