package Factory_DP;

import Factory_DP.components.button.AndroidButton;
import Factory_DP.components.button.Button;
import Factory_DP.components.dropdown.AndroidDropDown;
import Factory_DP.components.dropdown.DropDown;
import Factory_DP.components.menu.Menu;

public class AndroidUIFactory implements UIFactory{
    public Button createButton(){
        return new AndroidButton();
    }

    public Menu createMenu(){
        return null;
    }
    public DropDown createDropDown(){
        return new AndroidDropDown();
    }
}
