package alarmsystem;

import alarmsystem.controller.DefaultAlarmSystem;
import alarmsystem.model.*;
import alarmsystem.view.Menu;

public class Main {
    static void main() {
        Menu menu = new Menu(new DefaultAlarmSystem());
        menu.run();
    }
}
