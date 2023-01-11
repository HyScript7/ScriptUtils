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
import os

import discord
from discord.ext import commands
from decouple import config
from pymongo import MongoClient

__version__ = "1.0"

TOKEN = config("BOT_TOKEN", "").strip()
PREFIX = config("BOT_PREFIX", ".").strip()
MONGO_USER = config("MONGO_USER", "root").strip()
MONGO_PASS = config("MONGO_PASS", "root").strip()
MONGO_HOST = config("MONGO_HOST", "127.0.0.1").strip()
MONGO_PORT = config("MONGO_PORT", "27017").strip()
MONGO_SRV = bool(config("MONGO_SRV", "False"))


class suDatabase:
    def __init__(
        self,
        MONGO_SRV: str,
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
        intents = discord.Intents.all() # TODO: Change this in the first release version to really only enable what it needs
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
        for i in self.Modules:
            print(f"Extension {i} is loading")
            try:
                await self.load_extension(i)
                print(f"Extension {i} has been loaded")
            except Exception as e:
                print(f"Extension {i} has failed to load: {e}")

    async def on_ready(self):
        await bot.wait_until_ready()
        if not self.Synced:
            await bot.tree.sync()
            self.Synced = True
            print("Commands synced!")


bot = suBot()

if __name__ == "__main__":
    from sys import exit

    if (not len(TOKEN) or TOKEN.count(".") != 2) if type(TOKEN) is str else True:
        exit("Invalid bot token has been provided!")
    
    asyncio.run(bot.loadModules())

    bot.run(TOKEN)
    exit(0)
