package Factory_DP;

import Factory_DP.components.button.Button;
import Factory_DP.components.button.IosButton;
import Factory_DP.components.dropdown.DropDown;
import Factory_DP.components.dropdown.IosDropDown;
import Factory_DP.components.menu.Menu;

public class IosUIFactory implements UIFactory {
     public Button createButton(){
        return new IosButton();
    }

    public Menu createMenu(){
        return null;
    }
    public DropDown createDropDown(){
        return new IosDropDown();
    }
}
