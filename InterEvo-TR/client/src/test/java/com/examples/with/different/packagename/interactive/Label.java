// *******************************************
// * JIPA - Java Interpreted Pseuso-Assembly *
// ***                                     ***
// * Created by: Dominic Charley-Roy         *
// ***                                     ***
// * Released under GNU General              *
// * Public License                          *
// *******************************************
package com.examples.with.different.packagename.interactive;

/**
 * Class Name: Label
 * Class Use: This class will handle all the labels in JIPA, and provide
 *            a basic interface for accessinh and using them.
 * @author Dominic Charley-Roy
 */
import java.util.*;

public class Label {
    public static Collection<Label> labelList = new ArrayList<Label>();
    private int lineNumber; private String labelName;

    public Label(int line, String name)
    {
        this.lineNumber = line;
        this.labelName = name;
    }

    public String getName() {return labelName;}
    public int getLine() {return lineNumber;}

    public static int findLabel(String name)
    {
        // Iterate through the label list until we find a label with that name
        Iterator iterator= labelList.iterator();
        while (iterator.hasNext())
        {
            Label curLabel = (Label) iterator.next();
            if (curLabel.getName().equals(name))
                return curLabel.getLine();
        }

        return -1;

    }

    public static void buildLabelList()
    {
        // Loop through each line looking for a label
        for (int ctr=0; ctr < Main.TOTAL_INSTRUCTIONS;ctr++)
        {
            if (Main.instruction[ctr].toLowerCase().startsWith("lbl ")){
                labelList.add(new Label(ctr,Main.instruction[ctr].substring(4)));
            }
        }
    }
}
