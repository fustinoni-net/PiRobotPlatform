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
package motej.demos.discovery;

import java.util.ArrayList;
import java.util.List;

import motej.Mote;
import motej.MoteFinder;
import motej.MoteFinderListener;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class AdvancedDiscovery {

	private static List<Mote> motes = new ArrayList<Mote>();
	
	public static void main(String[] args) throws InterruptedException {
		MoteFinderListener listener = new MoteFinderListener() {
		
			public void moteFound(Mote mote) {
				System.out.println("Found mote: " + mote.getBluetoothAddress());
				mote.rumble(2000l);
				motes.add(mote);
			}
		
		};
		
		MoteFinder finder = MoteFinder.getMoteFinder();
		finder.addMoteFinderListener(listener);
		
		System.out.println("Starting discovery..");
		finder.startDiscovery();
		
		System.out.println("Putting thread to sleep..");
		Thread.sleep(30000l);
		
		System.out.println("Stopping discovery..");
		finder.stopDiscovery();
		
		for (Mote m : motes) {
			m.disconnect();
		}
	}

}
