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
package motej.demos.ircamera;

import motej.Mote;
import motej.demos.common.SimpleMoteFinder;
import motej.event.IrCameraEvent;
import motej.event.IrCameraListener;
import motej.request.ReportModeRequest;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class IrCameraDemo {

	public static void main(String[] args) throws InterruptedException {
		IrCameraListener listener = new IrCameraListener() {
		
			public void irImageChanged(IrCameraEvent evt) {
				System.out.println(evt.getIrPoint(0).getX() + " : " + evt.getIrPoint(0).getY());
			}
		
		};
		
		SimpleMoteFinder simpleMoteFinder = new SimpleMoteFinder();
		Mote mote = simpleMoteFinder.findMote();
		mote.addIrCameraListener(listener);
		mote.enableIrCamera();
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x36);
		
		Thread.sleep(60000l);
		
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x30);
		mote.disableIrCamera();
		mote.disconnect();
	}
}
