# Use case 1: Production Trouble shooting
## Setup
* Copy logsimulator.zip to VM and unzip it ("jar xvf logsimulator.zip")
* Copy filebeat.yml to /etc/filebeat
* Edit log path in filebeat.yml
* Copy logstash.yml to /etc/logstash/conf.d
* Restart logstsh and filebeat service
* Generate log
```
java -Dexample.logsimulator.sleeptime=10 -jar logsimulator.jar
```
* Create index pattern
* Import kibana object file: kibana.json


## Presentation
* Discovery Tab
  * index
  * time range
  * JSON
  
* Show user case 1 dashboard
  * Visualization 
  * Multi-dimentation drill down
  * keyword search
  * Production trouble shooting
  * Alerting integration
