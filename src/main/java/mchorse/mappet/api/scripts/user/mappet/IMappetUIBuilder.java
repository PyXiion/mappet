package mchorse.mappet.api.scripts.user.mappet;

import mchorse.mappet.api.scripts.user.IScriptEvent;
import mchorse.mappet.api.ui.components.UIButtonComponent;
import mchorse.mappet.api.ui.components.UILabelComponent;
import mchorse.mappet.api.ui.components.UIStringListComponent;
import mchorse.mappet.api.ui.components.UITextComponent;
import mchorse.mappet.api.ui.components.UITextboxComponent;
import mchorse.mappet.api.ui.components.UIToggleComponent;
import mchorse.mappet.api.ui.components.UITrackpadComponent;

import java.util.List;

/**
 * This is user interface builder interface. You can create GUIs with this thing.
 *
 * TODO: example
 */
public interface IMappetUIBuilder
{
    public default void setHandler(IScriptEvent event, String function)
    {
        this.setHandler(event.getScript(), function);
    }

    public void setHandler(String script, String function);

    public IMappetUIBuilder background();

    public UIButtonComponent button(String label);

    public UILabelComponent label(String label);

    public UITextComponent text(String text);

    public default UITextboxComponent textbox()
    {
        return this.textbox("");
    }

    public UITextboxComponent textbox(String text);

    public default UIToggleComponent toggle(String label)
    {
        return this.toggle(label, false);
    }

    public UIToggleComponent toggle(String label, boolean state);

    public default UITrackpadComponent trackpad()
    {
        return this.trackpad(0);
    }

    public UITrackpadComponent trackpad(int value);

    public default UIStringListComponent stringList(List<String> values)
    {
        return this.stringList(values, -1);
    }

    public UIStringListComponent stringList(List<String> values, int selected);
}