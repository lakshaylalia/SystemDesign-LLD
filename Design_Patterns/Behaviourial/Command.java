import java.util.*;

class Light {
    public void on() {
        System.out.println("Light turned ON");
    }

    public void off() {
        System.out.println("Light turned OFF");
    }
};

class AC {
    public void on() {
        System.out.println("AC turned ON");
    }

    public void off() {
        System.out.println("AC turned OFF");
    }
};

/*
 * class NaiveRemoteControl {
 * private Light light;
 * private AC ac;
 * private String lastAction = "";
 * 
 * public NaiveRemoteControl(Light light, AC ac) {
 * this.light = light;
 * this.ac = ac;
 * }
 * 
 * public void pressLightOn() {
 * light.on();
 * lastAction = "LIGHT_ON";
 * }
 * 
 * public void pressLightOff() {
 * light.off();
 * lastAction = "LIGHT_OFF";
 * }
 * 
 * public void pressACOn() {
 * ac.on();
 * lastAction = "AC_ON";
 * }
 * 
 * public void pressACOff() {
 * ac.on();
 * lastAction = "AC_OFF";
 * }
 * 
 * public void pressUndo() {
 * switch (lastAction) {
 * case "LIGHT_ON":
 * light.off();
 * lastAction = "LIGHT_OFF";
 * break;
 * 
 * case "LIGHT_OFF":
 * light.on();
 * lastAction = "LIGHT_ON";
 * break;
 * 
 * case "AC_ON":
 * ac.off();
 * lastAction = "AC_OFF";
 * break;
 * 
 * case "AC_OFF":
 * ac.on();
 * lastAction = "AC_ON";
 * break;
 * default:
 * System.out.println("No action to undo");
 * break;
 * }
 * }
 * };
 */

// Command Class
interface command {
    void execute();

    void undo();
};

class LightOnCommand implements command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
};

class LightOffCommand implements command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
};

class ACOnCommand implements command {
    private AC ac;

    public ACOnCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }
};

class ACOffCommand implements command {
    private AC ac;

    public ACOffCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }
};

// Invoker
class RemoteControl {
    private command[] buttons = new command[4];
    private Stack<command> commandHistory = new Stack<>();

    public void setCommand(int slot, command c) {
        buttons[slot] = c;
    }

    public void pressButton(int slot) {
        if (buttons[slot] != null) {
            buttons[slot].execute();
        } else {
            System.out.println("No command assigned to slot: " + slot);
        }
    }

    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            commandHistory.pop().undo();
        } else {
            System.out.println("No commands to undo");
        }
    }
};

public class Command {
    public static void main(String args[]) {
        Light light = new Light();
        AC ac = new AC();                                                                                                                                                                                                    

        // NaiveRemoteControl remote = new NaiveRemoteControl(light, ac);
        // remote.pressLightOn();
        // remote.pressACOn();
        // remote.pressACOff();
        // remote.pressUndo();
        // remote.pressUndo(); // here last action on undo must be AC OFF but it will be Light_On
    }
} 