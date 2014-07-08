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
package eu.diversify.trio.actions;

import eu.diversify.trio.Action;
import eu.diversify.trio.Topology;

/**
 * Generalise the behaviour of action and their comparison
 */
public abstract class AbstractAction implements Action {

    public abstract Topology executeOn(Topology topology);

    @Override
    public final boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof AbstractAction) {
            return object.toString().equals(toString());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return toString().hashCode();
    }

    @Override
    public abstract String toString();

    
    /*
     * Factory methods to ease the construction of actions
     */
    
    public static Action none() {
        return None.getInstance();
    }
    
    public static Action activate(String target) {
        return new Activate(target); 
    }
    
    public static Action inactivate(String target) {
        return new Inactivate(target);
    }

}
