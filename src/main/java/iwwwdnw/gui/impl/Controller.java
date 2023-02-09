package iwwwdnw.gui.impl;

import iwwwdnw.gui.impl.commands.Commands;
import iwwwdnw.gui.impl.commands.InputException;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.spielzug.port.SpielzugPort;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;

import java.util.Scanner;

public class Controller implements Observer {
    
    private ViewImpl gui;
    private Scanner scanner;
    private MVCPort mvcPort;
    private SpielzugPort spielzugPort;
    private State currentState;
    
    
    public Controller(ViewImpl gui, MVCPort mvcPort, SpielzugPort spielzugPort) {
        this.gui = gui;
        this.mvcPort = this.gui.getMvcPort();
        this.mvcPort.subject().attach(this);
        scanner = new Scanner(System.in);
        this.spielzugPort = spielzugPort;
    }
    
    @Override
    public void update(State state) {
        this.currentState = state;
    }
    
    public MVCPort getMvcPort() {
        return mvcPort;
    }
    
    void doit() {
        gui.show("What do you want to do next?");
        String input = scanner.nextLine();
        try {
            gui.show(Commands.executeMatching(input, spielzugPort.spielzug()).toString());
        } catch(InputException ie) {
            gui.show(ie.getMessage());
        }
    }
    
}
