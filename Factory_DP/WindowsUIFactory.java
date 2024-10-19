package Factory_DP;

import Factory_DP.components.button.Button;
import Factory_DP.components.button.WindowsButton;
import Factory_DP.components.dropdown.DropDown;
import Factory_DP.components.dropdown.WindowsDropDown;
import Factory_DP.components.menu.Menu;

public class WindowsUIFactory implements UIFactory{
     public Button createButton(){
        return new WindowsButton();
    }

    public Menu createMenu(){
        return null;
    }
    public DropDown createDropDown(){
        return new WindowsDropDown();
    }
}
