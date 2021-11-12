FROM python:3.8.3-slim-buster
Copy filesCOPY . /src
RUN pip install -r /src/requirements.txt
