package com.example.adou.mydrone.fr.istic.sit.rabbit.client;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adou.mydrone.R;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by rostom on 13/03/18.
 */

public class ClientRabbitService extends Service {

    Thread subscribeThread;
    Thread publishThread;
    String incomingMessageHandler="";

    private BlockingDeque<String> queue = new LinkedBlockingDeque<String>();

    ConnectionFactory factory = new ConnectionFactory();

    /** Called when the service is being created. */
    @Override
    public void onCreate() {
        setupConnectionFactory();
        subscribe();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setupConnectionFactory();
        subscribe();
        return START_STICKY;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        publishThread.interrupt();
        subscribeThread.interrupt();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void setupConnectionFactory() {
        String uri = "127.0.0.1";
        try {
            factory.setAutomaticRecoveryEnabled(false);
            factory.setUri(uri);
        } catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    void subscribe() {
        subscribeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Connection connection = factory.newConnection();
                        Channel channel = connection.createChannel();
                        channel.basicQos(1);

                        channel.exchangeDeclare("exchangeName", "topic");
                        String q = channel.queueDeclare().getQueue();

                        channel.queueBind(q, "exchangeName", "drone");

                        Consumer consumer = new DefaultConsumer(channel) {
                            @Override
                            public void handleDelivery(String consumerTag, Envelope envelope,
                                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                                incomingMessageHandler = new String(body, "UTF-8");
                                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + incomingMessageHandler + "'");
                            }
                        };

                        channel.basicConsume(q, true, consumer);

                    } catch (Exception e1) {
                        Log.d("", "Connection broken: " + e1.getClass().getName());
                        try {
                            Thread.sleep(4000); //sleep and then try again
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            }
        });
        subscribeThread.start();
    }
}
