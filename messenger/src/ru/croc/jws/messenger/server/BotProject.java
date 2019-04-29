package ru.croc.jws.messenger.server;

import ru.croc.jws.messenger.common.Message;
import ru.croc.jws.messenger.common.User;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotProject implements Bot {
    private final static String NAME_OF_BOT = "bot";
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
        System.out.println("ok1 " + command);
        if (commandLine[0].equals("@" + NAME_OF_BOT)) {
            System.out.println("ok " + command);
            switch (command) {
                case "старт":
                    System.out.println("case: start");
                    flag = true;
                    messagesForBot.remove(searchMessage("start"));
                    mainLoop(message, messageRepository);
                    break;
                case "стоп":
                    System.out.println("case: stop");
                    flag = false;
                    break;
                case "помощь":
                    getDelay(2);
                    messageRepository.addMessage(new Message(bot, "all commands: start, stop"));
                    break;
                case "агрессия":
                    getRage(commandLine[2], messageRepository);
                    break;
                default:
                    messageRepository.addMessage(new Message(new User("bot"), "@" + message.getUser().getName() + ", я такого не умею"));
            }
        }
    }

    private Message searchMessage(String message) {
        for (Message i : messagesForBot) {
            if (i.getText().startsWith(message)) {
                return i;
            }
        }
        return null;
    }

    private void mainLoop(Message message, MessageRepository messageRepository) {
        while (flag) {
            for (Message i : messageRepository.findMessagesAfter(currentTime)) {
                messagesForBot.add(i);
            }
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

                System.out.println(result + " условие " + allLogin.contains(result.replace("@", "")));
                if (!result.equals("") && search(result)) {
                    System.out.println("new message");
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

    private HashSet getAllLogin() {
        for (Message i : messagesForBot) {
            allLogin.add(i.getUser().getName());
        }
        return allLogin;
    }

    private void getDelay(int timeSecond) {
        try {
            Thread.sleep(timeSecond * 1000);
        } catch (InterruptedException e) {
            return;
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
            System.out.println("searchLogin: " + fields.get(0));
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
                "инетерсеный"};
        int sizeOfAA = allAnswer.length;
        int r = rnd.nextInt(sizeOfAA);
        return ", ты " + allAnswer[r];
    }

    private void getRage(String login, MessageRepository messageRepository) {
        getAllLogin();
        for (String i : allLogin) {
            if (i.equals(NAME_OF_BOT)) {
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
        return ",ты " + allAnswer[r];
    }

    private boolean search(String name) {
        for (String i : allLogin) {
            if (i.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
