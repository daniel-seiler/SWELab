package iwwwdnw.ui.impl;

import iwwwdnw.ui.impl.commands.Commands;
import iwwwdnw.ui.impl.commands.InputException;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.spielzug.port.SpielzugPort;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;

import java.util.Scanner;

public class Controller implements Observer {
    
    private final UiImpl gui;
    private final Scanner scanner;
    private final MVCPort mvcPort;
    private final SpielzugPort spielzugPort;
    private State currentState;
    
    
    public Controller(UiImpl gui, MVCPort mvcPort, SpielzugPort spielzugPort) {
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
        String input = scanner.nextLine();
        try {
            gui.show(Commands.executeMatching(input, spielzugPort).toString());
        } catch(InputException ie) {
            gui.show(ie.getMessage());
        }
    }
    
}
