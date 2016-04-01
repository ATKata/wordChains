import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/*
 * This Java source file was auto generated by running 'gradle init --type java-library'
 * by 'Alec.Tunbridge' at '31/03/16 16:08' with Gradle 1.10
 *
 * @author Alec.Tunbridge, @date 31/03/16 16:08
 */
public class WordChainsTest {
//    @Ignore
//    @Test public void readFile() throws IOException, URISyntaxException {
//        WordChains classUnderTest = new WordChains();
//        assertThat(classUnderTest.readFile()).isTrue();
//    }

    @Before
    public void setup() throws IOException, URISyntaxException {
    }

    @Test
    public void findWordsOfSameLength() {
        List<String> words = new ArrayList<>(Arrays.asList(new String[]{"bob", "bib", "bap", "to", "boat"}));
        WordChains wordChains = new WordChains(Lists.newArrayList("bob", "bib", "bap", "to", "boat"),"start", "end");
        List<String> result = wordChains.findWordsOfGivenLength(words, 3);
        assertThat(result).hasSize(3).contains("bob", "bib", "bap");
    }

    @Test
    public void distanceOfOne() {
        WordChains wordChains = new WordChains();
        assertThat(wordChains.areOneApart("cat", "cot")).isTrue();
        assertThat(wordChains.areOneApart("cat", "dot")).isFalse();
    }

    @Test
    public void answerWithOneStep() throws IOException, URISyntaxException {
        WordChains wordChains = new WordChains(Lists.newArrayList("cat","cot", "cut", "boat"), "cat", "cot");
        assertThat(wordChains.findChain()).hasSize(2).containsExactly("cat", "cot");
    }

    @Test
    public void answerWithTwoSteps() throws IOException, URISyntaxException {
        WordChains wordChains = new WordChains(Lists.newArrayList("cat", "cut", "boat", "cub"), "cat", "cub");
        assertThat(wordChains.findChain()).containsExactly("cat", "cut", "cub");
    }
}
