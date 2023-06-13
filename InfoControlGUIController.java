package us.ihmc.eva.GuiOnExo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import us.ihmc.augmentativeExoskeletons.control.highestLevelStates.AugmentativeControlMode;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InfoControlGUIController implements Initializable
{

   double batteryLevel = 100;

   @FXML
   private ProgressBar batteryLevelProgressBar;

   @FXML
   private Label batteryLevelText;

   @FXML
   private Label errorMessage;

   @FXML
   private Label conrtolModeRunning;

   @FXML
   ListView<AugmentativeControlMode> controlModeChoice = new ListView<>();

   AugmentativeControlMode controlModeChosen = AugmentativeControlMode.DO_NOTHING;
   String assistancelevelChosen = "Off";

   public void initialize(URL arg0, ResourceBundle arg1)
   {
      batteryLevelProgressBar.setStyle("-fx-accent: #00FF00;");
      ObservableList<AugmentativeControlMode> allControlModes = FXCollections.observableArrayList(AugmentativeControlMode.values());
      controlModeChoice.setItems(allControlModes);
      controlModeChoice.getSelectionModel().selectFirst();
      selectAssistanceMode();


   }

   public void printError(KeyCode keycode)//simulation of errors
   {
      if (keycode == KeyCode.NUMPAD1)
      {
         errorMessage.setText("error type1 occured");
      }
      else if (keycode == KeyCode.NUMPAD2)
      {
         errorMessage.setText("error type2 occured");
      }
      else if (keycode == KeyCode.NUMPAD3)
      {
         errorMessage.setText("Oups, the exos(que)leton exploded");
      }

   }

   public void setAssistanceLevel(KeyCode keycodeAssistancelevel) //simulation of the rotary switch
   {
      if (keycodeAssistancelevel == KeyCode.DIGIT1)
      {
         assistancelevelChosen = "Low";
         modifyAssistanceLevel();
      }
      else if (keycodeAssistancelevel == KeyCode.DIGIT2)
      {
         assistancelevelChosen = "Medium";
         modifyAssistanceLevel();
      }
      else if (keycodeAssistancelevel == KeyCode.DIGIT3)
      {
         assistancelevelChosen = "High";
         modifyAssistanceLevel();
      }
      else if (keycodeAssistancelevel == KeyCode.DIGIT4)
      {
         assistancelevelChosen = "Off";
         modifyAssistanceLevel();
      }

   }

   private void modifyAssistanceLevel()
   {
      if (Objects.equals(controlModeChosen, AugmentativeControlMode.DO_NOTHING))
      {
         conrtolModeRunning.setText(controlModeChosen.toString());
      }
      else
      {
         conrtolModeRunning.setText(assistancelevelChosen + " " + controlModeChosen);
      }
   }

   public void selectAssistanceMode()
   {
      controlModeChosen = controlModeChoice.getSelectionModel().getSelectedItem();
      if (Objects.equals(controlModeChosen, AugmentativeControlMode.DO_NOTHING))
      {
         conrtolModeRunning.setText(controlModeChosen.toString());
      }
      else
      {
         conrtolModeRunning.setText(assistancelevelChosen + " " + controlModeChosen);
      }
   }

   public void goUp()
   {
      int currentIndex = controlModeChoice.getSelectionModel().getSelectedIndex();
      if (currentIndex > 0)
      {
         controlModeChoice.getSelectionModel().select(currentIndex - 1);
      }
      setStyleSelectedItem();

   }

   public void goDown()
   {
      int currentIndex = controlModeChoice.getSelectionModel().getSelectedIndex();
      if (currentIndex < controlModeChoice.getItems().size() - 1)
      {
         controlModeChoice.getSelectionModel().select(currentIndex + 1);
      }
      setStyleSelectedItem();
   }

   public void setStyleSelectedItem()
   {
      controlModeChoice.setCellFactory(listView -> new ListCell<>()
      {
         @Override
         protected void updateItem(AugmentativeControlMode item, boolean empty)
         {
            super.updateItem(item, empty);
            if (item != null)
            {
               setText(item.toString());
               if (isSelected())
               {
                  setTextFill(Color.WHITE);
                  setStyle("-fx-control-inner-background: blue;");
               }
               else
               {
                  setTextFill(Color.BLACK);
                  setStyle("-fx-control-inner-background: white;");
               }
            }
            else
            {
               setText(null);
               setGraphic(null);
            }
         }
      });

   }

   public void loosingBattery()
   {
      if (batteryLevel <= 0.1)// have to check later for the rounding 
         errorMessage.setText("Oups, battery is dead");

      else if (batteryLevel <= 6)
      {
         errorMessage.setText("Careful, battery is almost dead");
         batteryLevel += -1;
         batteryLevelProgressBar.setProgress(batteryLevel / 100);
         batteryLevelText.setText(batteryLevel + "%");
         batteryLevelProgressBar.setStyle("-fx-accent: #ff0000;");
      }

      else if (batteryLevel <= 21)
      {
         errorMessage.setText("Careful, battery is low");
         batteryLevel += -1;
         batteryLevelProgressBar.setProgress(batteryLevel / 100);
         batteryLevelText.setText(batteryLevel + "%");
         batteryLevelProgressBar.setStyle("-fx-accent: #ff9d00;");
      }
      else
      {
         batteryLevel += -1;
         batteryLevelProgressBar.setProgress(batteryLevel / 100);
         batteryLevelText.setText(batteryLevel + "%");
      }

   }

   public void chargingBattery()
   {
      batteryLevel = 100;
      batteryLevelProgressBar.setProgress(batteryLevel / 100);
      batteryLevelText.setText(batteryLevel + "%");
      batteryLevelProgressBar.setStyle("-fx-accent: #00FF00;");

   }

}
