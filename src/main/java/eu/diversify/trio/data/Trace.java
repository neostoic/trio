/**
 *
 * This file is part of TRIO.
 *
 * TRIO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TRIO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TRIO.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package eu.diversify.trio.data;

import eu.diversify.trio.codecs.DataFormat;
import eu.diversify.trio.simulation.Action;
import java.util.ArrayList;
import java.util.List;

import static eu.diversify.trio.simulation.actions.AbstractAction.*;

/**
 * The data collected by the recorder
 */
public class Trace {

    private final int capacity;
    private final List<State> states;

    public Trace(int capacity) {
        this.capacity = capacity;
        this.states = new ArrayList<State>();
        states.add(new State(none(), 0, capacity, 0));
    }

    public <T> T accept(DataSetVisitor<T> visitor) {
        return visitor.visitTrace(this);
    }
    
    public List<Integer> disruptionLevels() {
        final List<Integer> levels = new ArrayList<Integer>();
        for (State eachState: states) {
            levels.add(eachState.getDisruptionLevel());
        }
        return levels;
    }

    public void record(Action action, int activityLevel) {
        final State next = current().update(action, activityLevel);
        this.states.add(next);
    }

    private State current() {
        if (states.isEmpty()) {
            throw new IllegalStateException("A trace shall have at least one state!");
        }
        final int lastIndex = states.size() - 1;
        return states.get(lastIndex);
    }

    public State afterDisruption(int level) {
        for (State eachState: states) {
            if (eachState.getDisruptionLevel() == level) {
                return eachState;
            }
        }
        return null;
    }

    public int length() {
        return states.size() - 1;
    }

    public String to(DataFormat format, int index) {
        final String EOL = java.lang.System.lineSeparator();
        final StringBuilder builder = new StringBuilder();
        for (State eachState: states) {
            builder.append(eachState.to(format, index)).append(EOL); 
        }
        return builder.toString();
    }

}
