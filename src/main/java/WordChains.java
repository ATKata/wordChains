import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Tree based approach to the word chains problem.
 * Builds up a tree structure of possible word chains.
 *
 * Rather than stop on the first word chain it finds, this continues, outputting each chain as it finds it.
 * While this means the algorithm takes longer, it is also capable of finding multiple chains.
 *
 */

public class WordChains {
    public static Stream<String> dictionary() {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("words.txt").toURI());
            return Files.lines(path, Charset.forName("ISO-8859-1"))
                    .filter(s -> s.matches("\\p{Lower}*"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static int distance(String origin, String target) {
        if (origin.length() != target.length()) {
            throw new IllegalArgumentException("Invalid value: " + origin);
        };
        return IntStream.range(0, origin.length())
                .map(i -> origin.charAt(i) == target.charAt(i) ? 0:1)
                .sum();
    }

    public static WordChainsNode calculate(String origin, String target) {
        if (origin.length() != target.length()) {
            throw new IllegalArgumentException("Invalid value: " + origin);
        };
        return calculate(new WordChainsNode(null, origin, distance(origin, target)), origin, target);
    }

    public static WordChainsNode calculate(final WordChainsNode node, final String origin, final String target) {
        WordChains.dictionary()
                .filter(w -> w.length() == origin.length())
                .filter(w -> distance(origin, w) <= 1)
                .filter(w -> !node.chain().contains(w))
                .map(w -> new WordChainsNode(node, w, distance(w, target)))
                .filter(n -> n.distanceToTarget() < node.distanceToTarget())
                .forEach(n -> calculate(n, n.word(), target));
        return node;
    }

    public static void main(String[] args) {
        calculate("cat", "dog");
    }
}
