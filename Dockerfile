FROM centos:7
RUN yum -y install python
RUN yum -y install epel-release && yum clean all
RUN yum -y install python-pip && yum clean all
RUN yum install -y \
       java-1.8.0-openjdk \
       java-1.8.0-openjdk-devel
ADD swap.py /
COPY swap.py /
CMD [ "python","./swap.py"]