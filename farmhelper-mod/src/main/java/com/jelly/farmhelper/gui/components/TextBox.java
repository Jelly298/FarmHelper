package com.jelly.farmhelper.gui.components;

import com.jelly.farmhelper.config.ConfigHandler;
import gg.essential.elementa.UIComponent;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.components.input.UITextInput;
import gg.essential.elementa.constraints.*;

import java.awt.*;

public class TextBox extends UIBlock {
    private final UIComponent textWrapper;
    private UITextInput textInput;
    private String textValue;

    public TextBox(String optionName, String placeholder, String configName) {
        // Get value from config
        this.textValue = (String) ConfigHandler.get(configName);

        this.setX(new PixelConstraint(0))
            .setY(new SiblingConstraint())
            .setWidth(new RelativeConstraint(1))
            .setHeight(new AdditiveConstraint(new ChildBasedSizeConstraint(), new PixelConstraint(15)))
            .setColor(new Color(36, 37, 39));

        new UIText(optionName, false)
            .setX(new PixelConstraint(10))
            .setY(new PixelConstraint(5))
            .setTextScale(new PixelConstraint(1f))
            .setColor(new Color(249, 249, 249))
            .setChildOf(this);

        textWrapper = new UIBlock(new Color(30, 31, 32))
            .setX(new CenterConstraint())
            .setY(new AdditiveConstraint(new SiblingConstraint(), new PixelConstraint(5)))
            .setHeight(new PixelConstraint(20))
            .setWidth(new SubtractiveConstraint(new RelativeConstraint(1f), new PixelConstraint(20)))
            .setChildOf(this)
            .onMouseClick((component, uiClickEvent) -> {
                textInput.grabWindowFocus();
                setDefaultText();
                return null;
            });

        textInput = (UITextInput) new UITextInput(textValue.equals("") ? placeholder : textValue)
            .setX(new PixelConstraint(5))
            .setY(new CenterConstraint())
            .setWidth(new SubtractiveConstraint(new RelativeConstraint(1), new PixelConstraint(10)))
            .setChildOf(textWrapper)
            .onKeyType((component, character, integer) -> {
                textValue = textInput.getText();
                ConfigHandler.set(configName, textValue);
                return null;
            })
            .onFocusLost(component -> {
                textInput.setText(textValue.equals("") ? placeholder : textValue);
                return null;
            });
    }

    public void setDefaultText() {
        textInput.setText(textValue);
    }
}
