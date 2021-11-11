FROM python:2.7
RUN yum -y install python
RUN yum -y install epel-release && yum clean all
RUN yum -y install python-pip && yum clean all

