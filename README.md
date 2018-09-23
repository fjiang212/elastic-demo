# Prepare Virtual Machine
https://github.com/fjiang212/my-notes/blob/master/devops/vitualization/vituralbox.md#base-virtual-machine-centos_base

# Download and Install Elastic Stack

## Install Elastic Stack and X-Pack
* Install JDK
```
rpm -i jdk-8u152-linux-x64.rpm
```
* Install Elasticsearch: https://www.elastic.co/guide/en/elasticsearch/reference/current/rpm.html
```
rpm -i elasticsearch-6.2.2.rpm
systemctl daemon-reload
systemctl enable elasticsearch.service
/usr/share/elasticsearch/bin/elasticsearch-plugin install file:/root/x-pack-6.2.2.zip 
```
* Install Logstash: 
```
rpm -i logstash-6.2.2.rpm
```
* Install Kibana
```
rpm -i kibana-6.2.2-x86_64.rpm
systemctl daemon-reload
systemctl enable kibana.service
/usr/share/kibana/bin/kibana-plugin install file:/root/x-pack-6.2.2.zip
```
* Install Filebeat
```
rpm -i filebeat-5.6.3-x86_64.rpm
```

## Config Elastic Stack
* Edit /etc/elasticsearch/elasticsearch.yml
```
network.host: 0.0.0.0
```
* Edit /etc/kibana/kibana.yml
```
server.host: 0.0.0.0
```

## Start Elastic Stack
```
service elasticsearch start
service lostash start
service kibana start
service filebeat start
```
