package Factory_DP;

import Factory_DP.components.button.Button;
import Factory_DP.components.dropdown.DropDown;

public class Client {
    public static void main(String[] args){
        Flutter flutter = new Flutter();
        UIFactory uiFactory = flutter.getUIFactory(SupportedPlatforms.IOS);

        Button button = uiFactory.createButton();
        button.clickButton();

        DropDown dropDown = uiFactory.createDropDown();
        dropDown.showDropDown();
    }
}
