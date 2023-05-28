package com.vk.spark.rabbitmq.connector

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object RabbitStreamMain {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkRabbitMQ").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(2))
    ssc.checkpoint("checkpoint")
    val rabbitMqReceiver = new RabbitMQReceiver("localhost", 8311, "Admin", "Admin", "test_spark_stream")
    val stream = ssc.receiverStream(rabbitMqReceiver)
    stream.foreachRDD(rdd => {
      println(s"NumPartitions ${rdd.getNumPartitions} and ${rdd.count()}");

    })
    ssc.start()
    ssc.awaitTermination()

  }
}