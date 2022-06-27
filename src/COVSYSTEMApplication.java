/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import animatefx.animation.FadeIn;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hp
 */
public class COVSYSTEMApplication extends Application {
    
   private double xOffset=0, yOffset=0;
   private static final int COUNT_LIMIT=10;
   
          
    @Override
    public void start(Stage stage)throws Exception {
        
       Parent root = FXMLLoader.load(getClass().getResource("Register_SignInForms/SIGNINForm.fxml"));
        
        Scene scene = new Scene(root,Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        //to make the window draggable (you can move it)
        root.setOnMousePressed(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {xOffset = event.getSceneX();
            yOffset = event.getSceneY();
                
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event)
           {stage.setX(event.getScreenX() - xOffset);
           stage.setY(event.getScreenY() - yOffset);
           
               
           }
        });
        stage.setScene(scene);               
        stage.centerOnScreen();
        stage.show();
            
        new FadeIn(root).play();//animation
    }

    @Override
    public void init() throws Exception
    {for (int i=0;i<COUNT_LIMIT;i++)
        {double progress=(double)i/10;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        Thread.sleep(2000);}
    }
           /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       LauncherImpl.launchApplication(COVSYSTEMApplication.class, MyPreloader.class,args);
    } 
}
