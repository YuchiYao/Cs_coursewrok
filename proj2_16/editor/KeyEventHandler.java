package editor;

import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


import java.util.LinkedList;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    /** An EventHandler to handle keys that get pressed. */
        int textCenterX;
        int textCenterY;

        private static final int STARTING_FONT_SIZE = 20;
        private static final int STARTING_TEXT_POSITION_X = 250;
        private static final int STARTING_TEXT_POSITION_Y = 250;
        // The text starts at the middle of the windows : Initialization //

        private static final String MESSAGE_PREFIX =
            "User pressed the shortcut key (command or control, depending on the OS)";

        /** The Text to display on the screen. */
        private Text displayText = new Text(STARTING_TEXT_POSITION_X, STARTING_TEXT_POSITION_Y, "");
        private LinkedList<String> inputcharter = new LinkedList<>();
        private int fontSize = STARTING_FONT_SIZE;

        private String fontName = "Verdana";

        KeyEventHandler(final Group root, int windowWidth, int windowHeight) {
            // Set the text begin at the leftup corner
            textCenterX = 5;
            textCenterY = 0;

            // Initialize some empty text and add it to root so that it will be displayed.
            displayText = new Text(textCenterX, textCenterY, "");
            // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
            // that when the text is assigned a y-position, that position corresponds to the
            // highest position across all letters (for example, the top of a letter like "I", as
            // opposed to the top of a letter like "e"), which makes calculating positions much
            // simpler!
            displayText.setTextOrigin(VPos.TOP);
            displayText.setFont(Font.font(fontName, fontSize));

            // All new Nodes need to be added to the root in order to be displayed.
            root.getChildren().add(displayText);
        }
        /** Convert a list of char to string and set the displaytext with this string*/
        public void CombineCharToString(LinkedList<String> a){
            if (a!=null){
                // direct put into displaytext, get rid of string stack overflow//
                displayText.setText(String.join("",a));
            }
        }

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_TYPED && !keyEvent.isShortcutDown()) {
                // Make sure the character is pressed the command is not pressed
                // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
                // the KEY_TYPED event, javafx handles the "Shift" key and associated
                // capitalization.
                String characterTyped = keyEvent.getCharacter();
                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    // Ignore control keys, which have non-zero length, as well as the backspace
                    // key, which is represented as a character of value = 8 on Windows.
                    inputcharter.addLast(characterTyped);
                    CombineCharToString(inputcharter);
                    keyEvent.consume();
                }

            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.UP) {
                    fontSize += 5;
                    displayText.setFont(Font.font(fontName, fontSize));

                } else if (code == KeyCode.DOWN) {
                    fontSize = Math.max(0, fontSize - 5);
                    displayText.setFont(Font.font(fontName, fontSize));

                } else if (code == KeyCode.BACK_SPACE && inputcharter.size()>0){
                    // If the backspace is press
                    inputcharter.removeLast();
                    CombineCharToString(inputcharter);
                    keyEvent.consume();
                }else if(keyEvent.isShortcutDown() ){

                    if (keyEvent.getCode() == KeyCode.A) { // Set all string
                        System.out.println(MESSAGE_PREFIX + " in addition to \"a\"");

                    } else if (keyEvent.getCode() == KeyCode.Z) { // Trace back
                        System.out.println(MESSAGE_PREFIX + " in addition to \"z\"");

                    } else if (keyEvent.getCode() == KeyCode.C){  // Copy the string
                        System.out.println(MESSAGE_PREFIX + " in addition to \"z\"");

                    } else if (keyEvent.getCode() == KeyCode.V){ // paste the string
                        System.out.println(MESSAGE_PREFIX + " in addition to \"z\"");

                    }
            }

            }
        }

}
