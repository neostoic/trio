/**
 *
 * This file is part of TRIO.
 *
 * TRIO is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * TRIO is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TRIO. If not, see <http://www.gnu.org/licenses/>.
 */
package eu.diversify.trio.analysis;

import eu.diversify.trio.data.State;
import eu.diversify.trio.data.Trace;
import java.util.List;

/**
 * Compute the robustness
 */
public class Robustness extends Metric {

    public Robustness() {
        super(NAME, "none");
    }
    public static final String NAME = "Robustness";

    @Override
    public void exitTrace(Trace trace) {
        int robustness = 0;
        final List<Integer> disruptions = trace.disruptionLevels();
        for (int i = 0; i < disruptions.size(); i++) {
            final State current = trace.afterDisruption(disruptions.get(i));
            int step = trace.getControlCapacity() + 1 - i;
            if (i < disruptions.size() - 1) {
                step = disruptions.get(i + 1) - i;
            }
            robustness += step * current.getObservedActivityLevel();
        }
        distribution().record(trace.label(), robustness);
    }

}
