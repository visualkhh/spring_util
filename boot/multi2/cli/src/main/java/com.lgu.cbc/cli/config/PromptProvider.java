package com.lgu.cbc.cli.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PromptProvider implements org.springframework.shell.jline.PromptProvider {

    @Override
    public AttributedString getPrompt() {
            return new AttributedString("CBC shell:>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }

//    @EventListener
//    public void handle(ConnectionUpdatedEvent event) {
//        this.connection = event.getConnectionDetails();
//    }
}
