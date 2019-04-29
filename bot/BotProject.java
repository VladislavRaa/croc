package ru.croc.jws.messenger.server;

import ru.croc.jws.messenger.common.Message;
import ru.croc.jws.messenger.common.User;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotProject implements Bot {
    private final static String NAME_OF_BOT = "бот";
    private User bot = new User(NAME_OF_BOT);
    private final Random rnd = new Random(System.currentTimeMillis());
    private static Date currentTime = new Date();
    private static ArrayList<Message> messagesForBot = new ArrayList<>();
    private static HashSet<String> allLogin = new HashSet<>();
    private static boolean flag;

    @Override
    public User getUser() {
        return bot;
    }

    @Override
    public void onMessage(Message message, MessageRepository messageRepository) {
        String[] commandLine = message.getText().split(" ");
        String command = commandLine[1];
        if (commandLine[0].equals("@" + NAME_OF_BOT)) {
            switch (command) {
                case "старт":
                    flag = true;
                    mainLoop(messageRepository);
                    break;
                case "стоп":
                    flag = false;
                    break;
                case "помощь":
                    getDelay(2);
                    messageRepository.addMessage(new Message(bot, "доступыне команды: старт, стоп, помощь, агрессия"));
                    break;
                case "агрессия":
                    getRage(commandLine[2], messageRepository, message);
                    break;
                default:
                    messageRepository.addMessage(new Message(new User(NAME_OF_BOT), "@" + message.getUser().getName() + ", я такого не умею"));
                    getDelay(1);
            }
        }
    }

    private void mainLoop(MessageRepository messageRepository) {
        while (flag) {
            messagesForBot.addAll(messageRepository.findMessagesAfter(currentTime));
            getAllLogin();
            for (String i : allLogin) {
                System.out.println(i);
            }
            Iterator iter = messagesForBot.iterator();
            while (iter.hasNext()) {
                Message currentMessage = (Message) iter.next();
                String login = currentMessage.getUser().getName();
                String text = currentMessage.getText();
                String result = searchLogin(text);
                if (!result.equals("") && search(result)) {
                    Message myAnswer = new Message(new User(result), "@" + login + " " + getRandomAnswer());
                    messageRepository.addMessage(myAnswer);
                    getDelay(2);
                }
                iter.remove();
            }
            messagesForBot.clear();
            currentTime = new Date();
            getDelay(5);
        }
    }

    private void getAllLogin() {
        for (Message i : messagesForBot) {
            allLogin.add(i.getUser().getName());
        }
    }

    private void getDelay(int timeSecond) {
        try {
            Thread.sleep(timeSecond * 1000);
        } catch (InterruptedException ignored) {
        }
    }

    private String searchLogin(String message) {
        String pattern = "@{1}([а-яА-Я]+)(\\s||[,.])";
        ArrayList<String> fields = new ArrayList<>();
        Pattern regex = Pattern.compile(pattern);
        Matcher m = regex.matcher(message);
        while (m.find()) {
            fields.add(m.group(0));
        }
        if (!fields.isEmpty()) {
            return fields.get(0).replace("@", "");
        }
        return "";
    }

    private String getRandomAnswer() {
        String allAnswer[] = {
                "молодец",
                "умничка",
                "добрый",
                "хорооший",
                "интересный"};
        int sizeOfAA = allAnswer.length;
        int r = rnd.nextInt(sizeOfAA);
        return ", ты " + allAnswer[r];
    }

    private void getRage(String login, MessageRepository messageRepository, Message message) {
        getAllLogin();
        for (String i : allLogin) {
            if (i.equals(NAME_OF_BOT) || i.equals(message.getUser().getName())) {
                continue;
            }
            Message myAnswer = new Message(new User(i.replace("@", "")), "@" + login + " " + getRandomRageAnswer());
            messageRepository.addMessage(myAnswer);
            getDelay(1);
        }
    }

    private String getRandomRageAnswer() {
        String allAnswer[] = {
                "козел",
                "неудачник",
                "питонист",
                "лох",
                "дурачок"};
        int sizeOfAA = allAnswer.length;
        int r = rnd.nextInt(sizeOfAA);
        return ", ты " + allAnswer[r];
    }

    private boolean search(String name) {
        for (String i : allLogin) {
            if (i.equals(name)) {
                System.out.println("нашел: " + i);
                return true;
            }
        }
        return false;
    }
}