package org.solidarizr.agent.messageHandler;

public class Messages {

    public static class Responses{
        public static final String START = "Olá! \nEu sou o Solidarizr! :D\nEstou aqui para te ajudar a encontrar projetos voluntários :)\nSempre que quiser iniciar uma conversa comigo, é só digitar \"Oi!\" ou \"/start\" ;)\nVocê gostaria de procurar um projeto voluntário?";
        public static final String GREETINGS = "Olá! \nVocê gostaria de procurar um projeto voluntário?";
        public static final String UNKNOWN = "Desculpe, ainda não estou preparado para receber esse tipo de mensagem. Se quiser iniciar uma conversa comigo digite \"Oi!\" ou \"/start\".";
        public static final String ASK_TARGET_AUDIENCE = "Legal! Pode selecionar abaixo o público alvo que mais te interessa! :)";
        public static final String ASK_CATEGORY = "Ok! Agora que você escolheu seu público alvo, favor escolha a categoria do projeto de voluntariado";
        public static final String CREATE_PROJECTS = "Olá! Ainda estamos desenvolvendo a opção para cadastrar projetos voluntários pelo Bot!\nPara realizar cadastros de projetos enquanto esta funcionalidade não está disponível, por favor, utilize o formulário a seguir: https://forms.gle/KgpyvsU9yL1iyJzBA !\nMuito obrigado! <3";
        public static final String THANKS_FOR_USING_SOLIDARIZE_TO_FIND_PROJECTS = "Muito obrigado por utilizar o Solidarizr!\nAbaixo serão enviados os eventos encontrados! :)";
        public static final String WE_HAVE_NOT_FOUND_ANY_PROJECTS = "Não encontramos projetos para os dados que informaste :/";


    }

    public static class EventText {
        public static final String NAME = "<b>Nome:</b> %s\n";
        public static final String DESCRIPTION = "<b>Descrição:</b> %s\n";
        public static final String EMAIL = "<b>Email:</b> %s\n";
        public static final String SITE = "<b>Site:</b> %s\n";
        public static final String PHONE = "<b>Telefone:</b> %s\n";
        public static final String CATEGORY = "\n<b>Categoria:</b> %s\n";
        public static final String TARGET_AUDIENCE = "<b>Público Alvo:</b> %s\n";
    }
}
