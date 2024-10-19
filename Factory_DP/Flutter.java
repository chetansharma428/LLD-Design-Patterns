package Factory_DP;

public class Flutter {
    //non factory methods
    void refresh(){
        System.out.println("REFRESHED UI");
    }

    void setTheme(){
        System.out.println("Set Theme");
    }

    public UIFactory getUIFactory(SupportedPlatforms platform){
        return UIFactoryFactory.getUIFactoryForPlatform(platform);
    }
}
