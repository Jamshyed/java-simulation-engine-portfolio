package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class HelpCommand extends Command
{

    public HelpCommand(List<String> words)
    {
        super(words);
    }

    public String execute(Context ctx)
    {
        return "Commands: \nbuild <type>\ninfo <resources/entities>\ntick\ncheat\nlaunch <name>\ngraph <resource>\nsave <file>\nload <file>\nhelp\nquit";
            
            
    }
}