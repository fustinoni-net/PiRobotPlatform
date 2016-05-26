/*
 * Copyright 2007-2008 Volker Fritzsch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package motej.demos.buttons;

import motej.Mote;
import motej.demos.common.SimpleMoteFinder;
import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class ButtonsDemo {
	
//	private static Logger log = LoggerFactory.getLogger(ButtonsDemo.class);

	public static void main(String[] args) {
		SimpleMoteFinder simpleMoteFinder = new SimpleMoteFinder();
		Mote mote = simpleMoteFinder.findMote();
		mote.addCoreButtonListener(new CoreButtonListener() {
		
			public void buttonPressed(CoreButtonEvent evt) {
				if (evt.isButtonAPressed()) {
					System.out.println("Button A pressed!");
				}
				if (evt.isButtonBPressed()) {
					System.out.println("Button B pressed!");
				}
				if (evt.isNoButtonPressed()) {
					System.out.println("No button pressed.");
				}
			}
		
		});
		
		try {
			Thread.sleep(60000l);
		} catch (InterruptedException ex) {
//			log.error(ex.getMessage(), ex);
		} finally {
			mote.disconnect();
		}
	}
}
