Qui ước:
	server1: 10.30.22.158
	server2: 10.30.22.153
	server3: 10.30.22.155

Các service sẽ được chạy trên các server được chỉ định:

	1. ZooKeeper (3 instances):
		instance 1 được chạy trên server1
		instance 2 được chạy trên server3
		instance 3 được chạy trên server2

	2. ActiveMQ (1 instance): được chạy trên server1
		
	3. TokenServer: được chạy trên server2
		
	4. Storage: được chạy trên server2
		
	5. AccountBalanceCache: được chạy trên server1
		
	6. LatestTranxCache: được chạy trên server1
		
	7. PaymentZKBackEnd (PUSH): được chạy trên server2
		
	8. PaymentZKBackEnd (BILL): được chạy trên server1
		
	9. PaymentGateway (BILL): được chạy trên server1
		
	10. PaymentAdmin: được chạy trên server2
		
	11. Web-service for PUSH: được chạy trên server2

Database (mysql) được chạy trên server2(master) và trên server3(slave)


