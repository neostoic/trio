package eu.diversity.trio.unit.performance;

import eu.diversify.trio.performance.Options;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

/**
 * Specification of the Options reader class
 */
public class OptionsTest {

    @Test
    public void shouldDetectSetupFile() {
        String[] args = new String[]{"-setup", "my_setup.properties"};

        Options options = Options.readFrom(args);

        assertThat(options.getSetupFile(), is(equalTo(args[1])));
        assertThat(options.getOutputFile(), is(equalTo(Options.DEFAULT_OUTPUT_FILE)));
    }

    @Test
    public void shouldDetectSetupFileWhenShortNotationIsUsed() {
        String[] args = new String[]{"-s", "my_setup.properties"};

        Options options = Options.readFrom(args);

        assertThat(options.getSetupFile(), is(equalTo(args[1])));
        assertThat(options.getOutputFile(), is(equalTo(Options.DEFAULT_OUTPUT_FILE)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectMissingSetupFile() {
        String[] args = new String[]{"-setup"};

        Options options = Options.readFrom(args);
    }

    @Test
    public void shouldDetectOutputFile() {
        String[] args = new String[]{"-output", "results.csv"};

        Options options = Options.readFrom(args);

        assertThat(options.getOutputFile(), is(equalTo(args[1])));
        assertThat(options.getSetupFile(), is(equalTo(Options.DEFAULT_SETUP_FILE)));
    }

    @Test
    public void shouldDetectOutputFileWhenShortNotationIsUsed() {
        String[] args = new String[]{"-o", "results.csv"};

        Options options = Options.readFrom(args);

        assertThat(options.getOutputFile(), is(equalTo(args[1])));
        assertThat(options.getSetupFile(), is(equalTo(Options.DEFAULT_SETUP_FILE)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectMissingOutputFile() {
        String[] args = new String[]{"-output"};

        Options options = Options.readFrom(args);
    }

    @Test
    public void shouldDetectBoth() {
        String[] args = new String[]{"-setup", "setup.properties", "-output", "results.csv"};

        Options options = Options.readFrom(args);

        assertThat(options.getSetupFile(), is(equalTo(args[1])));
        assertThat(options.getOutputFile(), is(equalTo(args[3])));
    }

    @Test
    public void shouldDetectBothInReverseOrder() {
        String[] args = new String[]{"-output", "results.csv", "-setup", "setup.properties"};

        Options options = Options.readFrom(args);

        assertThat(options.getSetupFile(), is(equalTo(args[3])));
        assertThat(options.getOutputFile(), is(equalTo(args[1])));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUnknownOptions() {
        String[] args = new String[]{"-config", "results.csv"};

        Options options = Options.readFrom(args);
    }

}
