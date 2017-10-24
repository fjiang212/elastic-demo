# Prepare Virtual Machine
* Install Virtual Box for windows: available from https://www.virtualbox.org/wiki/Downloads
* Install Centos 7: https://github.com/fjiang212/my-notes/blob/master/vitualization/notes.md
* Set up yum proxy if needed (/etc/yum.conf)
```  
proxy=http://x.x.x.x:xxxx
proxy_username=xxxx
proxy_password=xxxxx
```
* Install tools
```
yum install dos2unix -y
yum install net-tools -y 
yum install lsof -y
yum install nmap -y
yum install git -y
 ```
 
# Download and Install Elastic Stack
```
rpm -i jdk-8u152-linux-x64.rpm

rpm -i elasticsearch-5.6.3.rpm
sudo systemctl daemon-reload
sudo systemctl enable elasticsearch.service
sudo systemctl start elasticsearch.servicerp

rpm -i logstash-5.6.3.rpm
sudo systemctl start logstash.servicerp

rpm -i kibana-5.6.3-
service kibana start
```
