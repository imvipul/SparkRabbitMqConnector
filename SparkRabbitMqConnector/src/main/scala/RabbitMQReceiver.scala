package com.vk.spark.rabbitmq.connector

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import com.rabbitmq.client.{AMQP, BasicProperties, Channel, ConnectionFactory, DefaultConsumer, Envelope}

class RabbitMQReceiver(host: String, port: Int, username: String, password: String, queueName: String)
  extends Receiver[String](StorageLevel.MEMORY_ONLY) {
  override def onStart(): Unit = {
    val factory = new ConnectionFactory()
    factory.setHost(host)
    factory.setPort(port)
    factory.setUsername(username)
    factory.setPassword(password)

    val connection = factory.newConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(queueName, false, false, false, null)
    channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
        val message = new String(body, "UTF-8")
//        println(s"message= ${message}")
        store(message)
      }
    })
  }

  override def onStop(): Unit = {}
}
