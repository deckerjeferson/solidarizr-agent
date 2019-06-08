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
    WANT_TO_FIND_PROJECT("want_to_find_project", null),
    UNKNOWN(null, "Desculpe, não entendi o que você falou."),
    ASK_TARGET_AUDIENCE("asking_target_audience", "Legal! Pode selecionar abaixo o público alvo que mais te interessa! :)"),
    ASK_CATEGORIES("defined_target_audience", "Ok! Agora que você escolheu seu público alvo, favor escolha a categoria do projeto de voluntariado"),
    GET_EVENTS,
    CREATE_PROJECTS("want_to_create_project", "Olá! Ainda estamos desenvolvendo a opção para cadastrar projetos voluntários pelo Bot!\nPara realizar cadastros de projetos enquanto esta funcionalidade não está disponível, por favor, utilize o formulário a seguir: https://forms.gle/KgpyvsU9yL1iyJzBA !\nMuito obrigado! <3");

    private String trigger;
    private String response;

}
