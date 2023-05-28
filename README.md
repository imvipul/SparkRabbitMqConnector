# SparkRabbitMqConnector

This code demonstrates how to read data from a RabbitMQ stream using Spark's RabbitMQ connector. The `SparkConf` object is used to set the configuration parameters for the connection details, the exchange, and the queue. The `StreamingContext` is used to create the Spark streaming context with a batch interval of 1 second. 

The `RabbitMQUtils.createStream()` function is used to create a DStream of data from RabbitMQ with the given configuration. It takes in various arguments such as `uri` for the connection details, `QueueDescription` for setting up the queue parameters, `ExchangeDescription` for setting up the exchange parameters, and the `routing_key` for the desired binding of the queue to the exchange. 

Finally, the `print()` method is called on the stream to display the received data, and the `start()` method is called on the `StreamingContext` to start the streaming process, followed by `awaitTermination()` to wait for the process to terminate.
