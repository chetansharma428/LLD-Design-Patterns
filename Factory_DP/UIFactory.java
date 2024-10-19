 package Factory_DP;

import Factory_DP.components.button.Button;
import Factory_DP.components.dropdown.DropDown;
import Factory_DP.components.menu.Menu;

public interface UIFactory {
    //factory methods
    Button createButton();
    Menu createMenu();
    DropDown createDropDown();
    
}