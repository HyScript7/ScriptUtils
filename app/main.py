#!/bin/python
#   ____            _       _   _   _ _   _ _
#  / ___|  ___ _ __(_)_ __ | |_| | | | |_(_) |___
#  \___ \ / __| '__| | '_ \| __| | | | __| | / __|
#   ___) | (__| |  | | |_) | |_| |_| | |_| | \__ \
#  |____/ \___|_|  |_| .__/ \__|\___/ \__|_|_|___/
#                    |_|
#
# Author(s): HyScript7, HyBomtarax
# License: MIT License
# See LICENSE.txt for more information
#
import asyncio
import logging
import os
import time
from logging import handlers
from sys import stdout

import discord
import models
from decouple import config
from discord.ext import commands
from pymongo import MongoClient

__version__ = "1.0"

TOKEN = config("BOT_TOKEN", "").strip()
PREFIX = config("BOT_PREFIX", ".").strip()
MONGO_USER = config("MONGO_USER", "root").strip()
MONGO_PASS = config("MONGO_PASS", "root").strip()
MONGO_HOST = config("MONGO_HOST", "127.0.0.1").strip()
MONGO_PORT = config("MONGO_PORT", "27017").strip()
MONGO_SRV = config("MONGO_SRV", "False").lower() == "true"


class suDatabase:
    def __init__(
        self,
        MONGO_SRV: bool,
        MONGO_HOST: str,
        MONGO_PORT: str,
        MONGO_USER: str,
        MONGO_PASS: str,
    ) -> None:
        self.dbClient = MongoClient(
            f"mongodb{'+srv' if MONGO_SRV else ''}://{MONGO_USER}:{MONGO_PASS}@{MONGO_HOST}{(':'+MONGO_PORT) if not MONGO_SRV else ''}",
            serverSelectionTimeoutMS=5000,
        )
        self.dbScriptUtils = self.dbClient["ScriptUtils"]


class suBot(commands.Bot):
    def __init__(self) -> None:
        intents = (
            discord.Intents.all()
        )  # TODO: Change this in the first release version to really only enable what it needs
        super().__init__(PREFIX, intents=intents)
        self.Synced = False
        self.DB = suDatabase(MONGO_SRV, MONGO_HOST, MONGO_PORT, MONGO_USER, MONGO_PASS)
        self.suVersion = __version__

    async def loadModules(self):
        Modules = []
        for i in os.listdir("modules"):
            if i.startswith("__"):
                continue
            Modules.append("modules." + i.strip(".py"))
        for i in Modules:
            logging.info(f"Extension {i} is loading")
            try:
                await self.load_extension(i)
                logging.info(f"Extension {i} has been loaded")
            except Exception as e:
                logging.error(f"Extension {i} has failed to load: {e}")

    async def on_ready(self):
        await bot.wait_until_ready()
        if not self.Synced:
            await bot.tree.sync()
            self.Synced = True
            logging.info("Commands synced!")


bot = suBot()

# ? There definitely has to be a better way to do this
# Create loggers & set levels
discord_logger = logging.getLogger("discord")
discord_logger.setLevel(logging.INFO)
root_logger = logging.getLogger()
root_logger.setLevel(logging.INFO)
logging.getLogger("discord.http").setLevel(logging.INFO)

# Create formatter
dt_fmt = "%d.%m.%Y %H:%M:%S"
formatter = logging.Formatter(
    "[{asctime}] [{levelname:<8}] {name}: {message}", dt_fmt, style="{"
)

# Create handlers

log_file = logging.handlers.RotatingFileHandler(
    filename=f"./logs/ScriptUtils {time.ctime()}.log",
    encoding="utf-8",
    maxBytes=32 * 1024 * 1024,  # 32 MiB
    backupCount=3,  # Rotate through 5 files
)
log_file.setFormatter(formatter)
log_console = logging.StreamHandler(stdout)
log_console.setFormatter(formatter)

# Set handlers
discord_logger.addHandler(log_file)
root_logger.addHandler(log_file)
root_logger.addHandler(log_console)

if __name__ == "__main__":
    from sys import exit

    if (not len(TOKEN) or TOKEN.count(".") != 2) if type(TOKEN) is str else True:
        logging.error("Invalid token: Token failed check")
        exit("Invalid bot token has been provided!")

    try:
        asyncio.run(bot.loadModules())
        bot.run(TOKEN, log_handler=None)
    except KeyboardInterrupt:
        logging.info("Received KeyboardInterrupt")
    except Exception as e:
        logging.error(f"Uncaught exception: {e}")
        exit(e)
    logging.info("Bot stopped, exiting")
    exit(0)
