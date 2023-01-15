package iwwwdnw.gui.impl;

import iwwwdnw.logic.LogicFactory;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.logic.port.SpielzugPort;
import iwwwdnw.spielzug.port.Spielzug;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.Subject;

import java.util.Scanner;

public class Controller implements Observer {
	
	private Gui gui;
	private Scanner scanner;
	private MVCPort mvcPort;
	private SpielzugPort spielzugPort;
	
	
	public Controller (Gui gui, MVCPort mvcPort, SpielzugPort spielzugPort) {
		this.gui = gui;
		this.mvcPort = this.gui.getMvcPort();
		this.mvcPort.attach(this);
		scanner = new Scanner(System.in);
		this.spielzugPort = spielzugPort;
	}

	@Override
	public void update(State state) {
		System.out.println("Here controller needs to do something");
	}
	
    public MVCPort getMvcPort() {
        return mvcPort;
    }
	
	void doit() {
		this.gui.show("What do you want to do next?");
		String input = scanner.nextLine();
	}

}
