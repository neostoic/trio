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


package eu.diversify.trio.unit.core.requirements;

import eu.diversify.trio.core.DefaultSystemVisitor;
import eu.diversify.trio.core.SystemVisitor;
import eu.diversify.trio.core.requirements.Conjunction;
import eu.diversify.trio.core.requirements.Nothing;
import eu.diversify.trio.core.requirements.Require;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Specification of the 'nothing' literal (i.e., TRUE)
 */
@RunWith(JUnit4.class)
public class NothingTest {

    
      @Test
    public void beginShouldTriggerEnterOnTheVisitor() {
        final Mockery context = new JUnit4Mockery();

        final Nothing nothing = Nothing.getInstance();
        
        final SystemVisitor visitor = context.mock(SystemVisitor.class);

        context.checking(new Expectations() {
            {
                oneOf(visitor).enter(nothing);
            }
        });

        nothing.begin(visitor);

        context.assertIsSatisfied();
    }

   
    @Test
    public void endShouldTriggerEnterOnTheVisitor() {
        final Mockery context = new JUnit4Mockery();

        final Nothing nothing = Nothing.getInstance();
        
        final SystemVisitor visitor = context.mock(SystemVisitor.class);

        context.checking(new Expectations() {
            {
                oneOf(visitor).exit(nothing);
            }
        });

        nothing.end(visitor);

        context.assertIsSatisfied();
    }
    
    
}
