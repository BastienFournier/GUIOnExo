package us.ihmc.eva.GuiOnExo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUIonExoMain extends ApplicationNoModule
{
   @Override
   public void start(Stage primaryStage) throws Exception
   {
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GuionExoFXML/InfoControlGUI.fxml"));
      AnchorPane rootPane = loader.load();
      InfoControlGUIController guiController = loader.getController();

      Scene scene = new Scene(rootPane, 480, 320);

      primaryStage.setTitle("GUIonExo");
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      //primaryStage.setFullScreen(true); 
      guiController.controlModeChoice.requestFocus();

      scene.setOnKeyPressed(event ->
      {

         //Press NUMPAD1,NUMPAD2,NUMPAD3 to simulate errors NUMPAD4 is to loose battery, NUMPAD5 is to charge4444 completely
         //Press DIGIT2,DIGIT2,DIGIT3,DIGIT4 for different level of assistance: 1 is low, 2 is medium and 3 is high. DIGIT4 is off
         //Press a to validate the selection of the control Mode

         //			System.out.println(event.getCode());//debug comment
         if (event.getCode() == KeyCode.NUMPAD1)
         {
            guiController.printError(KeyCode.NUMPAD1);
         }
         else if (event.getCode() == KeyCode.NUMPAD2)
         {
            guiController.printError(KeyCode.NUMPAD2);
         }
         else if (event.getCode() == KeyCode.NUMPAD3)
         {
            guiController.printError(KeyCode.NUMPAD3);
         }
         else if (event.getCode() == KeyCode.NUMPAD4)
         {
            guiController.loosingBattery();
         }
         else if (event.getCode() == KeyCode.NUMPAD5)
            {
               guiController.chargingBattery();
         }
         else if (event.getCode() == KeyCode.DIGIT1)
         {
            guiController.setAssistanceLevel(KeyCode.DIGIT1);
         }
         else if (event.getCode() == KeyCode.DIGIT2)
         {
           guiController.setAssistanceLevel(KeyCode.DIGIT2);
         }
         else if (event.getCode() == KeyCode.DIGIT3)
         {
            guiController.setAssistanceLevel(KeyCode.DIGIT3);
         }
         else if (event.getCode() == KeyCode.DIGIT4)
         {
            guiController.setAssistanceLevel(KeyCode.DIGIT4);
         }
         else if (event.getCode() == KeyCode.A)
         {
            guiController.selectAssistanceMode();
         }
         else if (event.getCode() == KeyCode.UP)
         {
            guiController.goUp();
         }
         else if (event.getCode() == KeyCode.DOWN)

         {
            guiController.goDown();
         }

      });

      primaryStage.show();
      

   }

   public static void main(String[] args)
   {
      launch(args);
   }

}
