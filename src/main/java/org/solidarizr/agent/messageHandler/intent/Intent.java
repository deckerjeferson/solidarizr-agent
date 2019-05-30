package org.solidarizr.agent.messageHandler.intent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum  Intent {
    START("/start", "Olá! \nEu sou o Solidarize! :D\nEstou aqui para te ajudar a encontrar um projeto voluntário que tenha a sua cara! :) \nVocê gostaria de procurar um projeto voluntário?"),
    GREETING("Oi!", "Olá! \nVocê gostaria de procurar um projeto voluntário?"),
    UNKNOWN(null, "Desculpe, não entendi o que você falou."),
    ASK_TARGET_AUDIENCE("asking_target_audience", "Legal! Pode selecionar abaixo o público alvo que mais te interessa! :)");

    private String trigger;
    private String response;

}
