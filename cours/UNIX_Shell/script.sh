#!/bin/bash
echo purge
apt-get purge
echo autoremove
apt-get autoremove
echo update
apt-get update
echo upgrade tomcat8
apt-get upgrade tomcat8
#crontab < <(crontab -l ; echo "0 * * * * echo test")
#(crontab -l 2>/dev/null; echo "0 * * * * echo test") | crontab -
