FROM python
WORKDIR /
COPY . .

# Install dependencies
RUN ["python", "-m", "pip", "install", "-r", "./requirements.txt"]

# Create Env
ENV MONGO_USER=root
ENV MONGO_PASS=root
ENV MONGO_HOST=db
ENV MONGO_PORT=27017
ENV MONGO_SRV=False
ENV BOT_TOKEN=""
ENV BOT_PREFIX=.

# Run application
CMD ["python3", "./main.py"]
