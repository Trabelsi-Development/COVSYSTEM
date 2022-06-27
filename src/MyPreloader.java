
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class MyPreloader extends Preloader{
    
    private Stage preloaderStage;
    private Scene scene;
    
    public MyPreloader()
    {
        
    }
    
    @Override
    public void init() throws Exception
    {Parent root1=FXMLLoader.load(getClass().getResource("COVSYSTEMApplication.fxml"));
        scene=new Scene(root1);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {this.preloaderStage=primaryStage;
    preloaderStage.setScene(scene);
    preloaderStage.initStyle(StageStyle.UNDECORATED);
    preloaderStage.show();
        
    }
    
    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info)
        { if (info instanceof ProgressNotification)
        {COVSYSTEMApplicationController.loading.setText("Loading "+((ProgressNotification)info).getProgress()*100+" %");
        COVSYSTEMApplicationController.myProgressBar.setProgress(((ProgressNotification)info).getProgress());
        }
    }
    
     @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info)
    {StateChangeNotification.Type type=info.getType();
        switch(type)
        {
            case BEFORE_START:
                preloaderStage.hide();
                break;

        }

    }
    
}
