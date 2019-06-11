package org.solidarizr.agent.messageHandler.intent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum  Intent {
    START("/start"),
    GREETING("Oi!"),
    WANT_TO_FIND_PROJECT("want_to_find_project"),
    UNKNOWN,
    ASK_TARGET_AUDIENCE("asking_target_audience"),
    ASK_CATEGORIES("defined_target_audience"),
    GET_EVENTS,
    CREATE_PROJECTS("want_to_create_project");

    private String trigger;
}
