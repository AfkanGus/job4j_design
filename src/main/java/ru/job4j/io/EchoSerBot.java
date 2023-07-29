package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3. Бот [#7921 # [#7921].
 * Этот класс представляет простого HTTP-бота (EchoBot).
 * Он принимает запросы от клиентов по протоколу HTTP и отвечает на них.
 * Если клиент отправляет запрос с параметром "?msg=Hello", сервер отвечает "Hello".
 * Если клиент отправляет запрос с параметром "?msg=Exit", сервер отвечает
 * "Server is closing!" и закрывает свой серверный сокет.
 * В остальных случаях сервер отвечает "What".
 */
public class EchoSerBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoSerBot.class.getName());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                try (OutputStream outputStream = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream())
                     )) {
                    StringBuilder reqFromClient = new StringBuilder();
                    outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        reqFromClient.append(str).append("\r\n");
                    }
                    /*ответ сервера*/
                    String response;
                    /*запрос клиента*/
                    String request = reqFromClient.toString();
                    if (request.contains("?msg=Hello")) {
                        response = "Hello";
                    } else if (request.contains("?msg=Exit")) {
                        response = "Server is closing!";
                        serverSocket.close();
                    } else {
                        response = "What";
                    }
                    outputStream.write(("HTTP/1.1 200 OK\r\n\r\n" + response).getBytes());
                    outputStream.flush();
                    throw new IOException("exception");
                }
            }
        } catch (IOException e) {
            LOGGER.error("Произошла ошибка при работе с серверным сокетом: ", e);
        }
    }
}

